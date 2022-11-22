package com.Grupo2.ReservaQuinchos.entidades;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class FotoQuincho extends Base{
    private String nombre;
    @ManyToOne
    private Quincho quincho;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Quincho getQuincho() {
        return quincho;
    }

    public void setQuincho(Quincho quincho) {
        this.quincho = quincho;
    }
    
}
