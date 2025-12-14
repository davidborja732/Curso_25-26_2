package org.iesch.proyectobase.proyectobase.controller;

import org.iesch.proyectobase.proyectobase.modelo.Producto;
import org.iesch.proyectobase.proyectobase.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class ProductosController {
    private static final Logger logger= LoggerFactory.getLogger(ProductosController.class);
    @Autowired
    Map<Long,Producto> productos;
    @Autowired
    ProductoService productoService;
    @GetMapping("/producto")
    /*public List<Producto> getTodos(){
        return productos;
    }*/
    public ResponseEntity<?> getTodos(){
        if (productos.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(productos);
        }
    }
    @GetMapping("/unproducto/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        logger.debug("Buscando el producto con Id: {}",id);
        Producto producto=productoService.getOne(id);
        if (producto==null){
            logger.warn("Producto con Id {} no encontrado",id);
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(producto);
        }
    }
    @PostMapping("/producto/")
    public ResponseEntity<?> anadeuno(@RequestBody Producto producto){
        logger.info("Creando un nuevo producto: {}",producto);
        productoService.addOne(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(producto);//una forma de hacerlo
        /*URI location= URI.create("/producto/"+producto.getId());//prueba
        return ResponseEntity.created(location).body(producto);*/
    }
    @PutMapping("/producto/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Producto producto,@PathVariable Long id){
        logger.info("Modificando el producto con id {}",id);
        logger.debug("Datos {}",producto.toString());
        Producto productoEntity= productoService.actualizaUno(producto,id);
        if (productoEntity==null){
            logger.warn("Producto con Id {} no encontrado",id);
            return null;
        }else {
            return ResponseEntity.ok(productoEntity);
        }
    }
    @DeleteMapping("/producto/{id}")
    public ResponseEntity<?> borraruno(@PathVariable Long id){
        logger.info("Elmininando producto con ID {}",id);
        Producto productoEntity= productoService.borrarUno(id);
        if (productoEntity==null){

            return ResponseEntity.notFound().build();//404
        }else {
            return ResponseEntity.ok(productoEntity);//204
        }
    }
}

