package com.Grupo2.ReservaQuinchos.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "/")
    public String index(){
        return "index.html";
    }
}
