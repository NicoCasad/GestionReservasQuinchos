package com.Grupo2.ReservaQuinchos.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/servicio")
public class ServicioController {

    @GetMapping("/crear")
    public void crearServicio(){

    }
}
