package org.iesch.proyectobase.proyectobase;

import org.iesch.proyectobase.proyectobase.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Map;

@SpringBootApplication
public class ProyectobaseApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProyectobaseApplication.class, args);
	}

	@Autowired
	Map<Long,Producto> productos;
	@Override
	public void run(String... args) throws Exception {
		productos.forEach((aLong, producto) -> System.out.println(aLong+", "+producto));
	}
}
