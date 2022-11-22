package com.Grupo2.ReservaQuinchos.servicio;

import com.Grupo2.ReservaQuinchos.entidades.Quincho;
import com.Grupo2.ReservaQuinchos.entidades.Usuario;
import com.Grupo2.ReservaQuinchos.excepciones.MyException;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface QuinchoService extends BaseService<Quincho, Long> {

    public void registrar(String nombre, float precioBase, String ubicacion, Usuario usuarioId) throws Exception;

    public void validar(String nombre, float precioBase, String ubicacion) throws Exception;

    public List<Quincho> devolverQuinchosDePropietario(Long idUsuario) throws Exception;

    public void actualizar(Long id, String nombre, float precioBase, String ubicacion) throws Exception;

    public void eliminar(Long id) throws MyException;
}
