package org.iesch.ad.DocumentosReferenciados.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.iesch.ad.DocumentosReferenciados.modelo.AutoresREF;
import org.iesch.ad.DocumentosReferenciados.repositorio.AutoresRefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Autores",description = "Api de Autores referenciados")
@RestController
@RequestMapping("api/autores")
public class AutorREFController {
    @Autowired
    AutoresRefRepository autoresRefRepository;
    // CRUD
    // Obtener todos
    // GET /api/autores
    @Operation(description = "Devuelve todos los autores")
    @ApiResponses()
    @ApiResponse(responseCode = "200",description = "Ha devuelto todos bien")
    @GetMapping
    public ResponseEntity<List<AutoresREF>> ObtenerTodos(){
        return ResponseEntity.ok(autoresRefRepository.findAll());
    }
    // Obtener uno
    // GET /api/autores/{id}
    @Operation(description = "Devuelve un libro recibiendo un id como parametro")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ha devuelto uno bien"),
            @ApiResponse(responseCode = "404", description = "No se ha encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<AutoresREF>> ObtenerUno(@PathVariable String id){
        return ResponseEntity.ok(autoresRefRepository.findById(id));
    }
    // Añadir uno
    // POST /api/autores/
    @PostMapping()
    public ResponseEntity<Optional<AutoresREF>> CrearAutor(@RequestBody AutoresREF autor){
        AutoresREF autoresREF=autoresRefRepository.save(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(Optional.of(autoresREF));
    }
    // Modificar uno
    // PUT /api/autores/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Optional<AutoresREF>> ModiAutor(@PathVariable String id,@RequestBody AutoresREF autor){
        if (autoresRefRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        autor.setId(id);
        AutoresREF autoresREFsave=autoresRefRepository.save(autor);
        return ResponseEntity.status(HttpStatus.CREATED).body(Optional.of(autoresREFsave));
    }
    // Eliminar uno
    // Delete /api/autores/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> EliAutor(@PathVariable String id){
        if (!autoresRefRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        autoresRefRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    // Obtener por nombre
    // GET /api/autores/search/nombre?nombreusu=?
    @GetMapping("/search/nombre")
    public ResponseEntity<List<AutoresREF>> ObtenerNombre(@PathVariable String nombreusu){
        return ResponseEntity.ok(autoresRefRepository.findByNombreContainingIgnoreCase(nombreusu));
    }
    // Obtener por nacionalidad
    // GET /api/autores/nacionalidad?pais=?
    @GetMapping("/search/nacionalidad/{pais}")
    public ResponseEntity<List<AutoresREF>> ObtenerNacio(@PathVariable String pais){
        return ResponseEntity.ok(autoresRefRepository.findByNacionalidadContainingIgnoreCase(pais));
    }
    // Obtener por nacionalidades
    // POST /api/autores/search/{nacio}
    @PostMapping("/search/nacionalidad")
    public ResponseEntity<List<AutoresREF>> ObtenerNaciones(@RequestBody List<String> nacionalidades){
        return ResponseEntity.ok(autoresRefRepository.findByNacionalidadIn(nacionalidades));
    }
    // Obtener por IdAutor
    // GET /api/autores/id
    @GetMapping("/search/{id}")
    public ResponseEntity<List<AutoresREF>> ObtenerIdAutor(@PathVariable String id){
        return ResponseEntity.ok(autoresRefRepository.findByNacionalidadContainingIgnoreCase(id));
    }




}
