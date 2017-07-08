package com.example.lucas.fitnet20;

/**
 * Created by lucas on 6/7/2017.
 */

public class Item_Actividad extends Item {
    private int id;
    private String Actividad;
    private Float Precio;
    private int Dias;
    private int Perdido;

    public int getDias() {
        return Dias;
    }

    public void setDias(int dias) {
        Dias = dias;
    }

    public int getPerdido() {
        return Perdido;
    }

    public void setPerdido(int perdido) {
        Perdido = perdido;
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
