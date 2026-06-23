package com.example.shared.Model;

import androidx.annotation.NonNull;

public class Nota {
    int idNota;
    String titulo;
    String txt;

    public Nota(String titulo, String txt) {
        this.titulo = titulo;
        this.txt = txt;
    }

    public Nota(int idNota, String titulo, String txt) {
        this.idNota = idNota;
        this.titulo = titulo;
        this.txt = txt;
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    @NonNull
    @Override
    public String toString() {
        return this.titulo;
    }
}
