package com.pelicula.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "peliculas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "genero")
    private String genero;

    public Pelicula(String titulo, String descripcion, String genero) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.genero = genero;
    }

}
