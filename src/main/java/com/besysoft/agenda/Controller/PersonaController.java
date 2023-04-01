package com.besysoft.agenda.Controller;

import com.besysoft.agenda.Dto.Mensaje;
import com.besysoft.agenda.Dto.PersonaDto;
import com.besysoft.agenda.Entity.Persona;
import com.besysoft.agenda.Exception.DatosInvalidosException;
import com.besysoft.agenda.IService.IPersonaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.xml.transform.OutputKeys;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/agenda/api/personas")
public class PersonaController {

    @Autowired
    private IPersonaService personaService;

    @PostMapping()
    public ResponseEntity<?> crearPersona(@Valid @RequestBody PersonaDto personaDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DatosInvalidosException(bindingResult);
        }
        Persona nuevaPersona = new Persona(personaDto.getNombre(),
                personaDto.getApellido(),
                personaDto.getTelefono(),
                personaDto.getCiudad(),
                personaDto.getEmail()
        );
        nuevaPersona = personaService.crearPersona(nuevaPersona);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevaPersona.getId())
                .toUri();
        return ResponseEntity.created(location).body(new Mensaje("Persona creada correctamente"));
    }

    @GetMapping()
    public ResponseEntity<List<Persona>> listadoPersonas(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String apellido
    ) {
        List<Persona> personas;
        if(!Objects.isNull(nombre) && Objects.isNull(apellido)){
            personas = personaService.listadoPersonasPorNombre(nombre);
            return ResponseEntity.ok(personas);
        }
        if(Objects.isNull(nombre) && !Objects.isNull(apellido)){
            personas = personaService.listadoPersonasPorApellido(apellido);
            return ResponseEntity.ok(personas);
        }
        if(!Objects.isNull(nombre) && !Objects.isNull(apellido)){
            personas = personaService.listadoPersonasPorNombreYApellido(nombre,apellido);
            return ResponseEntity.ok(personas);
        }
        personas = personaService.listadoPersonas();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPersonaPorId(@PathVariable Long id) {
        Persona persona = personaService.obtenerPersona(id);
        return ResponseEntity.ok(persona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarPersona(@PathVariable Long id, @Valid @RequestBody PersonaDto personaDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DatosInvalidosException(bindingResult);
        }
        Persona personaEditar = new Persona(personaDto.getNombre(),
                personaDto.getApellido(),
                personaDto.getTelefono(),
                personaDto.getCiudad(),
                personaDto.getEmail());
        personaService.editarPersona(id, personaEditar);
        return new ResponseEntity<>(new Mensaje("Persona editada correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPersona(@PathVariable Long id) {
        personaService.eliminarPersona(id);
        return new ResponseEntity<>(new Mensaje("Persona eliminada correctamente"), HttpStatus.OK);
    }

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity personasPorCiudad(@PathVariable String ciudad){
        List<Persona> personas = personaService.listadoPersonasPorCiudad(ciudad);
        return ResponseEntity.ok(personas);
    }

    @GetMapping("/varias-ciudades")
    public ResponseEntity<?> personasPorVariasCiudades(
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam List<String> ciudad){

        List<Persona> personas = personaService.personasPorVariasCiudades(nombre, apellido, ciudad);

        return ResponseEntity.ok(personas);
    }

}
