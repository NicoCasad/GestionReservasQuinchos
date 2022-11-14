package com.Grupo2.ReservaQuinchos.entidades;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Quincho quincho;

    @ManyToMany
    private List<Servicio> servicios;
}
