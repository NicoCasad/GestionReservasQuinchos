package com.Grupo2.ReservaQuinchos.servicio;

import com.Grupo2.ReservaQuinchos.entidades.Quincho;
import com.Grupo2.ReservaQuinchos.repositorio.BaseRepository;
import com.Grupo2.ReservaQuinchos.repositorio.QuinchoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class QuinchoServiceImpl extends BaseServiceImpl<Quincho, Long>{

    @Autowired
    QuinchoRepository quinchoRepository;

    public QuinchoServiceImpl(BaseRepository<Quincho, Long> baseRepository) {super(baseRepository);}
}
