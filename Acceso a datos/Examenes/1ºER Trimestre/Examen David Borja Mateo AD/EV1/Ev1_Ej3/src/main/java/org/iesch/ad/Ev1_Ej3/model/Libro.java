package org.iesch.ad.Ev1_Ej3.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String titulo;
    @Basic
    private Double precio;
    @Basic
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "editorial_id")
    @JsonBackReference
    private Editorial editorial;

    @ManyToMany(mappedBy = "libros")
    @JsonBackReference
    private List<Pedido> pedidos = new ArrayList<>();


}
