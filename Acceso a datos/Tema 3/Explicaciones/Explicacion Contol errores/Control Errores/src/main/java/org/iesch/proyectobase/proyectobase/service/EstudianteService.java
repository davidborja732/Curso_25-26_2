package org.iesch.proyectobase.proyectobase.service;

import jakarta.validation.Valid;
import org.iesch.proyectobase.proyectobase.excepciones.DatosInvalidosException;
import org.iesch.proyectobase.proyectobase.excepciones.EmailDuplicadoException;
import org.iesch.proyectobase.proyectobase.excepciones.EstudianteNoEncontradoExcepcion;
import org.iesch.proyectobase.proyectobase.modelo.Estudiante;
import org.iesch.proyectobase.proyectobase.modelo.dto.EstudianteRequestDTO;
import org.iesch.proyectobase.proyectobase.modelo.dto.EstudianteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class EstudianteService {
    @Autowired
    Map<Long,Estudiante> estudiantes;
    public List<EstudianteResponseDTO> obtenertodos() {
        List<Estudiante> lista= new ArrayList<>(estudiantes.values());
        return lista.stream().map(EstudianteResponseDTO::new).toList();
    }

    public EstudianteResponseDTO obtenerPorId(long id) {
        Estudiante estudiante = estudiantes.get(id);
        if (!(estudiante ==null)){
            return new EstudianteResponseDTO(estudiante);
        }else {
            throw new EstudianteNoEncontradoExcepcion(id);
        }
    }

    public EstudianteResponseDTO crear(@Valid EstudianteRequestDTO estudianteRequestDTO) {
        // validamos que no exista ya ese email
        boolean existeEmail=estudiantes.values().stream().anyMatch(estudiante -> estudiante.getEmail().equalsIgnoreCase(estudianteRequestDTO.getEmail()));
        if (existeEmail){
            throw new EmailDuplicadoException(estudianteRequestDTO.getEmail());
        }
        // crear usuario nuevo
        Long maxkey=estudiantes.keySet().stream().max(Long::compareTo).orElse(1L);
        Estudiante estudiante=new Estudiante(maxkey+1, estudianteRequestDTO.getNombre(),estudianteRequestDTO.getApellidos(), estudianteRequestDTO.getEmail(), estudianteRequestDTO.getEdad(), estudianteRequestDTO.getCiclo());
        estudiantes.put(maxkey,estudiante);
        return new EstudianteResponseDTO(estudiante);
    }

    public EstudianteResponseDTO actualizar(Long id,@Valid EstudianteRequestDTO estudianteRequestDTO) {
        Estudiante estudiante=estudiantes.get(id);
        if (estudiante==null){
            throw new EstudianteNoEncontradoExcepcion(id);
        }
        boolean existeEmail=estudiantes.values().stream().anyMatch(estudiantecom -> estudiantecom.getEmail().equalsIgnoreCase(estudianteRequestDTO.getEmail()));
        if (existeEmail){
            throw new EmailDuplicadoException(estudianteRequestDTO.getEmail());
        }
        // actualizo los datos
        estudiante.setNombre(estudianteRequestDTO.getNombre());
        estudiante.setApellidos(estudianteRequestDTO.getApellidos());
        estudiante.setEmail(estudianteRequestDTO.getEmail());
        estudiante.setEdad(estudianteRequestDTO.getEdad());
        estudiante.setCiclo(estudianteRequestDTO.getCiclo());
        return new EstudianteResponseDTO(estudiante);
    }

    public void eliminar(Long id) {
        Estudiante estudiante=estudiantes.get(id);
        if (estudiante==null){
            throw new EstudianteNoEncontradoExcepcion(id);
        }
        estudiantes.remove(id);

    }

    public List<EstudianteResponseDTO> obtenerporCiclo(String ciclo) {
        // Validamos que el ciclo sea valido
        if (!ciclo.matches("(?)DAM|DAW|ASIR|SMR")){
            throw new DatosInvalidosException("Ciclo no valido");
        }
        List<EstudianteResponseDTO> estudiantesciclo=estudiantes.values().stream().filter(estudiante -> estudiante.getCiclo().equalsIgnoreCase(ciclo)).map(EstudianteResponseDTO::new).toList();
        if (estudiantesciclo.isEmpty()){
            throw new EstudianteNoEncontradoExcepcion(ciclo);
        }
        return estudiantesciclo;
    }
}
