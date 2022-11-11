package com.Grupo2.ReservaQuinchos.servicio;

import com.Grupo2.ReservaQuinchos.entidades.Usuario;
import com.Grupo2.ReservaQuinchos.repositorio.BaseRepository;
import com.Grupo2.ReservaQuinchos.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long>{

    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository) {super(baseRepository);}
}
