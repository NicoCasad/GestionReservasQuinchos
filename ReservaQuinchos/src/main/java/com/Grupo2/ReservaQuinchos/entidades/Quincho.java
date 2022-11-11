package com.Grupo2.ReservaQuinchos.entidades;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Quincho extends Base{
    private String nombre;
    private float precioBase;
    private String ubicacion;
}
