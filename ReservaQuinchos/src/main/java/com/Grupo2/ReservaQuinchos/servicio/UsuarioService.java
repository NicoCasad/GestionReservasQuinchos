package com.Grupo2.ReservaQuinchos.servicio;

import com.Grupo2.ReservaQuinchos.entidades.Usuario;
import com.Grupo2.ReservaQuinchos.excepciones.MyException;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;

public interface UsuarioService extends BaseService<Usuario, Long>{
    public void registrar(MultipartFile imagen, String telefono, String email, String nombre, String apellido, String rol, String contrasena) throws Exception;
    public void validar(String nombre, String apellido, String email, String contrasena) throws Exception;
    void validarActualizar(String nombre, String apellido, String email, String contrasena, Usuario usuario) throws Exception;
    public void actualizar(MultipartFile imagen, Long id, String telefono, String nombre, String apellido, String email, String contrasena) throws Exception;
    public void eliminar(Long id) throws MyException;
}
