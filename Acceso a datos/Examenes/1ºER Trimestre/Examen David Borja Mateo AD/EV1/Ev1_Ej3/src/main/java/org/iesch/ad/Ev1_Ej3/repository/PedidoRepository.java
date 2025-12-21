package org.iesch.ad.Ev1_Ej3.repository;

import org.iesch.ad.Ev1_Ej3.model.Cliente;
import org.iesch.ad.Ev1_Ej3.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByFecha(String editorial);

}
