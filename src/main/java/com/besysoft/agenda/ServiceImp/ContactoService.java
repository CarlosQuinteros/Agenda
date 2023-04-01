package com.besysoft.agenda.ServiceImp;

import com.besysoft.agenda.Entity.Contacto;
import com.besysoft.agenda.IService.IContactoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContactoService implements IContactoService {

    @Override
    public Contacto crearContacto(Contacto contacto) {
        return null;
    }

    @Override
    public Contacto obtenerContacto(Long id) {
        return null;
    }

    @Override
    public Contacto editarContacto(Long id, Contacto contacto) {
        return null;
    }

    @Override
    public List<Contacto> listadoContactos() {
        return null;
    }

    @Override
    public List<Contacto> listadoContactosPorEmpresa(Long idEmpresa) {
        return null;
    }

    @Override
    public boolean existeContactoPorEmpresa(Long idEmpresa) {
        return false;
    }

    @Override
    public boolean existeContactoPorPersona(Long idPersona) {
        return false;
    }
}
