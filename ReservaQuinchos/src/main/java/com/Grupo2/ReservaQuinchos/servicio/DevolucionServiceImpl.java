package com.Grupo2.ReservaQuinchos.servicio;

import com.Grupo2.ReservaQuinchos.entidades.Devolucion;
import com.Grupo2.ReservaQuinchos.repositorio.BaseRepository;
import com.Grupo2.ReservaQuinchos.repositorio.DevolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DevolucionServiceImpl extends BaseServiceImpl<Devolucion, Long>{

    @Autowired
    DevolucionRepository devolucionRepository;

    public DevolucionServiceImpl(BaseRepository<Devolucion, Long> baseRepository) {super(baseRepository);}
}
