package org.iesch.ad.Ev2_Hotel.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Entidad que representa un cliente del hotel
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "clientes")
public class Cliente {

    @Id
    private String id;

    @Indexed(unique = true) //Crea un índice en la base de datos para el campo dni y asegura que no se repitan valores y Garantiza la unicidad (unique = true)
    private String dni;

    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private LocalDateTime fechaNacimiento;
    private Direccion direccion;
    private Boolean esVIP;
    private Integer puntosFidelidad;
    private Integer totalReservas;
    private Double gastoTotal;
    private Double deudaPendiente;
    private LocalDateTime fechaRegistro;
}


