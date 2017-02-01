package com.jjv.examenjson_2016.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by javi0 on 01/02/2017.
 */

public class Filmografia implements Parcelable {
    private String fecha;
    private String nombre;

    public Filmografia() {
    }

    public Filmografia(String nombre,String fecha) {

        this.nombre = nombre;
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(fecha);
    }

    protected Filmografia(Parcel in) {

        nombre = in.readString();
        fecha = in.readString();
    }

    public static final Creator<Filmografia> CREATOR = new Creator<Filmografia>() {
        @Override
        public Filmografia createFromParcel(Parcel in) {
            return new Filmografia(in);
        }

        @Override
        public Filmografia[] newArray(int size) {
            return new Filmografia[size];
        }
    };
}
