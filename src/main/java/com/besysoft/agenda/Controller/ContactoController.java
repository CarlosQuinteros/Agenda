package com.besysoft.agenda.Controller;

import com.besysoft.agenda.IService.IContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agenda/api/contactos")
public class ContactoController {

    @Autowired
    IContactoService contactoService;
}
