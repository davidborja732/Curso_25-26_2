package Controller;

import modelo.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.GuardaDatos;

@RestController
public class NombreController {
    @Autowired
    GuardaDatos guardaDatos;
    @PostMapping("/persona")
    public String guarda(@RequestBody Persona persona){
        System.out.println(persona);
        return guardaDatos.guarda(persona);
    }
}
