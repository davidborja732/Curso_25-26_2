package com.david.hotelapi.service;

import com.david.hotelapi.exception.NotFoundException;
import com.david.hotelapi.model.Cliente;
import com.david.hotelapi.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findByDni(String dni) {
        return clienteRepository.findById(dni)
                .orElseThrow(() -> new NotFoundException("El cliente no existe"));
    }

    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void delete(String dni) {
        if (!clienteRepository.existsById(dni)) {
            throw new NotFoundException("El cliente no existe");
        }
        clienteRepository.deleteById(dni);
    }
}
