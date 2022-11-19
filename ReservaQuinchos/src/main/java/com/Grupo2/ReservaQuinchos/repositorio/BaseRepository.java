package com.Grupo2.ReservaQuinchos.repositorio;

import com.Grupo2.ReservaQuinchos.entidades.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@NoRepositoryBean
public interface BaseRepository <E extends Base, ID extends Serializable> extends JpaRepository<E, ID> {

}