package com.example.ejercicio6;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pelicula implements Parcelable {

    String titulo, director, sinopsis, sala, idYoutube;
    int clasi, portada, duracion;
    Date fecha;
    boolean favorita;

    private SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");

    public Pelicula(String titulo, String director, int duracion, Date fecha, String sala, int clasi, int portada) {
        this.clasi = clasi;
        this.director = director;
        this.fecha = fecha;
        this.portada = portada;
        this.sala = sala;
        this.titulo = titulo;
        this.duracion = duracion;
        this.favorita = false;
    }

    protected Pelicula(Parcel in) {
        titulo = in.readString();
        director = in.readString();
        sinopsis = in.readString();
        sala = in.readString();
        idYoutube = in.readString();
        clasi = in.readInt();
        portada = in.readInt();
        duracion = in.readInt();
        favorita = in.readByte() != 0;
    }

    public static final Creator<Pelicula> CREATOR = new Creator<Pelicula>() {
        @Override
        public Pelicula createFromParcel(Parcel in) {
            return new Pelicula(in);
        }

        @Override
        public Pelicula[] newArray(int size) {
            return new Pelicula[size];
        }
    };

    public String getIdYoutube() {
        return idYoutube;
    }

    public void setIdYoutube(String idYoutube) {
        this.idYoutube = idYoutube;
    }

    public int getClasi() {
        return clasi;
    }

    public void setClasi(int clasi) {
        this.clasi = clasi;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public boolean getFavorita() {
        return favorita;
    }

    public void setFavorita(Boolean favorita) {
        this.favorita = favorita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPortada() {
        return portada;
    }

    public void setPortada(int portada) {
        this.portada = portada;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(titulo);
        parcel.writeString(director);
        parcel.writeString(sinopsis);
        parcel.writeString(sala);
        parcel.writeString(idYoutube);
        parcel.writeInt(clasi);
        parcel.writeInt(portada);
        parcel.writeInt(duracion);
        parcel.writeByte((byte) (favorita ? 1 : 0));
    }
}
