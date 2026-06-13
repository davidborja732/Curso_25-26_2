package com.david.hotelapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                // ============================================================
                // 🔓 OPCIÓN ACTIVA: PERMITIR ACCESO A CUALQUIER ORIGEN
                // ============================================================
                registry.addMapping("/**")
                        .allowedOrigins("*")   // CUALQUIERA puede conectarse
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                        .allowedHeaders("*");

                // ============================================================
                // 🔐 OPCIÓN RESTRICTIVA (DESACTIVADA, SOLO EJEMPLO)
                // ============================================================
                /*
                registry.addMapping("/**")
                        .allowedOrigins(
                                "http://localhost:3000",      // React
                                "http://localhost:54870",     // Flutter Web
                                "https://miapp.com"           // Producción
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                        .allowedHeaders("*");
                */
            }
        };
    }
}
