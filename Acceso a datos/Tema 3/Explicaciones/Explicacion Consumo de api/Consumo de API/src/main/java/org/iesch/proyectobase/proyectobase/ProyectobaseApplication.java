package org.iesch.proyectobase.proyectobase;

import org.iesch.proyectobase.proyectobase.model.Fact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProyectobaseApplication{ //implements CommandLineRunner {
	@Autowired
	RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ProyectobaseApplication.class, args);
	}

	/*@Override
	public void run(String... args) throws Exception {
		String url="https://catfact.ninja/fact";
		//LLamo 200 veces a la url
		for (int i = 0; i < 200; i++) {
			Fact caracteristica=restTemplate.getForObject(url, Fact.class);
			System.out.println(caracteristica);
		}

	}*/
}
