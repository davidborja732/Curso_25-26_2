package org.iesch.dgt.Practica_DGT.service;

import org.iesch.dgt.Practica_DGT.modelos.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoService {

    Vehiculo altaVehiculo(Vehiculo vehiculo);

    Optional<Vehiculo> obtenerPorMatricula(String matricula);

    List<Vehiculo> listarTodos(int page, int size);


}

