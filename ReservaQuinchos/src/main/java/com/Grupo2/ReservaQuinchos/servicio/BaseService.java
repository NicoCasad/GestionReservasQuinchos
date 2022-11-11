package com.Grupo2.ReservaQuinchos.servicio;

import com.Grupo2.ReservaQuinchos.entidades.Base;
import java.io.Serializable;
import java.util.List;

public interface BaseService<E extends Base, ID extends Serializable> {

    public E findByID(ID id) throws Exception;
    public E save(E entity) throws Exception;
    public List<E> findAll() throws Exception;
    public E update(ID ig, E entity) throws Exception;
    public boolean delete(ID id) throws Exception;
}

