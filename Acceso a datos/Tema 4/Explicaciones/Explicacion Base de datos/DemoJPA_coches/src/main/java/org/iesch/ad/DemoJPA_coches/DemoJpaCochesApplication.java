package org.iesch.ad.DemoJPA_coches;

import jakarta.transaction.Transactional;
import org.iesch.ad.DemoJPA_coches.modelo.Persona;
import org.iesch.ad.DemoJPA_coches.repositorio.CocheRepositorio;
import org.iesch.ad.DemoJPA_coches.repositorio.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoJpaCochesApplication implements CommandLineRunner {
	@Autowired
	PersonaRepositorio personaRepositorio;
	@Autowired
	CocheRepositorio cocheRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(DemoJpaCochesApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		/*System.out.println("Muestra persona 2");
		Persona personasmtp=personaRepositorio.findById(2L).get();
		System.out.println(personasmtp.getNombre()+" "+personasmtp.getApellido());
		System.out.println("Coches de "+personasmtp.getNombre());
		personasmtp.getCoches().stream().forEach(System.out::println);
		System.out.println("Muestra coche 1");
		System.out.println(cocheRepositorio.findById(1L));*/
		Persona personasmtp=personaRepositorio.findById(2L).get();
		System.out.println(personasmtp.getNombre());
		System.out.println(personasmtp.getCoches());

	}
}
