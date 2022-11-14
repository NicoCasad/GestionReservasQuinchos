package com.Grupo2.ReservaQuinchos.servicio;

import com.Grupo2.ReservaQuinchos.entidades.Base;
import com.Grupo2.ReservaQuinchos.repositorio.BaseRepository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {
    protected BaseRepository<E, ID> baseRepository;

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public E findByID(ID id) throws Exception {
        try {
            Optional<E> resultado = baseRepository.findById(id);
            return resultado.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {

        try {
            baseRepository.save(entity);
            return entity;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<E> findAll() throws Exception {
        try {
            List<E> e = baseRepository.findAll();
            return e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}