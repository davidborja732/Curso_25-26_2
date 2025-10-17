package org.iesch.ade.primerSpring.service;

import org.springframework.stereotype.Service;

@Service
public class Utils {
    public String transformarmayuscula(String texto){
        return texto.toUpperCase();
    }
}
