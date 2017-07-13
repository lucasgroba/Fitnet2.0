package com.example.lucas.fitnet20;

import java.io.Serializable;

/**
 * Created by lucas on 9/7/2017.
 */

public class Item_Articulo implements Serializable {
    private int id;
    private String Nombre;
    private float Precio;

    public Item_Articulo(String nombre, float precio) {
        Nombre = nombre;
        Precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item_Articulo() {
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float precio) {
        Precio = precio;
    }
}
