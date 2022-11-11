package com.Grupo2.ReservaQuinchos.servicio;

import com.Grupo2.ReservaQuinchos.entidades.Reserva;
import com.Grupo2.ReservaQuinchos.repositorio.BaseRepository;
import com.Grupo2.ReservaQuinchos.repositorio.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ReservaServiceImpl extends BaseServiceImpl<Reserva, Long>{

    @Autowired
    ReservaRepository reservaRepository;

    public ReservaServiceImpl(BaseRepository<Reserva, Long> baseRepository) {super(baseRepository);}
}
