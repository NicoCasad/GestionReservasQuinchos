package com.Grupo2.ReservaQuinchos.controlador;

import com.Grupo2.ReservaQuinchos.entidades.Usuario;
import com.Grupo2.ReservaQuinchos.servicio.UsuarioService;
import com.Grupo2.ReservaQuinchos.servicio.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping(path = "/usuario")
public class UsuarioController{

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/registrar")
    public String registrar(){
        return "form.html";
    }

    @PostMapping(value = "/registro")
    public String registro(MultipartFile imagen, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String rol, @RequestParam String contrasena, ModelMap modelo){
        try {
            usuarioService.registrar(imagen, email, nombre, apellido, rol, contrasena);
            modelo.put("exito", "Registrado correctamente");
            return  "index.html";
        }catch(Exception e){
            modelo.put("error", e.getMessage());
            return "form.html";
        }
    }

    @GetMapping(value = "/login")
    public String login(){
        return "login.html";
    }

    @GetMapping(value = "/listar")
    public String listar(ModelMap modelo) {
        try {
            List<Usuario> usuarios = usuarioService.findAll();
            modelo.addAttribute("usuarios", usuarios);
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        }finally {
            return "usuario_list.html";
        }
    }

    @GetMapping(value = "/modificar/{id}")
    public String modificar(@PathParam("id") Long id, ModelMap modelo){
        try{
            modelo.put("usuario", usuarioService.findByID((id)));
            return "modificar_usuario.html";
        }catch (Exception e){
            modelo.put("error", e.getMessage());
            return "modificar_usuario.html";
        }
    }

    @PostMapping(value = "/modificado/{id}")
    public String modificado(@PathVariable("id") Long id, MultipartFile imagen, String nombre, String apellido, String email, String contrasena, ModelMap modelo){

        try{
            System.out.println(contrasena);
            usuarioService.actualizar(imagen, id, nombre, apellido, email, contrasena);
            return "redirect:../listar";
        }catch (Exception e){
            modelo.put("error", e.getMessage());
            return "modificar_usuario.html";
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Long id, ModelMap modelo){
        try{
            usuarioService.eliminar(id);
            modelo.put("excito", "eliminado correctamente");

        }catch (Exception e){
            modelo.put("error", e.getMessage());
        }finally {
            return "usuario_list.html";
        }
    }
}
