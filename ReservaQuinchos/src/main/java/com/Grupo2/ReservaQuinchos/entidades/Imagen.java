package com.Grupo2.ReservaQuinchos.entidades;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Imagen extends Base{
    private String mime;
    private String nombre;
}
