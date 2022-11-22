package com.Grupo2.ReservaQuinchos.servicio;

import com.Grupo2.ReservaQuinchos.entidades.Imagen;
import com.Grupo2.ReservaQuinchos.entidades.Usuario;
import com.Grupo2.ReservaQuinchos.enumeraciones.Rol;
import com.Grupo2.ReservaQuinchos.excepciones.MyException;
import com.Grupo2.ReservaQuinchos.repositorio.BaseRepository;
import com.Grupo2.ReservaQuinchos.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UserDetailsService, UsuarioService{

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ImagenService imagenService;

    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository) {super(baseRepository);}

    @Override
    @Transactional
    public void registrar(MultipartFile imagen, String telefono, String email, String nombre, String apellido, String rol, String contrasena) throws Exception {
        validar(nombre, apellido, email, contrasena);
        Usuario usuario = new Usuario();

        usuario.setEstadoUsuario(true);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setTelefono(telefono);
        usuario.setEmail(email);
        usuario.setContrasena(new BCryptPasswordEncoder().encode(contrasena));

        if (rol.equals(Rol.CLIENTE.toString())){
            usuario.setRol(Rol.CLIENTE);
        }else if (rol.equals(Rol.PROPIETARIO.toString())){
            usuario.setRol(Rol.PROPIETARIO);
        }
        
        Imagen imagenUsuario = imagenService.guardar(imagen);
        usuario.setImagen(imagenUsuario);

        usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.buscarPorEmail(email);

        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());

            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("usuariosession", usuario);

            return new User(usuario.getEmail(), usuario.getContrasena(), permisos);
        } else {
            return null;
        }
    }

    @Override
    public void validar(String nombre, String apellido, String email, String contrasena) throws Exception{

        if (nombre == null || nombre.isEmpty()){
            throw new MyException("el nombre no puede ser nulo o estar vacio");
        }
        if (apellido == null || apellido.isEmpty()){
            throw new MyException("el apellido no puede ser nulo o estar vacio");
        }
        if (contrasena == null || contrasena.isEmpty()){
            throw new MyException("el contrasena no puede ser nulo o estar vacio");
        }
        if (email == null || email.isEmpty()){
            throw new MyException("el email no puede ser nulo o estar vacio");
        }
        Usuario usuario = usuarioRepository.buscarPorEmail(email);
        if (usuario != null){
            throw new MyException("El mail ya esta registrado");
        }
    }
    @Override
    public void validarActualizar(String nombre, String apellido, String email, String contrasena, Usuario usuario) throws Exception{

        if (nombre == null || nombre.isEmpty()){
            throw new MyException("el nombre no puede ser nulo o estar vacio");
        }
        if (apellido == null || apellido.isEmpty()){
            throw new MyException("el apellido no puede ser nulo o estar vacio");
        }
        if (contrasena == null || contrasena.isEmpty()){
            throw new MyException("el contrasena no puede ser nulo o estar vacio");
        }
        if (email == null || email.isEmpty()){
            throw new MyException("el email no puede ser nulo o estar vacio");
        }
        if (!usuario.getEmail().equals(email)){
            Usuario usuario1 = usuarioRepository.buscarPorEmail(email);
            if (usuario != null){
                throw new MyException("El mail ya esta registrado");
            }
        }
    }

    @Override
    @Transactional
    public void actualizar(MultipartFile imagen, Long id, String telefono, String nombre, String apellido, String email, String contrasena) throws Exception{

        Optional<Usuario> respuesta = usuarioRepository.findById(id);
        if (respuesta.isPresent()){
            Usuario usuario = respuesta.get();
            validarActualizar(nombre, apellido, email, contrasena, usuario);
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setTelefono(telefono);
            usuario.setEmail(email);
            usuario.setContrasena(new BCryptPasswordEncoder().encode(contrasena));

            Long idImagen = null;

            if (usuario.getImagen() != null) {
                idImagen = usuario.getImagen().getId();
            }

            Imagen imagenUsuario = imagenService.actualizar(imagen, idImagen);

            usuario.setImagen(imagenUsuario);

            usuarioRepository.save(usuario);
        }
    }

    @Override
    @Transactional
    public void eliminar(Long id) throws MyException{
        Optional<Usuario> respuesta = usuarioRepository.findById(id);

        if (respuesta.isPresent()){
            Usuario usuario = respuesta.get();
            usuarioRepository.delete(usuario);
        }else{
            throw new MyException("no existe este usuario");
        }
    }
}
