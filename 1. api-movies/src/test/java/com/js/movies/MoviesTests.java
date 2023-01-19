package com.js.movies;

import com.js.movies.dao.GeneroDao;
import com.js.movies.dao.PagosEventoDao;
import com.js.movies.dao.PeliculaDao;
import com.js.movies.dto.PagosEventoDTO;
import com.js.movies.dto.PeliculaDTO;
import com.js.movies.modelo.Detalle;
import com.js.movies.modelo.Genero;
import com.js.movies.modelo.Pelicula;
import com.js.movies.modelo.Usuario;
import com.js.movies.servicio.GeneroServicio;
import com.js.movies.servicio.PagosEventoService;
import com.js.movies.servicio.PeliculaServicio;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MoviesTests {

    @Autowired
    private PeliculaServicio peliculaServicio;

    @Autowired
    private GeneroServicio generoServicio;

    @Autowired
    private PagosEventoService pagosEventoService;
    @MockBean
    private PeliculaDao peliculaDao;

    @MockBean
    private GeneroDao generoDao;

    @MockBean
    private PagosEventoDao pagosEventoDao;

    @Test
    public void getMovieByID() {
        PeliculaDTO resp = new PeliculaDTO();
        resp.setId(20);
        Mockito.when(peliculaDao.getPeliculaId(20)).thenReturn(resp);
        Assert.assertEquals(resp.getId(), peliculaServicio.getPeliculaId(20).getId());
        System.out.println("Test OK");
    }

    @Test
    public void getGeneros(){
        List<PeliculaDTO> peliculas = new ArrayList<>() ;
        Mockito.when(peliculaDao.getPeliculasCalificacion()).thenReturn(peliculas);
        Assert.assertEquals(peliculas.size(), peliculaServicio.getPeliculasCalificacion().size());
        System.out.println("Test OK");

    }

    @Test
    public void saveGenero(){
        Genero genero = new Genero();
        genero.setNombreGenero("Prueba");
        Short estado = 1;
        genero.setEstado(estado);
        String salida = "";
        Mockito.when(generoDao.saveCatalogo(genero)).thenReturn(salida);
        Assert.assertEquals(salida, generoServicio.saveGenero(genero));
    }

    @Test
    public void savePagoEvento(){
        PagosEventoDTO pagosEvento = new PagosEventoDTO(8, 100);
        Pelicula pelicula = new Pelicula();
        pelicula.setId(pagosEvento.getIdPelicula());

        Usuario usuario = new Usuario();
        usuario.setId(pagosEvento.getIdUsuario());
        Detalle detalle = new Detalle();
        detalle.setIdUsuario(usuario);
        detalle.setIdPelicula(pelicula);
        String salida = "";
        Mockito.when(pagosEventoDao.savePagosEvento(detalle)).thenReturn(salida);
        Assert.assertEquals(salida, pagosEventoService.savePagosEvento(pagosEvento.getIdUsuario(), pagosEvento.getIdPelicula()));

    }

}
