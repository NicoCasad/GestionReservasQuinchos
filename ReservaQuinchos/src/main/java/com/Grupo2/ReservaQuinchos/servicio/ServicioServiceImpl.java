package com.Grupo2.ReservaQuinchos.servicio;

import com.Grupo2.ReservaQuinchos.entidades.Imagen;
import com.Grupo2.ReservaQuinchos.entidades.Servicio;
import com.Grupo2.ReservaQuinchos.excepciones.MyException;
import com.Grupo2.ReservaQuinchos.repositorio.BaseRepository;
import com.Grupo2.ReservaQuinchos.repositorio.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ServicioServiceImpl extends BaseServiceImpl<Servicio, Long> implements ServicioService{
    @Autowired
    ServicioRepository servicioRepository;
    @Autowired
    ImagenService imagenService;

    public ServicioServiceImpl(BaseRepository<Servicio, Long> baseRepository) {super(baseRepository);}

    @Override
    public void crearServicio(String descripcion, String nombre, float precio, MultipartFile imagen) throws MyException {
        validate(descripcion,nombre,precio);
        Servicio servicio = new Servicio();

        servicio.setDescripcion(descripcion);
        servicio.setNombre(nombre);
        servicio.setPrecio(precio);

        Imagen imagenServicio = imagenService.guardar(imagen);
        servicio.setImagen(imagenServicio);

        servicioRepository.save(servicio);
    }

    public void validate(String descripcion, String nombre, float precio) throws MyException{
        if (descripcion == null || descripcion.isEmpty()){
            throw new MyException("No puede enviar este campo nulo");
        }
    }
    @Override
    @Transactional
    public void actualizar(Long id, String descripcion, String nombre, float precio, MultipartFile imagen) throws Exception{

        Optional<Servicio> respuesta = servicioRepository.findById(id);
        if (respuesta.isPresent()){
            Servicio servicio = respuesta.get();
            validate(descripcion,nombre,precio);
            servicio.setDescripcion(descripcion);
            servicio.setNombre(nombre);
            servicio.setPrecio(precio);

            Long idImagen = null;

            if (servicio.getImagen() != null) {
                idImagen = servicio.getImagen().getId();
            }

            Imagen imagenServicio = imagenService.actualizar(imagen, idImagen);

            servicio.setImagen(imagenServicio);

            servicioRepository.save(servicio);
        }
    }

    @Override
    @Transactional
    public void eliminar(Long id) throws MyException{
        Optional<Servicio> respuesta = servicioRepository.findById(id);

        if (respuesta.isPresent()){
            Servicio servicio = respuesta.get();
            servicioRepository.delete(servicio);
        }else{
            throw new MyException("no existe este usuario");
        }
    }
}
