package com.Grupo2.ReservaQuinchos.servicio;

import com.Grupo2.ReservaQuinchos.entidades.Imagen;
import com.Grupo2.ReservaQuinchos.entidades.Servicio;
import com.Grupo2.ReservaQuinchos.entidades.Usuario;
import com.Grupo2.ReservaQuinchos.excepciones.MyException;
import org.springframework.web.multipart.MultipartFile;

public interface ServicioService extends BaseService<Servicio, Long>{

    public void crearServicio(String descripcion, String nombre, float precio, Imagen imagen);

}
