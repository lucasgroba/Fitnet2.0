package com.example.lucas.fitnet20;

import java.io.Serializable;

/**
 * Created by lucas on 14/7/2017.
 */

public class Item_Usuario implements Serializable {
    private int id;
    private String Nick;
    private String Password;
    private String Nombre;
    private byte[] imagen;

    public Item_Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return Nick;
    }

    public void setNick(String nick) {
        Nick = nick;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
