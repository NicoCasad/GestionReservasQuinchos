package com.Grupo2.ReservaQuinchos.controlador;

import com.Grupo2.ReservaQuinchos.entidades.Usuario;
import com.Grupo2.ReservaQuinchos.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(path = "/usuario")
public class UsuarioController{

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/registrar")
    public String registrar(){
        return "Registro.html";
    }

    @PostMapping(value = "/registro")
    public String registro(MultipartFile imagen, String telefono, @RequestParam String nombre, @RequestParam String apellido, @RequestParam String email, @RequestParam String rol, @RequestParam String contrasena, ModelMap modelo){
        try {
            usuarioService.registrar(imagen, telefono, email, nombre, apellido, rol, contrasena);
            modelo.put("exito", "Registrado correctamente");
            return  "index.html";
        }catch(Exception e){
            modelo.put("error", e.getMessage());
            return "Registro.html";
        }
    }

    @GetMapping(value = "/login")
    public String login(ModelMap modelo){
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
            return "listar_usuarios.html";
        }
    }

    @GetMapping(value = "/modificar/{id}")
    public String modificar(@PathVariable("id") Long id, ModelMap modelo){
        try{
            Usuario usuario = usuarioService.findByID(id);
            modelo.addAttribute("usuario", usuario);
            return "modificar.html";
        }catch (Exception e){
            modelo.put("error", e.getMessage());
            return "modificar.html";
        }
    }

    @PostMapping(value = "/modificado/{id}")
    public String modificado(@PathVariable Long id, MultipartFile imagen, String telefono, String nombre, String apellido, String email, String contrasena, ModelMap modelo) throws Exception {

        try{
            usuarioService.actualizar(imagen, id, telefono, nombre, apellido, email, contrasena);
            List<Usuario> usuarios = usuarioService.findAll();
            modelo.addAttribute("usuarios", usuarios);
            return "redirect:../listar";
        }catch (Exception e){
            List<Usuario> usuarios = usuarioService.findAll();
            modelo.addAttribute("usuarios", usuarios);
            modelo.addAttribute("error", e.getMessage());
            return "listar_usuarios";
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Long id, ModelMap modelo){
        try{
            usuarioService.eliminar(id);
            modelo.put("exito", "eliminado correctamente");

        }catch (Exception e){
            modelo.put("error", e.getMessage());
        }finally {
            return "listar_usuarios.html";
        }
    }
}
