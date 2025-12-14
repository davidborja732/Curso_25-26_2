package org.iesch.ad.NconsultasYmas;

import org.iesch.ad.NconsultasYmas.servicio.CriteriaDemoService;
import org.iesch.ad.NconsultasYmas.servicio.JdbcService;
import org.iesch.ad.NconsultasYmas.servicio.Nmas1DemoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class NconsultasYmasApplication implements CommandLineRunner {

	@Autowired
	Nmas1DemoServicio nmas1DemoServicio;
	@Autowired
	CriteriaDemoService criteriaDemoService;
	@Autowired
	JdbcService jdbcTemplateExampleDemo;

	public static void main(String[] args) {
		SpringApplication.run(NconsultasYmasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("\n"+"-".repeat(30));
		System.out.println("n+1");
		System.out.println("\n"+"-".repeat(30));
		nmas1DemoServicio.mostrarProblemaNMas1();
		System.out.println("\n"+"-".repeat(30));
		System.out.println("n+1 Solucion");
		System.out.println("\n"+"-".repeat(30));
		nmas1DemoServicio.solucionFetchJoin();
		System.out.println("\n\n\n"+"--------".repeat(20));
		criteriaDemoService.ejemploCOnsultaSimple();
		criteriaDemoService.ejemploCOnsultaConWHERE();
		System.out.println("\n"+"-".repeat(30));
		criteriaDemoService.ConsultaConLike();
		criteriaDemoService.ConsultaConJoin();
		System.out.println("\n"+"-".repeat(30));
		criteriaDemoService.ConsultaConMultiplesCondiciones();
		System.out.println("\n"+"-".repeat(30));
		//criteriaDemoService.ConsultaDinamica("Argentina",12.00,1912); Mirar en casa
		System.out.println("\n"+"-".repeat(30));
		criteriaDemoService.ConsultaConOrderBy();
		System.out.println("\n"+"-".repeat(30));
		criteriaDemoService.ConsultaConAgregaciones();
		System.out.println("\n"+"-".repeat(30));
		System.out.println("JDBC-TEMPLATE");
		jdbcTemplateExampleDemo.demoCosultaJDBCTemplate();
	}
}
