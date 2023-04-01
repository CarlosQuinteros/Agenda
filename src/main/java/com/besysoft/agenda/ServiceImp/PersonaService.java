package com.besysoft.agenda.ServiceImp;

import com.besysoft.agenda.Entity.Persona;
import com.besysoft.agenda.Exception.BadRequestException;
import com.besysoft.agenda.Exception.RecursoNoEncontradoException;
import com.besysoft.agenda.IService.IContactoService;
import com.besysoft.agenda.IService.IPersonaService;
import com.besysoft.agenda.Repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonaService implements IPersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private IContactoService contactoService;

    @Override
    public Persona obtenerPersona(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontradoException("La Persona con id:" + id + " no existe."));
    }

    @Override
    public List<Persona> listadoPersonas() {
        return personaRepository.findAllByOrderByApellidoAsc();
    }

    @Override
    public List<Persona> listadoPersonasPorNombre(String nombre) {
        return personaRepository.findByNombre(nombre);
    }

    @Override
    public List<Persona> listadoPersonasPorApellido(String apellido) {
        return personaRepository.findByApellido(apellido);
    }

    @Override
    public List<Persona> listadoPersonasPorCiudad(String ciudad) {
        return personaRepository.findByCiudadContaining(ciudad);
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public Persona editarPersona(Long id, Persona persona) {
        Persona personaAEditar = obtenerPersona(id);
        personaAEditar.setApellido(persona.getApellido());
        personaAEditar.setNombre(persona.getNombre());
        personaAEditar.setEmail(persona.getEmail());
        personaAEditar.setCiudad(persona.getCiudad());
        personaAEditar.setTelefono(persona.getTelefono());
        return personaRepository.save(personaAEditar);
    }

    @Override
    public void eliminarPersona(Long id) {
        Persona persona = obtenerPersona(id);
        if(contactoService.existeContactoPorPersona(persona.getId())){
            throw new BadRequestException(persona.getNombre() + " " + persona.getApellido() + " tiene referencias con contactos y no se puede eliminar.");
        }
        personaRepository.deleteById(persona.getId());
    }

    @Override
    public List<Persona> listadoPersonasPorNombreYApellido(String nombre, String apellido) {
        return personaRepository.findByNombreAndApellido(nombre, apellido);
    }

    @Override
    public List<Persona> personasPorVariasCiudades(String nombre, String apellido, List<String> ciudades) {
        List<Persona> personas = listadoPersonasPorNombreYApellido(nombre, apellido)
                .stream()
                .filter(persona -> ciudades.contains(persona.getCiudad()))
                .collect(Collectors.toList());
        return personas;
    }
}
