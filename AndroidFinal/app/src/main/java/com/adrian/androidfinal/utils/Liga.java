package com.adrian.androidfinal.utils;

import java.io.Serializable;

public class Liga implements Serializable {
    String nombre,id;

    public Liga(String nombre,String id) {
        this.nombre = nombre;
        this.id=id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
