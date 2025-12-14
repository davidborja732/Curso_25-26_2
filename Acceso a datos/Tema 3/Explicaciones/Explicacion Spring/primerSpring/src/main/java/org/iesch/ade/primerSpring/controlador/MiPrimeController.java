package org.iesch.ade.primerSpring.controlador;

import org.iesch.ade.primerSpring.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MiPrimeController {
    @Value("${nombre}") String nombre;
    @Autowired
    Utils utils;
    @GetMapping("/saludo")
    public String saludo(){
        return "Hola "+nombre;
    }
    @GetMapping("/saluda/{nombre}")
    public Map<String,String> saludarA(@PathVariable String nombre){
        return Map.of("saludo","Hola "+nombre);
    }
    @GetMapping("/buscar")
    public Map<String,String> buscar(@RequestParam(required = true,name="nombre") String nombre){
        return Map.of("saludo","Buscamos a "+utils.transformarmayuscula(nombre));
    }
}
