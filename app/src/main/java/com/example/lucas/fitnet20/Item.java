package com.example.lucas.fitnet20;

/**
 * Created by santiago on 04/06/17.
 */

public class Item {
    private int imagen;
    private String titulo;
    private String descripcion;

    public Item(String descripcion) {
        this.descripcion = descripcion;
    }

    public Item() {

    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
