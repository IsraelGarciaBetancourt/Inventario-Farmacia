package com.example.demo.documento;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.productoCatalogo.ProductoCatalogoService;
import com.example.demo.productoParque.ProductoParqueService;
import com.example.demo.usuario.Usuario;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/documentos")
public class DocumentosController {

    private final DocumentoService documentoService;
    private final ProductoCatalogoService catalogoService;
    private final ProductoParqueService parqueService;

    public DocumentosController(DocumentoService documentoService,
                                ProductoCatalogoService catalogoService,
                                ProductoParqueService parqueService) {
        this.documentoService = documentoService;
        this.catalogoService = catalogoService;
        this.parqueService = parqueService;
    }

    // =============================
    // LISTAR INGRESOS / SALIDAS
    // =============================
    @GetMapping("/ingresos/list")
    public String listarIngresos(Model model) {
        model.addAttribute("items", documentoService.listarIngresos());
        return "documentos/ingresos/list";
    }

    @GetMapping("/salidas/list")
    public String listarSalidas(Model model) {
        model.addAttribute("items", documentoService.listarSalidas());
        return "documentos/salidas/list";
    }

    // =============================
    // FORMULARIOS
    // =============================
    @GetMapping("/ingresos/crear")
    public String crearIngreso(Model model, HttpSession session) {

        model.addAttribute("numero", documentoService.generarNumeroIngreso());
        model.addAttribute("productos", catalogoService.listarActivos());
        model.addAttribute("fechaHoy", LocalDate.now());
        return "documentos/ingresos/form";
    }

    @GetMapping("/salidas/crear")
    public String crearSalida(Model model) {

        model.addAttribute("numero", documentoService.generarNumeroSalida());
        model.addAttribute("productos", parqueService.listarActivosConStock());
        model.addAttribute("fechaHoy", LocalDate.now());
        return "documentos/salidas/form";
    }

    // =============================
    // GUARDAR INGRESO
    // =============================
    @PostMapping("/ingresos/guardar")
    public String guardarIngreso(
            @RequestParam String numeroDocumento,
            @RequestParam String fecha,
            @RequestParam String observacion,
            @RequestParam List<Integer> idProducto,
            @RequestParam List<Integer> cantidad,
            HttpSession session) {

        Documento doc = new Documento();
        doc.setTipoMovimiento("INGRESO");
        doc.setNumeroDocumento(numeroDocumento);
        doc.setFecha(LocalDate.parse(fecha));

        Usuario usuario = new Usuario();
        usuario.setId((Integer) session.getAttribute("usuarioId"));
        doc.setUsuario(usuario);

        doc.setObservacion(observacion);

        List<DocumentoDetalle> detalles = DocumentoMapper.mapDetalles(idProducto, cantidad);

        documentoService.guardarIngreso(doc, detalles);

        return "redirect:/documentos/ingresos/list";
    }

    // =============================
    // GUARDAR SALIDA
    // =============================
    @PostMapping("/salidas/guardar")
    public String guardarSalida(
            @RequestParam String numeroDocumento,
            @RequestParam String fecha,
            @RequestParam String observacion,
            @RequestParam List<Integer> idProducto,
            @RequestParam List<Integer> cantidad,
            HttpSession session,
            Model model) {

        Documento doc = new Documento();
        doc.setTipoMovimiento("SALIDA");
        doc.setNumeroDocumento(numeroDocumento);
        doc.setFecha(LocalDate.parse(fecha));

        Usuario usuario = new Usuario();
        usuario.setId((Integer) session.getAttribute("usuarioId"));
        doc.setUsuario(usuario);

        doc.setObservacion(observacion);

        List<DocumentoDetalle> detalles = DocumentoMapper.mapDetalles(idProducto, cantidad);

        String resultado = documentoService.guardarSalida(doc, detalles);

        if (resultado.equals("NO_STOCK")) {
            model.addAttribute("error", "No hay suficiente stock para uno o más productos.");
            return "redirect:/documentos/salidas/crear";
        }

        return "redirect:/documentos/salidas/list";
    }

    // =============================
    // DETALLES
    // =============================
    @GetMapping("/detalles/{id}")
    public String verDetalles(@PathVariable int id, Model model) {
        model.addAttribute("documento", documentoService.buscarPorId(id));
        model.addAttribute("detalles", documentoService.listarDetalles(id));
        return "documentos/detalles";
    }
}
