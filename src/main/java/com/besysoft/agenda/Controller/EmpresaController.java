package com.besysoft.agenda.Controller;

import com.besysoft.agenda.Dto.EmpresaDto;
import com.besysoft.agenda.Dto.Mensaje;
import com.besysoft.agenda.Entity.Contacto;
import com.besysoft.agenda.Entity.Empresa;
import com.besysoft.agenda.Entity.Persona;
import com.besysoft.agenda.Exception.BadRequestException;
import com.besysoft.agenda.Exception.DatosInvalidosException;
import com.besysoft.agenda.IService.IContactoService;
import com.besysoft.agenda.IService.IEmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/agenda/api/empresas")
public class EmpresaController {

    @Autowired
    private IEmpresaService empresaService;

    @Autowired
    private IContactoService contactoService;

    @PostMapping()
    public ResponseEntity<?> crearEmpresa(@Valid @RequestBody EmpresaDto empresaDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DatosInvalidosException(bindingResult);
        }
        Empresa nuevaEmpresa = new Empresa(empresaDto.getNombre(),
                empresaDto.getEmail(),
                empresaDto.getSitioWeb(),
                empresaDto.getImagenUrl());
        nuevaEmpresa = empresaService.crearEmpresa(nuevaEmpresa);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevaEmpresa.getId())
                .toUri();
        return ResponseEntity.created(location).body(new Mensaje("Empresa creada correctamente"));
    }

    @GetMapping()
    public ResponseEntity<List<Empresa>> listadoEmpresas(){
        List<Empresa> empresas = empresaService.listadoEmpresas();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obtenerEmpresa(@PathVariable Long id){
        Empresa empresa = empresaService.obtenerEmpresa(id);
        return ResponseEntity.ok(empresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarEmpresa(@PathVariable Long id, @Valid @RequestBody EmpresaDto empresaDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new DatosInvalidosException(bindingResult);
        }
        Empresa empresaEditar = new Empresa(empresaDto.getNombre(),
                empresaDto.getEmail(),
                empresaDto.getSitioWeb(),
                empresaDto.getImagenUrl()
        );
        empresaService.editarEmpresa(id, empresaEditar);
        return ResponseEntity.ok(new Mensaje("Empresa editada correctamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEmpresa(@PathVariable Long id){
        empresaService.eliminarEmpresa(id);
        return ResponseEntity.ok(new Mensaje("Empresa eliminada correctamente"));
    }

    @GetMapping("/{id}/contactos")
    public ResponseEntity<List<Contacto>> contactosPorEmpresa(@PathVariable Long id){
        List<Contacto> contactos = contactoService.listadoContactosPorEmpresa(id);
        return ResponseEntity.ok(contactos);
    }


}
