package com.example.demo.documento;

import com.example.demo.productoCatalogo.ProductoCatalogoService;
import com.example.demo.productoParque.ProductoParque;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/documentos")
public class DocumentoController {

    private final DocumentoService documentoService;
    private final ProductoCatalogoService productoCatalogoService;

    public DocumentoController(DocumentoService documentoService,
                               ProductoCatalogoService productoCatalogoService) {
        this.documentoService = documentoService;
        this.productoCatalogoService = productoCatalogoService;
    }

    // ===================== INGRESOS =====================
    /** Vista principal de Ingresos */
    @GetMapping("/ingresos")
    public String mostrarIngresos(Model model) {
        model.addAttribute("ingresos", documentoService.listarIngresos());
        model.addAttribute("productos", productoCatalogoService.listarProductosCatalogoActivos());
        model.addAttribute("nuevoNumero", documentoService.generarNumeroIngreso());
        model.addAttribute("fechaActual", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        return "documentos/ingresos";
    }

    /** Guardar ingreso (JSON) */
    @PostMapping(value = "/guardarIngreso", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> guardarIngreso(@RequestBody Documento doc, HttpSession session) {
        doc.setTipoMovimiento("INGRESO");
        doc.setIdUsuario(resolverUsuarioSesion(session));
        int id = documentoService.registrarDocumentoConDetalles(doc);
        return ResponseEntity.ok("✅ Ingreso registrado correctamente. ID=" + id);
    }

    // ===================== SALIDAS =====================
    /** Vista principal de Salidas */
    @GetMapping("/salidas")
    public String mostrarSalidas(Model model) {
        List<Documento> salidas = documentoService.listarSalidas();
        List<ProductoParque> productosParque = documentoService.listarProductosEnParqueActivos();

        model.addAttribute("salidas", salidas);
        model.addAttribute("productosParque", productosParque);
        model.addAttribute("nuevoNumero", documentoService.generarNumeroSalida());
        model.addAttribute("fechaActual", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        return "documentos/salidas";
    }

    /** Guardar salida (JSON) */
    @PostMapping(value = "/guardarSalida", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> guardarSalida(@RequestBody Documento doc, HttpSession session) {
        doc.setTipoMovimiento("SALIDA");
        doc.setIdUsuario(resolverUsuarioSesion(session));
        int id = documentoService.registrarSalidaConDetalles(doc);
        return ResponseEntity.ok("✅ Salida registrada correctamente. ID=" + id);
    }

    // ===================== COMPATIBILIDAD (POST /guardar) =====================
    @PostMapping(value = "/guardar", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> guardarGenerico(@RequestBody Documento doc, HttpSession session) {
        String tipo = (doc.getTipoMovimiento() == null || doc.getTipoMovimiento().isBlank())
                ? "INGRESO" : doc.getTipoMovimiento().toUpperCase(Locale.ROOT);
        doc.setTipoMovimiento(tipo);
        doc.setIdUsuario(resolverUsuarioSesion(session));

        int id;
        if ("SALIDA".equals(tipo)) {
            id = documentoService.registrarSalidaConDetalles(doc);
        } else {
            id = documentoService.registrarDocumentoConDetalles(doc);
        }

        String msg = ("SALIDA".equals(tipo) ? "✅ Salida" : "✅ Ingreso") + " registrado correctamente. ID=" + id;
        return ResponseEntity.ok(msg);
    }

    // ===================== DETALLES =====================
    @GetMapping("/{id}")
    @ResponseBody
    public Documento obtenerDocumentoPorId(@PathVariable int id) {
        return documentoService.obtenerPorId(id);
    }

    @GetMapping("/detalle/{id}")
    @ResponseBody
    public ResponseEntity<List<DocumentoDetalle>> listarDetalles(@PathVariable int id) {
        try {
            List<DocumentoDetalle> detalles = documentoService.listarDetalles(id);
            if (detalles == null || detalles.isEmpty()) {
                System.out.println("⚠️ No se encontraron detalles para documento " + id);
                return ResponseEntity.notFound().build();
            }
            System.out.println("✅ Se encontraron " + detalles.size() + " detalles para documento " + id);
            return ResponseEntity.ok(detalles);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    // ===================== Helpers =====================
    private int resolverUsuarioSesion(HttpSession session) {
        Object u = session.getAttribute("usuarioLogeado");
        if (u == null) u = session.getAttribute("usuarioLogueado");
        if (u instanceof com.example.demo.usuario.Usuario usr) {
            return usr.getId();
        }
        return 1; // fallback
    }
}
