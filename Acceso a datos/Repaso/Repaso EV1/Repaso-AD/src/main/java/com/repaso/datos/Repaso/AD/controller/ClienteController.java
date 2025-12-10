package com.repaso.datos.Repaso.AD.controller;

import com.repaso.datos.Repaso.AD.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ClienteController {
    @Autowired
    ClienteService clienteService;
}
