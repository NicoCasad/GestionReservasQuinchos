package com.Grupo2.ReservaQuinchos.entidades;

import com.Grupo2.ReservaQuinchos.enumeraciones.Rol;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Usuario extends Base{
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String contrasena;
    private boolean estadoUsuario;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToOne
    private Imagen imagen;

    @OneToMany
    private List<Devolucion> devoluciones = new ArrayList<>();
}
