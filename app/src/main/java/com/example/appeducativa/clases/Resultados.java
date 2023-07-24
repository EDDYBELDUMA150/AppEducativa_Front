package com.example.appeducativa.clases;

import java.sql.Timestamp;
import java.util.Date;

public class Resultados {
    private int id_resultado;
    private int re_puntaje;
    private Timestamp re_fecha;
    private Actividad actividad;

    public Resultados() {
    }

    public Resultados(int re_puntaje, Actividad actividad) {
        this.re_puntaje = re_puntaje;
        this.actividad = actividad;
    }

    public int getId_resultado() {
        return id_resultado;
    }

    public void setId_resultado(int id_resultado) {
        this.id_resultado = id_resultado;
    }

    public int getRe_puntaje() {
        return re_puntaje;
    }

    public void setRe_puntaje(int re_puntaje) {
        this.re_puntaje = re_puntaje;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Timestamp getRe_fecha() {
        return re_fecha;
    }

    public void setRe_fecha(Timestamp re_fecha) {
        this.re_fecha = re_fecha;
    }
}
