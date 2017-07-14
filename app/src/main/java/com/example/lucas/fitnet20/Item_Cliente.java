package com.example.lucas.fitnet20;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by santiago on 13/07/17.
 */

public class Item_Cliente implements Serializable {
    private Bitmap imagen;
    private String nombre;
    private String fecha_nac;
    private String cedula;
    private String correo;
    private String celular;
    private String mutualista;
    private String sexo;

    public Item_Cliente() {
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMutualista() {
        return mutualista;
    }

    public void setMutualista(String mutualista) {
        this.mutualista = mutualista;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
