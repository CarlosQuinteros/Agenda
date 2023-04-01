package com.besysoft.agenda.Controller;

import com.besysoft.agenda.Dto.EmpresaDto;
import com.besysoft.agenda.Dto.Mensaje;
import com.besysoft.agenda.Entity.Empresa;
import com.besysoft.agenda.Exception.DatosInvalidosException;
import com.besysoft.agenda.IService.IEmpresaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/agenda/api/empresas")
public class EmpresaController {

    @Autowired
    private IEmpresaService empresaService;

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


}
