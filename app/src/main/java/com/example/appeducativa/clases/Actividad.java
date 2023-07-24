package com.example.appeducativa.clases;

import java.io.Serializable;

public class Actividad implements Serializable {
    private int id_activ;
    private String act_nombre;
    private String act_descripcion;
    private String act_dificultad;
    private int act_puntaje_max;
    private int act_puntaje_min;
    private int act_puntaje_alcanzado;
    private String act_aprendizaje;
    private boolean act_estado;
    private String act_respuesta;

    private Recursos recursos;

    private Niveles niveles;

    private Tipo_Aprendizaje tipo_aprendizaje;
    public Actividad() {
    }

    public Actividad(String act_nombre, String act_descripcion, String act_dificultad, int act_puntaje_max, int act_puntaje_min, int act_puntaje_alcanzado, String act_aprendizaje, boolean act_estado, String act_respuesta, Recursos recursos, Niveles niveles, Tipo_Aprendizaje tipo_aprendizaje) {
        this.act_nombre = act_nombre;
        this.act_descripcion = act_descripcion;
        this.act_dificultad = act_dificultad;
        this.act_puntaje_max = act_puntaje_max;
        this.act_puntaje_min = act_puntaje_min;
        this.act_puntaje_alcanzado = act_puntaje_alcanzado;
        this.act_aprendizaje = act_aprendizaje;
        this.act_estado = act_estado;
        this.act_respuesta = act_respuesta;
        this.recursos = recursos;
        this.niveles = niveles;
        this.tipo_aprendizaje = tipo_aprendizaje;
    }

    public int getId_activ() {
        return id_activ;
    }

    public void setId_activ(int id_activ) {
        this.id_activ = id_activ;
    }

    public String getAct_nombre() {
        return act_nombre;
    }

    public void setAct_nombre(String act_nombre) {
        this.act_nombre = act_nombre;
    }

    public String getAct_descripcion() {
        return act_descripcion;
    }

    public void setAct_descripcion(String act_descripcion) {
        this.act_descripcion = act_descripcion;
    }

    public String getAct_dificultad() {
        return act_dificultad;
    }

    public void setAct_dificultad(String act_dificultad) {
        this.act_dificultad = act_dificultad;
    }

    public int getAct_puntaje_max() {
        return act_puntaje_max;
    }

    public void setAct_puntaje_max(int act_puntaje_max) {
        this.act_puntaje_max = act_puntaje_max;
    }

    public int getAct_puntaje_min() {
        return act_puntaje_min;
    }

    public void setAct_puntaje_min(int act_puntaje_min) {
        this.act_puntaje_min = act_puntaje_min;
    }

    public int getAct_puntaje_alcanzado() {
        return act_puntaje_alcanzado;
    }

    public void setAct_puntaje_alcanzado(int act_puntaje_alcanzado) {
        this.act_puntaje_alcanzado = act_puntaje_alcanzado;
    }

    public String getAct_aprendizaje() {
        return act_aprendizaje;
    }

    public void setAct_aprendizaje(String act_aprendizaje) {
        this.act_aprendizaje = act_aprendizaje;
    }

    public boolean isAct_estado() {
        return act_estado;
    }

    public void setAct_estado(boolean act_estado) {
        this.act_estado = act_estado;
    }

    public String getAct_respuesta() {
        return act_respuesta;
    }

    public void setAct_respuesta(String act_respuesta) {
        this.act_respuesta = act_respuesta;
    }

    public Recursos getRecursos() {
        return recursos;
    }

    public void setRecursos(Recursos recursos) {
        this.recursos = recursos;
    }

    public Niveles getNiveles() {
        return niveles;
    }

    public void setNiveles(Niveles niveles) {
        this.niveles = niveles;
    }

    public Tipo_Aprendizaje getTipo_aprendizaje() {
        return tipo_aprendizaje;
    }

    public void setTipo_aprendizaje(Tipo_Aprendizaje tipo_aprendizaje) {
        this.tipo_aprendizaje = tipo_aprendizaje;
    }
}
