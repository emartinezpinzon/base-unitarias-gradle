package com.pelicula.servicio;

import com.pelicula.modelo.Pelicula;
import com.pelicula.repositorio.PeliculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServicio {

    @Autowired
    private PeliculaRepositorio peliculaRepositorio;

    public List<Pelicula> obtenerListaDePeliculas(String titulo) {
        List<Pelicula> peliculas = new ArrayList<>();

        if (titulo == null) {
            peliculaRepositorio.findAll().forEach(peliculas::add);
        } else {
            peliculaRepositorio.findByTituloContainingIgnoreCase(titulo).forEach(peliculas::add);
        }

        return peliculas;
    }

    public Optional<Pelicula> obtenerPeliculaPorId(long id) {
        return peliculaRepositorio.findById(id);
    }

    public Pelicula crearPelicula(Pelicula pelicula) {
        return peliculaRepositorio.save(new Pelicula(pelicula.getTitulo(), pelicula.getDescripcion(), pelicula.getGenero()));
    }

    public Optional<Pelicula> actualizarPelicula(long id, Pelicula peliculaNuevo) {
        Optional<Pelicula> tutorialActual = peliculaRepositorio.findById(id);

        if (tutorialActual.isPresent()) {
            Pelicula pelicula = tutorialActual.get();
            pelicula.setTitulo(peliculaNuevo.getTitulo());
            pelicula.setDescripcion(peliculaNuevo.getDescripcion());
            pelicula.setGenero(peliculaNuevo.getGenero());
            return Optional.of(peliculaRepositorio.save(pelicula));
        } else {
            return Optional.empty();
        }
    }

    public void eliminarPelicula(long id) {
        peliculaRepositorio.deleteById(id);
    }

    public void eliminarTodasLasPeliculas() {
        peliculaRepositorio.deleteAll();
    }

    public List<Pelicula> buscarPeliculaPorGenero(String genero) {
        return peliculaRepositorio.findByGeneroContainingIgnoreCase(genero);
    }

}
