package com.pelicula.controlador;

import com.pelicula.modelo.Pelicula;
import com.pelicula.servicio.PeliculaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class PeliculaControlador {

    @Autowired
    private PeliculaServicio peliculaServicio;


    @GetMapping("/pelicula/{id}")
    public ResponseEntity<Pelicula> obtenerPeliculaPorId(@PathVariable("id") long id) {
        Optional<Pelicula> tutorialData = peliculaServicio.obtenerPeliculaPorId(id);

        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pelicula")
    public ResponseEntity<List<Pelicula>> obtenerListaDePelicula(@RequestParam(required = false) String titulo) {
        try {
            List<Pelicula> peliculas = peliculaServicio.obtenerListaDePeliculas(titulo);
            return new ResponseEntity<>(peliculas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pelicula/genero/{genero}")
    public ResponseEntity<List<Pelicula>> buscarPorGenero(@PathVariable("genero") String genero) {
        try {
            List<Pelicula> peliculas = peliculaServicio.buscarPeliculaPorGenero(genero);

            if (peliculas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(peliculas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pelicula")
    public ResponseEntity<Pelicula> crearPelicula(@RequestBody Pelicula peliculaNuevo) {
        try {
            Pelicula peliculaCreado = peliculaServicio.crearPelicula(peliculaNuevo);
            return new ResponseEntity<>(peliculaCreado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/pelicula/{id}")
    public ResponseEntity<Pelicula> actualizarPelicula(@PathVariable("id") long id, @RequestBody Pelicula pelicula) {
        Optional<Pelicula> tutorialData = peliculaServicio.actualizarPelicula(id, pelicula);

        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/pelicula/{id}")
    public ResponseEntity<HttpStatus> eliminarPelicula(@PathVariable("id") long id) {
        try {
            peliculaServicio.eliminarPelicula(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/pelicula")
    public ResponseEntity<HttpStatus> eliminarTodosLasPeliculas() {
        try {
            peliculaServicio.eliminarTodasLasPeliculas();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
