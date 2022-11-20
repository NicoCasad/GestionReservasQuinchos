package com.Grupo2.ReservaQuinchos.servicio;

import com.Grupo2.ReservaQuinchos.entidades.Servicio;
import com.Grupo2.ReservaQuinchos.excepciones.MyException;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

public interface ServicioService extends BaseService<Servicio, Long>{

    public void crearServicio(String descripcion, String nombre, float precio, MultipartFile imagen) throws MyException;

    @Transactional
    void actualizar(Long id, String descripcion, String nombre, float precio, MultipartFile imagen) throws Exception;

    @Transactional
    void eliminar(Long id) throws MyException;
}
