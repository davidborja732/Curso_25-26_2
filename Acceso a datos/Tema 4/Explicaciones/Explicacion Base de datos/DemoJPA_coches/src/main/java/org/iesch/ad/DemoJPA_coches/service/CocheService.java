package org.iesch.ad.DemoJPA_coches.service;

import org.iesch.ad.DemoJPA_coches.modelo.Coche;
import org.iesch.ad.DemoJPA_coches.repositorio.CocheRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CocheService {
    @Autowired
    CocheRepositorio cocheRepositorio;
    public Coche guardar(Coche coche) {
        return cocheRepositorio.save(coche);
    }

    public List<Coche> obtenerTodos() {
        return cocheRepositorio.findAll();
    }

    public Coche obtenerUno(Long id) {
        return cocheRepositorio.findById(id).get();
    }

    public Coche actualizar(Long id, Coche coche) {
        Optional<Coche> cocheenBD=cocheRepositorio.findById(id);
        if (cocheenBD.isEmpty()){
            return null;
        }else {
            coche.setId(id);
            return cocheRepositorio.save(coche);
        }
    }

    public Coche borrar(Long id) {
        Coche coche=cocheRepositorio.findById(id).get();
        cocheRepositorio.deleteById(id);
        return coche;
    }

    public List<Coche> obtenerUnoPorColor(String color) {
        return cocheRepositorio.findByColor(color);
    }

    public List<Coche> obtenerUnoPorColorYMarca(String color, String marca) {
        return cocheRepositorio.findByColorAndMarca(color,marca);
    }
    public List<Coche> obtenerUnoPorColorYMarcaYPotenciaMenor(String color, String marca,int potencia) {
        return cocheRepositorio.findByColorAndMarcaAndPotenciaLessThan(color,marca,potencia);
    }
}
