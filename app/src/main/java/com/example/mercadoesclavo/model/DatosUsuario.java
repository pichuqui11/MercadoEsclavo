package com.example.mercadoesclavo.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


public class DatosUsuario {

    private String email;
    private String nombre;
    private String apellido;
    private String metodoRegistro;
    private String id;

    public String getMetodoRegistro() {
        return metodoRegistro;
    }

    public void setMetodoRegistro(String metodoRegistro) {
        this.metodoRegistro = metodoRegistro;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DatosUsuario(String email, String nombre, String apellido, String metodoRegistro, String id) {
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.metodoRegistro = metodoRegistro;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


}
