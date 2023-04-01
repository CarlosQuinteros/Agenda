package com.besysoft.agenda.ServiceImp;

import com.besysoft.agenda.Entity.Persona;
import com.besysoft.agenda.Exception.RecursoNoEncontradoException;
import com.besysoft.agenda.IService.IPersonaService;
import com.besysoft.agenda.Repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonaService implements IPersonaService {

    @Autowired
    private PersonaRepository personaRepository;

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
        personaRepository.deleteById(persona.getId());
    }
}
