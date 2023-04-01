package com.besysoft.agenda.IService;

import com.besysoft.agenda.Entity.Empresa;

import java.util.List;

public interface IEmpresaService {

    public Empresa obtenerEmpresa(Long id);

    public List<Empresa> listadoEmpresas();

    public Empresa crearEmpresa(Empresa empresa);

    public Empresa editarEmpresa(Long id, Empresa empresa);

    public void eliminarEmpresa(Long id);
}
