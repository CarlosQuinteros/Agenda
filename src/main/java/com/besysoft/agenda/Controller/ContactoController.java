package com.besysoft.agenda.Controller;

import com.besysoft.agenda.Dto.ContactoDto;
import com.besysoft.agenda.Dto.Mensaje;
import com.besysoft.agenda.Entity.Contacto;
import com.besysoft.agenda.Entity.Empresa;
import com.besysoft.agenda.Entity.Persona;
import com.besysoft.agenda.Exception.DatosInvalidosException;
import com.besysoft.agenda.IService.IContactoService;
import com.besysoft.agenda.IService.IEmpresaService;
import com.besysoft.agenda.IService.IPersonaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/agenda/api/contactos")
public class ContactoController {

    @Autowired
    IContactoService contactoService;

    @Autowired
    IPersonaService personaService;

    @Autowired
    IEmpresaService empresaService;

    @PostMapping()
    public ResponseEntity<?> crearContacto(@Valid @RequestBody ContactoDto contactoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new DatosInvalidosException(bindingResult);
        }
        Persona persona = personaService.obtenerPersona(contactoDto.getIdPersona());
        Empresa empresa = empresaService.obtenerEmpresa(contactoDto.getIdEmpresa());
        Contacto nuevoContacto = new Contacto(contactoDto.getTitulo(), persona,empresa,contactoDto.getImagenUrl());

        contactoService.crearContacto(nuevoContacto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevoContacto.getId())
                .toUri();
        return ResponseEntity.created(location).body(new Mensaje("Contacto creado correctamente"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contacto> obtenerContacto(@PathVariable Long id){
        Contacto contacto = contactoService.obtenerContacto(id);
        return ResponseEntity.ok(contacto);
    }

    @GetMapping()
    public ResponseEntity<List<Contacto>> listadoContactos(){
        List<Contacto> contactos = contactoService.listadoContactos();
        return ResponseEntity.ok(contactos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarContacto(@PathVariable Long id,@Valid @RequestBody ContactoDto contactoDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new DatosInvalidosException(bindingResult);
        }
        Persona persona = personaService.obtenerPersona(contactoDto.getIdPersona());
        Empresa empresa = empresaService.obtenerEmpresa(contactoDto.getIdEmpresa());
        Contacto contactoAEditar = new Contacto(contactoDto.getTitulo(), persona,empresa,contactoDto.getImagenUrl());
        contactoService.editarContacto(id,contactoAEditar);
        return ResponseEntity.ok(new Mensaje("Contacto editado correctamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarContacto(@PathVariable Long id){
        contactoService.eliminarContacto(id);
        return ResponseEntity.ok(new Mensaje("Contacto eliminado correctamente"));
    }

}
