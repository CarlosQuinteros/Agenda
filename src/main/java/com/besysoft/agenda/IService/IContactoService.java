package com.besysoft.agenda.IService;

import com.besysoft.agenda.Entity.Contacto;

import java.util.List;

public interface IContactoService {

    public Contacto crearContacto(Contacto contacto);

    public Contacto obtenerContacto(Long id);

    public Contacto editarContacto(Long id, Contacto contacto);

    public List<Contacto> listadoContactos();

    public List<Contacto> listadoContactosPorEmpresa(Long idEmpresa);

    public boolean existeContactoPorEmpresa(Long idEmpresa);

    public boolean existeContactoPorPersona(Long idPersona);

}
