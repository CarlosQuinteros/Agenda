package com.besysoft.agenda.Exception;

import java.time.ZonedDateTime;

public class MensajeError {
    private ZonedDateTime fechaHora;
    private String mensaje;
    private String exception;
    private String path;

    public MensajeError(){}

    public MensajeError(Exception exception, String path){
        this.fechaHora = ZonedDateTime.now();
        this.mensaje = exception.getMessage();
        this.exception = exception.getClass().getSimpleName();
        this.path = path;
    }

    public ZonedDateTime getFechaHora() {
        return fechaHora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getException() {
        return exception;
    }

    public String getPath() {
        return path;
    }

    public void setFechaHora(ZonedDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
