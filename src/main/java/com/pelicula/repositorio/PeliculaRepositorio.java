package com.pelicula.repositorio;

import com.pelicula.modelo.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PeliculaRepositorio extends JpaRepository<Pelicula, Long> {
    List<Pelicula> findByGeneroContainingIgnoreCase(String genero);

    List<Pelicula> findByTituloContainingIgnoreCase(String titulo);
}
