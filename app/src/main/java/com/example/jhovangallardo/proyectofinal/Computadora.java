package com.example.jhovangallardo.proyectofinal;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Clase que define el comportamiento de una computadora, principalmente sus atributos principales, funciona como Bean
 */

public class Computadora implements Parcelable{
    private String cliente;
    private String procesador;
    private String almacenamiento;
    private String cargador;
    private String audifonos;
    private String precio;
    private int id;


    public Computadora() {};

    public Computadora(String cliente, String procesador, String almacenamiento, String cargador, String audifonos) {
        super();
        this.cliente = cliente;
        this.procesador = procesador;
        this.almacenamiento = almacenamiento;
        this.cargador = cargador;
        this.audifonos = audifonos;
    }

    public Computadora(Parcel source) {
        this.cliente = source.readString();
        this.procesador = source.readString();
        this.almacenamiento = source.readString();
        this.cargador = source.readString();
        this.audifonos = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cliente);
        dest.writeString(procesador);
        dest.writeString(almacenamiento);
        dest.writeString(cargador);
        dest.writeString(audifonos);
    }

    public static final Creator<Computadora> CREATOR = new Creator<Computadora>() {

        @Override
        public Computadora createFromParcel(Parcel source) {
            return new Computadora(source);
        }

        @Override
        public Computadora[] newArray(int size) {
            return new Computadora[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public String getCargador() {
        return cargador;
    }

    public void setCargador(String cargador) {
        this.cargador = cargador;
    }

    public String getAudifonos() {
        return audifonos;
    }

    public void setAudifonos(String audifonos) {
        this.audifonos = audifonos;
    }

    public String toString(){
        return cliente + ", " + procesador + ", " + almacenamiento + ", " + cargador + ", " + audifonos;
    }
}
