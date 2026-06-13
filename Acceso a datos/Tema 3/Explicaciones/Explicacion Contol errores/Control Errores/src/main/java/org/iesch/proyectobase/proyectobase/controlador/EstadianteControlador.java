package org.iesch.proyectobase.proyectobase.controlador;

import jakarta.validation.Valid;
import org.iesch.proyectobase.proyectobase.modelo.dto.EstudianteRequestDTO;
import org.iesch.proyectobase.proyectobase.modelo.dto.EstudianteResponseDTO;
import org.iesch.proyectobase.proyectobase.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstadianteControlador {
    @Autowired
    EstudianteService estudianteService;

    @GetMapping
    //@CrossOrigin(origins="http://localhost")
    public ResponseEntity<List<EstudianteResponseDTO>> obtenerTodos() {
        List<EstudianteResponseDTO> studiantes = estudianteService.obtenertodos();
        return ResponseEntity.ok(studiantes);
    }
    /*public ResponseEntity<?> obtenerTodos(){
        List<Estudiante> studiantes= estudianteService.obtenertodos();
        return ResponseEntity.ok(studiantes);*/ //Otra forma de hacerlo
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteResponseDTO> obteneruno(@PathVariable long id){
        EstudianteResponseDTO estudianteResponseDTO=estudianteService.obtenerPorId(id);
        return ResponseEntity.ok(estudianteResponseDTO);
    }
    /**
     * GET/api/estudiantes/id
     * Obtengo estudiante por id y lanzo excepciones
     */
    @PostMapping
    public ResponseEntity<EstudianteResponseDTO> crearuno(@Valid @RequestBody EstudianteRequestDTO estudianteRequestDTO) {
        EstudianteResponseDTO nuevoestudiante=estudianteService.crear(estudianteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoestudiante);
    }
    /**
     *POST/api/estudiantes/
     * Meto un nuevo estudiante que puede lanzar excepciones
     */
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteResponseDTO> actualizar(@PathVariable Long id,@Valid @RequestBody EstudianteRequestDTO estudianteRequestDTO){
        EstudianteResponseDTO estudianteActualizado=estudianteService.actualizar(id,estudianteRequestDTO);
        return ResponseEntity.ok(estudianteActualizado);
    }
    /**
     * Modifico el estudiante que puede
     * lanzar excepciones
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        estudianteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    /**
     * Elimino el estudiante que puede
     * lanzar excepciones
     */
    @GetMapping("/{ciclo}")
    public ResponseEntity<List<EstudianteResponseDTO>> obtenerPorCicloGet(@PathVariable String ciclo) {
        List<EstudianteResponseDTO> studiantes = estudianteService.obtenerporCiclo(ciclo);
        return ResponseEntity.ok(studiantes);
    }
    // Obtener estudiantes por su ciclo formativo GET/api/estudiantes/ciclo/{ciclo}

}

