package org.iesch.proyectobase.proyectobase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Myconfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
