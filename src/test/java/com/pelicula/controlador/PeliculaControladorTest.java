package com.pelicula.controlador;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.pelicula.modelo.Pelicula;
import com.pelicula.servicio.PeliculaServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PeliculaControladorTest {

    @Mock
    private PeliculaServicio peliculaServicio;

    @InjectMocks
    private PeliculaControlador peliculaControlador;

    private Pelicula pelicula;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitulo("Test Pelicula");
        pelicula.setDescripcion("Descripcion de prueba");
        pelicula.setGenero("Accion");
    }

    @Test
    public void testObtenerPeliculaPorId() {
        when(peliculaServicio.obtenerPeliculaPorId(anyLong())).thenReturn(Optional.of(pelicula));

        ResponseEntity<Pelicula> response = peliculaControlador.obtenerPeliculaPorId(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pelicula, response.getBody());
    }

    @Test
    public void testObtenerPeliculaPorIdNotFound() {
        when(peliculaServicio.obtenerPeliculaPorId(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Pelicula> response = peliculaControlador.obtenerPeliculaPorId(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testObtenerListaDePelicula() {
        List<Pelicula> peliculas = Arrays.asList(pelicula);
        when(peliculaServicio.obtenerListaDePeliculas(any())).thenReturn(peliculas);

        ResponseEntity<List<Pelicula>> response = peliculaControlador.obtenerListaDePelicula(null);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(peliculas, response.getBody());
    }

    @Test
    public void testBuscarPorGenero() {
        List<Pelicula> peliculas = Arrays.asList(pelicula);
        when(peliculaServicio.buscarPeliculaPorGenero(anyString())).thenReturn(peliculas);

        ResponseEntity<List<Pelicula>> response = peliculaControlador.buscarPorGenero("Accion");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(peliculas, response.getBody());
    }

    @Test
    public void testBuscarPorGeneroNoContent() {
        when(peliculaServicio.buscarPeliculaPorGenero(anyString())).thenReturn(Arrays.asList());

        ResponseEntity<List<Pelicula>> response = peliculaControlador.buscarPorGenero("Accion");

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testCrearPelicula() {
        when(peliculaServicio.crearPelicula(pelicula)).thenReturn(pelicula);

        ResponseEntity<Pelicula> response = peliculaControlador.crearPelicula(pelicula);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(pelicula, response.getBody());
    }

    @Test
    public void testActualizarPelicula() {
        when(peliculaServicio.actualizarPelicula(anyLong(), any(Pelicula.class))).thenReturn(Optional.of(pelicula));

        ResponseEntity<Pelicula> response = peliculaControlador.actualizarPelicula(1L, pelicula);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pelicula, response.getBody());
    }

    @Test
    public void testActualizarPeliculaNotFound() {
        when(peliculaServicio.actualizarPelicula(anyLong(), any(Pelicula.class))).thenReturn(Optional.empty());

        ResponseEntity<Pelicula> response = peliculaControlador.actualizarPelicula(1L, pelicula);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testEliminarPelicula() {
        ResponseEntity<HttpStatus> response = peliculaControlador.eliminarPelicula(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testEliminarTodosLasPeliculas() {
        ResponseEntity<HttpStatus> response = peliculaControlador.eliminarTodosLasPeliculas();

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
