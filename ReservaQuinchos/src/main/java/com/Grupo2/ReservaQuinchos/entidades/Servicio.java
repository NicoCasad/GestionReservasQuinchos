package com.Grupo2.ReservaQuinchos.entidades;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Data
public class Servicio extends Base{
    private String descripcion;
    private String nombre;
    private float precio;

    @OneToOne
    private Imagen imagen;
}
