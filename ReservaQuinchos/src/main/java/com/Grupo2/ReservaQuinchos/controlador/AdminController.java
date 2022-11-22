package com.Grupo2.ReservaQuinchos.controlador;

import com.Grupo2.ReservaQuinchos.entidades.Servicio;
import com.Grupo2.ReservaQuinchos.entidades.Usuario;
import com.Grupo2.ReservaQuinchos.excepciones.MyException;
import com.Grupo2.ReservaQuinchos.servicio.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ServicioService servicioService;

    @GetMapping("/servicio/crear")
    public String crear(){
        return "crearServicio";
    }

    @PostMapping("/servicio/creado")
    public String creado(@RequestParam String descripcion, @RequestParam String nombre, @RequestParam float precio, @RequestParam MultipartFile imagen, ModelMap modelo){
        try{
            servicioService.crearServicio(descripcion, nombre, precio, imagen);
            return "adminpanel";
        }catch (Exception ex){
            modelo.put("error", ex.getMessage());
            return"adminpanel";
        }
    }

    @GetMapping(value = "/servicio/listar")
    public String listar(ModelMap modelo) {
        try {
            List<Servicio> servicios = servicioService.findAll();
            modelo.addAttribute("servicios", servicios);
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }finally {
            return "listar_servicios.html";
        }
    }

    @GetMapping(value = "/servicio/modificar/{id}")
    public String modificar(@PathVariable("id") Long id, ModelMap modelo){
        try{
            Servicio servicio = servicioService.findByID(id);
            modelo.addAttribute("servicio", servicio);
            return "modificar.html";
        }catch (Exception e){
            modelo.put("error", e.getMessage());
            return "modificar.html";
        }
    }

    @PostMapping(value = "/servicio/modificado/{id}")
    public String modificado(@PathVariable Long id, String descripcion, String nombre, float precio, MultipartFile imagen, ModelMap modelo) throws Exception {

        try{
            servicioService.actualizar(id, descripcion, nombre, precio, imagen);
            List<Servicio> servicios = servicioService.findAll();
            modelo.addAttribute("servicios", servicios);
            return "redirect:../listar";
        }catch (Exception e){
            List<Servicio> servicios = servicioService.findAll();
            modelo.addAttribute("servicios", servicios);
            modelo.addAttribute("error", e.getMessage());
            return "listar_servicios";
        }
    }

    @GetMapping(value = "/servicio/delete/{id}")
    public String delete(@PathVariable("id") Long id, ModelMap modelo){
        try{
            servicioService.eliminar(id);
            modelo.put("exito", "eliminado correctamente");

        }catch (Exception e){
            modelo.put("error", e.getMessage());
        }finally {
            return "listar_servicios.html";
        }
    }
}
