package org.iesch.ad.NconsultasYmas.servicio;

import org.iesch.ad.NconsultasYmas.modelo.Libro;
import org.iesch.ad.NconsultasYmas.repositorio.JDBCTemplateRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JdbcService {
    @Autowired
    JDBCTemplateRepositorio jdbcTemplateRepositorio;
    public void demoCosultaJDBCTemplate() {
        System.out.println("Buscar libros de autores argentinos");
        List<Libro> librosArgentinos = jdbcTemplateRepositorio.findLibroPorNacionalidadAutor("Argentina");
        librosArgentinos.forEach(libro -> System.out.println(
                libro.getTitulo()
                +" "+libro.getIsbn()
                +" "+libro.getAnioPublicacion()
                +" "+libro.getPrecio()+" euros "
                +" "+libro.getAutor().getNombre()
                +" "+libro.getAutor().getApellido())
        );
        System.out.println("Estadisticas de libros por autor");
        List<Map<String, Object>> estadisticas=jdbcTemplateRepositorio.getEstadisticasAutorLibro();
        estadisticas.forEach(stringObjectMap -> {
            System.out.println(
                    stringObjectMap.get("nombre")+" "+
                    stringObjectMap.get("apellido")+" "+
                    stringObjectMap.get("nacionalidad")+" "
            );
            System.out.println(
                    stringObjectMap.get("total_libros")+" "+
                    stringObjectMap.get("precio_promedio")+" "+
                    stringObjectMap.get("primer_libro")+" "+
                    stringObjectMap.get("ultimo_libro")+" "

            );
        });
    }
}
