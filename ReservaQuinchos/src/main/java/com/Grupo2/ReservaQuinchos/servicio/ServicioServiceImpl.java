package com.Grupo2.ReservaQuinchos.servicio;

import com.Grupo2.ReservaQuinchos.entidades.Servicio;
import com.Grupo2.ReservaQuinchos.repositorio.BaseRepository;
import com.Grupo2.ReservaQuinchos.repositorio.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ServicioServiceImpl extends BaseServiceImpl<Servicio, Long>{
    @Autowired
    ServicioRepository servicioRepository;

    public ServicioServiceImpl(BaseRepository<Servicio, Long> baseRepository) {super(baseRepository);}
}
