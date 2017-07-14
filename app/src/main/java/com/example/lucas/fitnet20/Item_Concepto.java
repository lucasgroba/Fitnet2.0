package com.example.lucas.fitnet20;

import java.io.Serializable;

/**
 * Created by lucas on 13/7/2017.
 */

public class Item_Concepto implements Serializable {
    private int Id;
    private String Nombre;
    private String Descripcion;

    public Item_Concepto(int id, String nombre, String descripcion) {
        Id = id;
        Nombre = nombre;
        Descripcion = descripcion;
    }

    public Item_Concepto() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
