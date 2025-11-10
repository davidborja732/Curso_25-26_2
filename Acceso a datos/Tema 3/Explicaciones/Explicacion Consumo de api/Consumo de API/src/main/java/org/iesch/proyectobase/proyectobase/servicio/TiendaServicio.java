package org.iesch.proyectobase.proyectobase.servicio;

import org.iesch.proyectobase.proyectobase.model.Fact;
import org.iesch.proyectobase.proyectobase.model.MiRespuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class TiendaServicio {
    @Autowired
    RestTemplate restTemplate;
    String url="https://catfact.ninja/fact";
    public MiRespuesta obtener(int veces) {
        MiRespuesta miRespuesta=new MiRespuesta();
        for (int i = 0; i < veces; i++) {
            Fact caracteristica=restTemplate.getForObject(url, Fact.class);
            miRespuesta.addone(caracteristica);
        }
        return miRespuesta;
    }
}
