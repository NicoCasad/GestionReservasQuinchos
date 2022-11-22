package com.Grupo2.ReservaQuinchos.repositorio;

import com.Grupo2.ReservaQuinchos.entidades.FotoQuincho;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface FotoQuinchoRepository extends JpaRepository<FotoQuincho, Long>{
    
    @Query("SELECT l FROM FotoQuincho l WHERE l.quincho.id = :id")
    public List<FotoQuincho> buscarPorIDquincho(@Param("id") Long id_quincho);
    
}
