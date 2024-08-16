package com.pelicula.servicio;

import com.pelicula.modelo.Pelicula;
import com.pelicula.repositorio.PeliculaRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PeliculaServicioTest {
    @Mock
    private PeliculaRepositorio peliculaRepositorio;

    @InjectMocks
    private PeliculaServicio peliculaServicio;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerListaDePeliculasSinTitulo() {
        List<Pelicula> peliculas = new ArrayList<>();
        peliculas.add(new Pelicula("Inception", "Descripción de Inception", "Ciencia Ficción"));
        peliculas.add(new Pelicula("The Shawshank Redemption", "Descripción de Shawshank", "Drama"));

        when(peliculaRepositorio.findAll()).thenReturn(peliculas);

        List<Pelicula> resultado = peliculaServicio.obtenerListaDePeliculas(null);

        assertEquals(2, resultado.size());
        verify(peliculaRepositorio, times(1)).findAll();
    }

/*    @Test
    public void testObtenerListaDePeliculasConTitulo() {
        List<Pelicula> peliculas = new ArrayList<>();
        peliculas.add(new Pelicula("Inception", "Descripción de Inception", "Ciencia Ficción"));

        when(peliculaRepositorio.findByTituloContainingIgnoreCase("Inception")).thenReturn(peliculas);

        List<Pelicula> resultado = peliculaServicio.obtenerListaDePeliculas("Inception");

        assertEquals(1, resultado.size());
        verify(peliculaRepositorio, times(1)).findByTituloContainingIgnoreCase("Inception");
    }

    @Test
    public void testObtenerPeliculaPorId() {
        Pelicula pelicula = new Pelicula("Inception", "Descripción de Inception", "Ciencia Ficción");
        when(peliculaRepositorio.findById(1L)).thenReturn(Optional.of(pelicula));

        Optional<Pelicula> resultado = peliculaServicio.obtenerPeliculaPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Inception", resultado.get().getTitulo());
        verify(peliculaRepositorio, times(1)).findById(1L);
    }

    @Test
    public void testCrearPelicula() {
        Pelicula pelicula = new Pelicula("Inception", "Descripción de Inception", "Ciencia Ficción");
        when(peliculaRepositorio.save(any(Pelicula.class))).thenReturn(pelicula);

        Pelicula resultado = peliculaServicio.crearPelicula(pelicula);

        assertEquals("Inception", resultado.getTitulo());
        verify(peliculaRepositorio, times(1)).save(any(Pelicula.class));
    }

    @Test
    public void testActualizarPeliculaExistente() {
        Pelicula peliculaExistente = new Pelicula("Inception", "Descripción de Inception", "Ciencia Ficción");
        Pelicula peliculaNueva = new Pelicula("Inception Updated", "Descripción Actualizada", "Ciencia Ficción");

        when(peliculaRepositorio.findById(1L)).thenReturn(Optional.of(peliculaExistente));
        when(peliculaRepositorio.save(any(Pelicula.class))).thenReturn(peliculaExistente);

        Optional<Pelicula> resultado = peliculaServicio.actualizarPelicula(1L, peliculaNueva);

        assertTrue(resultado.isPresent());
        assertEquals("Inception Updated", resultado.get().getTitulo());
        verify(peliculaRepositorio, times(1)).findById(1L);
        verify(peliculaRepositorio, times(1)).save(any(Pelicula.class));
    }*/

    @Test
    public void testActualizarPeliculaNoExistente() {
        Pelicula peliculaNueva = new Pelicula("Inception Updated", "Descripción Actualizada", "Ciencia Ficción");

        when(peliculaRepositorio.findById(1L)).thenReturn(Optional.empty());

        Optional<Pelicula> resultado = peliculaServicio.actualizarPelicula(1L, peliculaNueva);

        assertFalse(resultado.isPresent());
        verify(peliculaRepositorio, times(1)).findById(1L);
        verify(peliculaRepositorio, times(0)).save(any(Pelicula.class));
    }

    @Test
    public void testEliminarPelicula() {
        doNothing().when(peliculaRepositorio).deleteById(1L);

        peliculaServicio.eliminarPelicula(1L);

        verify(peliculaRepositorio, times(1)).deleteById(1L);
    }

    @Test
    public void testEliminarTodasLasPeliculas() {
        doNothing().when(peliculaRepositorio).deleteAll();

        peliculaServicio.eliminarTodasLasPeliculas();

        verify(peliculaRepositorio, times(1)).deleteAll();
    }

    @Test
    public void testBuscarPeliculaPorGenero() {
        List<Pelicula> peliculas = new ArrayList<>();
        peliculas.add(new Pelicula("Inception", "Descripción de Inception", "Ciencia Ficción"));

        when(peliculaRepositorio.findByGeneroContainingIgnoreCase("Ciencia Ficción")).thenReturn(peliculas);

        List<Pelicula> resultado = peliculaServicio.buscarPeliculaPorGenero("Ciencia Ficción");

        assertEquals(1, resultado.size());
        verify(peliculaRepositorio, times(1)).findByGeneroContainingIgnoreCase("Ciencia Ficción");
    }
}

