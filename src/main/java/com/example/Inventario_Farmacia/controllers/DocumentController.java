package com.example.Inventario_Farmacia.controllers;

import com.example.Inventario_Farmacia.model.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/documentos")
public class DocumentController {

    private List<Document> documents = new ArrayList<>(Arrays.asList(
            new Document(1, "Ingreso", "ING-001", LocalDate.parse("2025-10-07"), 2, "Ingreso de medicamentos al inventario"),
            new Document(2, "Salida",  "SAL-001", LocalDate.parse("2025-10-08"), 3, "Salida por venta al cliente"),
            new Document(3, "Ingreso", "ING-002", LocalDate.parse("2025-10-10"), 2, "Reposición de stock por proveedor"),
            new Document(4, "Salida",  "SAL-002", LocalDate.parse("2025-10-12"), 4, "Salida por ajuste de inventario")
    ));


    @GetMapping
    public ResponseEntity<List<Document>> getDocuments(){
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/{numero_documento}")
    public ResponseEntity<?> getNumeroDocumento(@PathVariable String numero_documento){
        for (Document d : documents) {
            if (d.getNumero_documento().equalsIgnoreCase(numero_documento)) {
                return ResponseEntity.ok(d);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Documento no encontrado con número de documento:" + numero_documento);
    }

    @PostMapping
    public ResponseEntity<?> postDocumento(@RequestBody Document document){
        documents.add(document);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{numero_documento}")
                .buildAndExpand(document.getNumero_documento())
                .toUri();

        // return ResponseEntity.created(location).build();
        return ResponseEntity.created(location).body(document); //Devuelve con la info
        // return ResponseEntity.status(HttpStatus.CREATED).body("Documento creado exitosamente: " + document.getNumero_documento());
    }

    // ACTUALIZA TODOS LOS CAMPOS
    @PutMapping
    public ResponseEntity<?> putDocumento(@RequestBody Document document){
        for (Document d : documents) {
            if (d.getID() == document.getID()) {
                d.setObservacion(document.getObservacion());
                // AUNQUE NO ESTEN IGUAL MODIFICA

                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    //ACTUALIZA SOLO UN CAMPO
    @PatchMapping
    public ResponseEntity<?> patchDocumento(@RequestBody Document document){
        for (Document d : documents) {
            if (d.getID() == document.getID()) {
                if (document.getObservacion() != null) {
                    d.setObservacion(document.getObservacion());
                }

                /* if (document.) {

                }*/

                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDocumento(@PathVariable int id){
        for (Document d : documents) {
            if (d.getID() == id) {
                documents.remove(d);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Documento eliminado correctamente:" + id);
            }
        }
        return ResponseEntity.notFound().build();
    }

}
