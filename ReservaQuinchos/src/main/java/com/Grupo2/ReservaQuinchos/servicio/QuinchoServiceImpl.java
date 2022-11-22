package com.Grupo2.ReservaQuinchos.servicio;

import com.Grupo2.ReservaQuinchos.entidades.Imagen;
import com.Grupo2.ReservaQuinchos.entidades.Quincho;
import com.Grupo2.ReservaQuinchos.entidades.Usuario;
import com.Grupo2.ReservaQuinchos.excepciones.MyException;
import com.Grupo2.ReservaQuinchos.repositorio.BaseRepository;
import com.Grupo2.ReservaQuinchos.repositorio.QuinchoRepository;
import com.Grupo2.ReservaQuinchos.repositorio.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class QuinchoServiceImpl extends BaseServiceImpl<Quincho, Long> implements QuinchoService {

    @Autowired
    QuinchoRepository quinchoRepository;

    public QuinchoServiceImpl(BaseRepository<Quincho, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional
    public void registrar(String nombre, float precioBase, String ubicacion, Usuario usuarioId) throws Exception {
        Quincho quincho = new Quincho();
        quincho.setNombre(nombre);
        quincho.setPrecioBase(precioBase);
        quincho.setUbicacion(ubicacion);
        quincho.setUsuario(usuarioId);
        quinchoRepository.save(quincho);
    }

    @Override
    public void validar(String nombre, float precioBase, String ubicacion) throws Exception {

        if (nombre == null || nombre.isEmpty()) {
            throw new MyException("el nombre no puede ser nulo o estar vacio");
        }

        if (ubicacion == null || ubicacion.isEmpty()) {
            throw new MyException("el ubicacion no puede ser nulo o estar vacio");
        }
    }

    @Override
    @Transactional
    public List<Quincho> devolverQuinchosDePropietario(Long idUsuario) throws Exception {
        return quinchoRepository.buscarPorPropietario(idUsuario);
    }

    @Override
    @Transactional
    public void actualizar(Long id, String nombre, float precioBase, String ubicacion) throws Exception {
        validar(nombre, precioBase, ubicacion);
        Optional<Quincho> respuesta = quinchoRepository.findById(id);

        if (respuesta.isPresent()) {
            Quincho quincho = respuesta.get();
            quincho.setNombre(nombre);
            quincho.setPrecioBase(precioBase);
            quincho.setUbicacion(ubicacion);

            quinchoRepository.save(quincho);
        }
    }

    @Override
    @Transactional
    public void eliminar(Long id) throws MyException {
        Optional<Quincho> respuesta = quinchoRepository.findById(id);

        if (respuesta.isPresent()) {
            Quincho quincho = respuesta.get();
            quinchoRepository.delete(quincho);
        } else {
            throw new MyException("no existe este usuario");
        }
    }

}
