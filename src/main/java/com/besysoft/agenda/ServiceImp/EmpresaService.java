package com.besysoft.agenda.ServiceImp;

import com.besysoft.agenda.Entity.Empresa;
import com.besysoft.agenda.Exception.BadRequestException;
import com.besysoft.agenda.Exception.RecursoNoEncontradoException;
import com.besysoft.agenda.IService.IContactoService;
import com.besysoft.agenda.IService.IEmpresaService;
import com.besysoft.agenda.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpresaService implements IEmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private IContactoService contactoService;

    @Override
    public Empresa obtenerEmpresa(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontradoException("La empresa con id: " + id + " no existe."));
    }

    @Override
    public List<Empresa> listadoEmpresas() {
        return empresaRepository.findAllByOrderByNombreAsc();
    }

    @Override
    public Empresa crearEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa editarEmpresa(Long id, Empresa empresa) {
        Empresa empresaAEditar = obtenerEmpresa(id);
        empresaAEditar.setNombre(empresa.getNombre());
        empresaAEditar.setEmail(empresa.getEmail());
        empresaAEditar.setSitioWeb(empresa.getSitioWeb());
        empresaAEditar.setImagenUrl(empresa.getImagenUrl());
        return empresaRepository.save(empresaAEditar);
    }

    @Override
    public void eliminarEmpresa(Long id) {
        Empresa empresa = obtenerEmpresa(id);
        if(contactoService.existeContactoPorEmpresa(empresa.getId())){
            throw new BadRequestException("La empresa " + empresa.getNombre() + " tiene referencias en contactos y no se puede eliminar.");
        }
        empresaRepository.deleteById(empresa.getId());
    }
}
