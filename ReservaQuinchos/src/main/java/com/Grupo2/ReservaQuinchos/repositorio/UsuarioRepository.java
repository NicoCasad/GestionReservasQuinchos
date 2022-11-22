package com.Grupo2.ReservaQuinchos.repositorio;

import com.Grupo2.ReservaQuinchos.entidades.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface UsuarioRepository extends BaseRepository<Usuario, Long>{

    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario buscarPorEmail(@Param("email") String email);
    
}
