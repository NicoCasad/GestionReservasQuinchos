package com.Grupo2.ReservaQuinchos.entidades;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Quincho extends Base{
    private String nombre;
    private float precioBase;
    private String ubicacion;

    @ManyToOne
    private Usuario usuario;

    @OneToMany
    private List<Devolucion> devoluciones;

    @ManyToMany
    private List<Servicio> servicios;
}
