package com.Grupo2.ReservaQuinchos.entidades;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Devolucion extends Base{
    private int puntuacion;
    private String testimonio;
    @ManyToOne
    private Usuario usuario;
}
