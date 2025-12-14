package org.iesch.proyectobase.proyectobase.config;

import org.iesch.proyectobase.proyectobase.modelo.Usuario;
import org.iesch.proyectobase.proyectobase.modelo.Producto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Configuracion {
    @Bean
    public Map<Long,Producto> inicializa(){
        Map<Long,Producto> productos=new HashMap<>();
        productos.put(1L,Producto.builder().id(1).name("Fairy").categoria("limpieza").price(5.25).descripcion("producto 1").stock(15).build());
        productos.put(2L,Producto.builder().id(2).name("Escoba").categoria("limpieza").price(2.25).descripcion("producto 2").stock(150).build());
        productos.put(3L,Producto.builder().id(3).name("Coche").categoria("Motor").price(1500).descripcion("producto 3").stock(19).build());
        productos.put(4L,Producto.builder().id(4).name("Raton").categoria("Informatica").price(15.50).descripcion("producto 4").stock(5).build());
        productos.put(5L,Producto.builder().id(5).name("Altavoz").categoria("Informatica").price(25.76).descripcion("producto 5").stock(23).build());
        productos.put(6L,Producto.builder().id(6).name("Monitor").categoria("Informatica").price(250.80).descripcion("producto 6").stock(230).build());
        productos.put(7L,Producto.builder().id(7).name("Teclado").categoria("Informatica").price(45.80).descripcion("producto 7").stock(132).build());
        productos.put(8L,Producto.builder().id(8).name("Pendrive").categoria("Informatica").price(12.98).descripcion("producto 8").stock(23).build());
        return productos;
    }
    @Bean
    public Map<Long, Usuario> initpersona(){
        Map<Long, Usuario> usuarios=new HashMap<>();
        usuarios.put(1L, Usuario.builder().id(1).nombre("David").apellido("Borja").password("Admin1234").build());
        return usuarios;
    }
}
