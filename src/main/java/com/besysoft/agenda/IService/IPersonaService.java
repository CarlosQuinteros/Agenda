package com.besysoft.agenda.IService;

import com.besysoft.agenda.Entity.Persona;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IPersonaService {

    public Persona obtenerPersona(Long id);

    public List<Persona> listadoPersonas();

    public List<Persona> listadoPersonasPorNombre(String nombre);

    public List<Persona> listadoPersonasPorApellido(String apellido);

    public List<Persona> listadoPersonasPorCiudad(String ciudad);

    public List<Persona> listadoPersonasPorNombreYApellido(String nombre, String apellido);

    public List<Persona> personasPorVariasCiudades(String nombre, String apellido, List<String> ciudades);

    public Persona crearPersona(Persona persona);

    public Persona editarPersona(Long id, Persona persona);

    public void eliminarPersona(Long id);

}
