package org.iesch.ad.Ev1_Ej3.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String nombre;
    @Basic
    private String direccion;

    @OneToMany(mappedBy = "editorial", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Libro> libros = new ArrayList<>();
}
