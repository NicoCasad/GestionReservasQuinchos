package com.Grupo2.ReservaQuinchos.controlador;

import com.Grupo2.ReservaQuinchos.entidades.FotoQuincho;
import com.Grupo2.ReservaQuinchos.entidades.Quincho;
import com.Grupo2.ReservaQuinchos.entidades.Usuario;
import com.Grupo2.ReservaQuinchos.servicio.FotoQuinchoService;
import com.Grupo2.ReservaQuinchos.servicio.QuinchoService;
import com.Grupo2.ReservaQuinchos.servicio.UsuarioService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/quincho")
public class QuinchoController {

    @Autowired
    private QuinchoService quinchoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private FotoQuinchoService fotoQuinchoService;

    @GetMapping("/registrar")
    public String registrar() {
        return "registrar_quincho.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre,
            @RequestParam float precioBase,
            @RequestParam String ubicacion, ModelMap modelo) {
        try {
            Usuario usuario = usuarioService.findByID(1L);
            quinchoService.registrar(nombre, precioBase, ubicacion, usuario);
            
            modelo.put("exito", "Registrado correctamente");
            return "redirect:../quincho/listar";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "registrar_quincho.html";
        }
    }

    @GetMapping("/listar")
    public String listar(ModelMap modelo) {
        try {
            //List<Quincho> quinchos = quinchoService.findAll();
            List<Quincho> quinchos = quinchoService.devolverQuinchosDePropietario(1L);
            modelo.addAttribute("quinchos", quinchos);
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        } finally {
            return "mis_quinchos.html";
        }
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable("id") Long id, ModelMap modelo) {
        try {
            Quincho quincho = quinchoService.findByID(id);
            modelo.put("quincho", quincho);
            System.out.println(quincho);
            return "modificar_quincho.html";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "modificar_quincho.html";
        }
    }

    @PostMapping("/modificado/{id}")
    public String modificado(@PathVariable Long id,
            @RequestParam String nombre,
            @RequestParam float precioBase,
            @RequestParam String ubicacion, ModelMap modelo) {

        try {
            quinchoService.actualizar(id, nombre, precioBase, ubicacion);
           // return "redirect:../listar";
           return "redirect:../fotos/"+id;
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "redirect:../listar";
        }
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Long id, ModelMap modelo) {
        try {

            List<FotoQuincho> fotosQuinchos = fotoQuinchoService.listaDeFotosDeQuinchos(id);
            for (FotoQuincho foto : fotosQuinchos) {
                fotoQuinchoService.eliminar(foto.getId());
            }

            quinchoService.eliminar(id);
            modelo.put("exito", "eliminado correctamente");

        } catch (Exception e) {
            modelo.put("error", e.getMessage());
        } finally {
            return "redirect:../listar";
        }
    }

    @GetMapping("/fotos/{id}")
    public String registrarFotos(@PathVariable("id") Long id,ModelMap modelo) {
        try {
            Quincho quincho = quinchoService.findByID(id);
            List<FotoQuincho> fotosQuinchos = fotoQuinchoService.listaDeFotosDeQuinchos(id);
            System.out.println("cantidad : " + fotosQuinchos.size());
            modelo.put("quincho", quincho);
            modelo.put("imagenes", fotosQuinchos);

        } catch (Exception ex) {
            Logger.getLogger(QuinchoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return "fotos_quinchos.html";
        }
    }

    @PostMapping("/registroFotos")
    public String registroFotos(@RequestParam MultipartFile file,
            @RequestParam Long id_quincho,
            ModelMap modelo) {
        try {
            Quincho quincho = quinchoService.findByID(id_quincho);
            String nombre = file.getOriginalFilename();

            fotoQuinchoService.guardarArchivoFoto(file, "\\src\\main\\resources\\static\\Images");
            fotoQuinchoService.registrar(nombre, quincho);
            modelo.put("quincho", quincho);
            modelo.put("exito", "¡¡FOTO GUARDADA CORRECTAMENTE!!");

        } catch (IOException ex) {
            modelo.put("error", "¡¡ERROR NO SE PUEDE GUARDAR EL ARCHIVO!!");
            return "fotos_quinchos.html";
        } catch (Exception ex) {
            modelo.put("error", "¡¡ERROR NO SE ENCONTRO EL QUINCHO!!");
        }
        return "redirect:../quincho/fotos/"+id_quincho;
        //return "fotos_quinchos.html";
    }

}
