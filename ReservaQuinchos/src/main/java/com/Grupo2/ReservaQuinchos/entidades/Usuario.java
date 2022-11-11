package com.Grupo2.ReservaQuinchos.entidades;

import com.Grupo2.ReservaQuinchos.enumeraciones.Rol;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Usuario extends Base{
    private String nombre;
    private String mail;
    private String contrasena;
    private int telefono;
    private boolean estadoUsuario;

    @Enumerated(EnumType.STRING)
    private Rol rol;
}
