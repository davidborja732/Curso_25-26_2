package org.iesch.ade.primerSpring.controlador;

import org.iesch.ade.primerSpring.modelo.Producto;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductosController {
    @PostMapping("/producto")
    public void recibeproducto(@RequestBody Producto producto){
        System.out.println(producto);
    }
    @GetMapping("/producto")
    public Producto dameProducto(){
        return new Producto(12,"Mantequilla",2.45f);
    }
}
