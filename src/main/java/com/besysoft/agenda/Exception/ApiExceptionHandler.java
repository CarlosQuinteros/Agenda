package com.besysoft.agenda.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({RecursoNoEncontradoException.class, HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public MensajeError recursoNoEncontradoException(HttpServletRequest request, Exception exception){
        return new MensajeError(exception, request.getRequestURI(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class, DatosInvalidosException.class})
    @ResponseBody
    public MensajeError badRequestException(HttpServletRequest request, Exception exception){
        return new MensajeError(exception, request.getRequestURI(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public MensajeError errorFatalException(HttpServletRequest request, Exception exception){
        /*
            Se deberia enviar un correo electronico al desarrollador indicando que pasó, para su analisis.
         */
        MensajeError mensajeError = new MensajeError(exception, request.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name());
        mensajeError.setMessage("Ocurrio un error inesperado.");
        return mensajeError;
    }

}
