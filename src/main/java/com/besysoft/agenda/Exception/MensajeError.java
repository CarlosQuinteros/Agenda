package com.besysoft.agenda.Exception;

import java.time.ZonedDateTime;

public class MensajeError {
    private ZonedDateTime timestamp;
    private String message;
    private int status;
    private String path;
    private String error;

    public MensajeError(){}

    public MensajeError(Exception exception, String path, int status, String error){
        this.timestamp = ZonedDateTime.now();
        this.message = exception.getMessage();
        this.status = status;
        this.path = path;
        this.error = error;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public String getPath() {
        return path;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
