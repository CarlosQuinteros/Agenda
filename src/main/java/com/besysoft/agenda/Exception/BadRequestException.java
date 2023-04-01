package com.besysoft.agenda.Exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String mensaje){
        super(mensaje);
    }
}
