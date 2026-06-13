package org.iesch.ade.primerSpring.controlador;

import jakarta.annotation.PostConstruct;
import org.iesch.ade.primerSpring.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ControllerEj2 {

    @Autowired
    Utils utils;

    @GetMapping("/conDistancia/{km}")
    public Map<String, Double> generadistancia(@PathVariable int km){
        return Map.of("En millas es", km/1.609);
    }
    @GetMapping("/conTemp/{temp}")
    public Map<String, Double> generatemperatura(@PathVariable double temp){
        return Map.of("En Grados Celsius es ", (temp*9/5)+32);
    }
}

