package com.example.lucas.fitnet20;

import java.io.Serializable;

/**
 * Created by lucas on 6/7/2017.
 */

public class Item_Actividad extends Item implements Serializable{
    private int id;
    private String Actividad;
    private Float Precio;
    private int Dias;
    private int Periodo;

    public int getDias() {
        return Dias;
    }

    public void setDias(int dias) {
        Dias = dias;
    }

    public int getPeriodo() {
        return Periodo;
    }

    public void setPeriodo(int periodo) {
        Periodo = periodo;
    }

    public Item_Actividad(){
        super();
    }

    public String getActividad() {
        return Actividad;
    }

    public void setActividad(String actividad) {
        Actividad = actividad;
    }

    public Float getPrecio() {
        return Precio;
    }

    public void setPrecio(Float precio) {
        Precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
