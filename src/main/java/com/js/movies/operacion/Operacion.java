package com.js.movies.operacion;

import java.text.Normalizer;

public class Operacion {

    public Operacion() {
    }

    public String convertirGenero(String nombreGenero) {
        String respuesta = nombreGenero.replace(" ", "-").toLowerCase();
        respuesta = Normalizer.normalize(respuesta, Normalizer.Form.NFD);
        respuesta = respuesta.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");
        respuesta = Normalizer.normalize(respuesta, Normalizer.Form.NFC);
        return respuesta;
    }

}
