package com.Grupo2.ReservaQuinchos.repositorio;

import com.Grupo2.ReservaQuinchos.entidades.Quincho;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface QuinchoRepository extends BaseRepository<Quincho, Long> {

    @Query("SELECT l FROM Quincho l WHERE l.usuario.id = :id")
    public List<Quincho> buscarPorPropietario(@Param("id") Long id);
}
