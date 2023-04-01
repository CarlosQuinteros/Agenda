package com.besysoft.agenda.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ContactoDto {

    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

    private String imagenUrl;

    @NotNull(message = "La empresa es obligatoria")
    @Min(value = 1, message = "El minimo valor de idEmpresa es 1")
    private Long idEmpresa;

    @NotNull(message = "La persona es obligatoria")
    @Min(value = 1, message = "El minimo valor de idPersona es 1")
    private Long idPersona;

    public String getTitulo() {
        return titulo;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public Long getIdPersona() {
        return idPersona;
    }
}
