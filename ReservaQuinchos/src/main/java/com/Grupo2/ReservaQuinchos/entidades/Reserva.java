package com.Grupo2.ReservaQuinchos.entidades;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Data
public class Reserva extends Base{

    @Temporal(TemporalType.DATE)
    private Date fechaCreacionReserva;
    @Temporal(TemporalType.DATE)
    private Date fechaDesde;
    @Temporal(TemporalType.DATE)
    private Date fechaHasta;
    private float precioTotal;
    private boolean estadoReserva;
}
