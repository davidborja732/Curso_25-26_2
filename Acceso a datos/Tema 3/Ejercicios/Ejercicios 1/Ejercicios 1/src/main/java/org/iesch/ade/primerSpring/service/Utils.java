package org.iesch.ade.primerSpring.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Utils {
    public String transformarmayuscula(String texto){
        return texto.toUpperCase();
    }
    public void meterletras(List<String> lista) {
        for (int i = 97; i <= 122; i++) {
            lista.add(String.valueOf((char) i));
        }
        for (int i = 97; i <= 122; i++) {
            lista.add(String.valueOf((char) i).toUpperCase());
        }
    }
}
