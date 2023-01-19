package com.js.movies.salida;

public class Respuesta {

    private String mensaje;

    /**
     * 0: ok
     * 1: error de cliente
     * 2: error no controlado
     */
    private int codigo;

    private Object respuesta;

    public Respuesta() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Object getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Object respuesta) {
        this.respuesta = respuesta;
    }
}
