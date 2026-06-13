package org.iesch.ade.primerSpring.controlador;

import org.iesch.ade.primerSpring.service.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;
import java.util.*;

@RestController
public class ControllerEj1 {

    private final List<String> lista = new ArrayList<>();

    @Autowired
    Utils utils;


    @GetMapping("/generaLetras")
    public Map<String, String> generaLetras(@RequestParam int longitud) {
        utils.meterletras(lista);
        List<String> contraselista = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(lista.size());
            contraselista.add(lista.get(index));
        }
        String contraseña = String.join("", contraselista);
        return Map.of("contraseña", contraseña);
    }
    @GetMapping("/genera")
    public Map<String, String> generarContraseñanumeros() {
        utils.meterletras(lista);
        int longitud=12;
        List<String> contraselista = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < longitud; i++) {
            int randomnumero = random.nextInt(lista.size());
            int randomcaracter=random.nextInt(0,9);
            if (randomnumero % 2 == 0){
                contraselista.add(lista.get(randomnumero));
            }else {
                contraselista.add(String.valueOf(randomcaracter));
            }
        }
        String contraseña = String.join("", contraselista);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("contraseña", contraseña);
        return respuesta;
    }
}

