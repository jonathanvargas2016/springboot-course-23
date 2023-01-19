package com.js.movies.excepcion;

import java.util.NoSuchElementException;

public class ElementoNuloExcepcion extends NoSuchElementException {

    public ElementoNuloExcepcion(String mensaje) {
        super(mensaje);
    }
}
