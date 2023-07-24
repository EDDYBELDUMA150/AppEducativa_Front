package com.example.appeducativa.clases;

public class Niveles {
    private int id_nivel;
    private int nv_numero_nivel;

    public Niveles() {
    }

    public Niveles(int id_nivel, int nv_numero_nivel) {
        this.id_nivel = id_nivel;
        this.nv_numero_nivel = nv_numero_nivel;
    }

    public int getId_nivel() {
        return id_nivel;
    }

    public void setId_nivel(int id_nivel) {
        this.id_nivel = id_nivel;
    }

    public int getNv_numero_nivel() {
        return nv_numero_nivel;
    }

    public void setNv_numero_nivel(int nv_numero_nivel) {
        this.nv_numero_nivel = nv_numero_nivel;
    }
}
