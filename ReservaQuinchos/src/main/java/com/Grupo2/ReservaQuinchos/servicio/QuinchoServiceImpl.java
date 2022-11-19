package com.Grupo2.ReservaQuinchos.servicio;

import com.Grupo2.ReservaQuinchos.entidades.Quincho;
import com.Grupo2.ReservaQuinchos.repositorio.BaseRepository;
import com.Grupo2.ReservaQuinchos.repositorio.QuinchoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuinchoServiceImpl extends BaseServiceImpl<Quincho, Long> implements QuinchoService{

    @Autowired
    QuinchoRepository quinchoRepository;

    public QuinchoServiceImpl(BaseRepository<Quincho, Long> baseRepository) {super(baseRepository);}
}
