package com.jjv.examenjson_2016.Objects;

import java.util.ArrayList;

/**
 * Created by javi0 on 01/02/2017.
 */

public class Actor {
    private String nombre;
    private String Descripcion;
    private String foto;
    private ArrayList <Filmografia> filmografia;



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public ArrayList<Filmografia> getFilmografia() {
        return filmografia;
    }

    public void setFilmografia(ArrayList<Filmografia> filmografia) {
        this.filmografia = filmografia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
