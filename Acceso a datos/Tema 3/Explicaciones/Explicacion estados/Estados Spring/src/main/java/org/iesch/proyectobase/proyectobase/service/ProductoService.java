package org.iesch.proyectobase.proyectobase.service;

import org.iesch.proyectobase.proyectobase.modelo.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class ProductoService {
    @Autowired
    Map<Long, Producto> producto;
    @Autowired
    Map<Long,Producto> productos;
    public Producto getOne(Long id){
        return productos.get(id);
    }
    public Producto addOne(Producto producto){
        Long maxkey = Collections.max(productos.keySet());
        producto.setId(maxkey+1);
        productos.put(maxkey+1,producto);
        return producto;
    }
    public Producto actualizaUno(Producto producto,Long id){
        Producto productotmp=productos.get(id);
        if (productotmp==null){
            return null;
        }else {
            producto.setId(id);
            productos.put(id,producto);
            return productotmp;
        }
    }

    public Producto borrarUno(Long id) {
        Producto productotmp=productos.get(id);
        if (productotmp==null){
            return null;
        }else {
            productos.remove(id);
            return productotmp;
        }
    }
}
