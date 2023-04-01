package com.besysoft.agenda.ServiceImp;

import com.besysoft.agenda.Entity.Contacto;
import com.besysoft.agenda.Exception.RecursoNoEncontradoException;
import com.besysoft.agenda.IService.IContactoService;
import com.besysoft.agenda.Repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContactoService implements IContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    @Override
    public Contacto crearContacto(Contacto contacto) {
        return contactoRepository.save(contacto);
    }

    @Override
    public Contacto obtenerContacto(Long id) {
        return contactoRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontradoException("El contacto con id: " + id + " no existe."));
    }

    @Override
    public Contacto editarContacto(Long id, Contacto contacto) {
        Contacto contactoAEditar = obtenerContacto(id);
        contactoAEditar.setEmpresa(contacto.getEmpresa());
        contactoAEditar.setTitulo(contacto.getTitulo());
        contactoAEditar.setImagenUrl(contacto.getImagenUrl());
        contactoAEditar.setPersona(contacto.getPersona());
        return contactoRepository.save(contactoAEditar);
    }

    @Override
    public List<Contacto> listadoContactos() {
        return contactoRepository.findAllByOrderByPersona_Apellido();
    }

    @Override
    public List<Contacto> listadoContactosPorEmpresa(Long idEmpresa) {
        return contactoRepository.findByEmpresa_Id(idEmpresa);
    }

    @Override
    public boolean existeContactoPorEmpresa(Long idEmpresa) {
        return contactoRepository.existsByEmpresaId(idEmpresa);
    }

    @Override
    public boolean existeContactoPorPersona(Long idPersona) {
        return contactoRepository.existsByPersonaId(idPersona);
    }

    @Override
    public void eliminarContacto(Long id) {
        Contacto contacto = obtenerContacto(id);
        contactoRepository.deleteById(contacto.getId());
    }
}
