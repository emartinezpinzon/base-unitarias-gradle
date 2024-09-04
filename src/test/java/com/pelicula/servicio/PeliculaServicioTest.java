package com.pelicula.servicio;

import com.pelicula.repositorio.PeliculaRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



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

    }

    @Test
    public void testObtenerListaDePeliculasConTitulo() {

    }

    @Test
    public void testObtenerPeliculaPorId() {

    }

    @Test
    public void testCrearPelicula() {

    }

    @Test
    public void testActualizarPeliculaExistente() {

    }

    @Test
    public void testActualizarPeliculaNoExistente() {

    }

    @Test
    public void testEliminarPelicula() {

    }

    @Test
    public void testEliminarTodasLasPeliculas() {

    }

    @Test
    public void testBuscarPeliculaPorGenero() {

    }
}

