package com.example.appeducativa.clases;

public class Tipo_Aprendizaje {
    private int id_tipo_aprend;
    private String tipo_apren_nombre;
    private int tipo_apren;

    public Tipo_Aprendizaje() {
    }

    public Tipo_Aprendizaje(int id_tipo_aprend, String tipo_apren_nombre, int tipo_apren) {
        this.id_tipo_aprend = id_tipo_aprend;
        this.tipo_apren_nombre = tipo_apren_nombre;
        this.tipo_apren = tipo_apren;
    }

    public int getId_tipo_aprend() {
        return id_tipo_aprend;
    }

    public void setId_tipo_aprend(int id_tipo_aprend) {
        this.id_tipo_aprend = id_tipo_aprend;
    }

    public String getTipo_apren_nombre() {
        return tipo_apren_nombre;
    }

    public void setTipo_apren_nombre(String tipo_apren_nombre) {
        this.tipo_apren_nombre = tipo_apren_nombre;
    }

    public int getTipo_apren() {
        return tipo_apren;
    }

    public void setTipo_apren(int tipo_apren) {
        this.tipo_apren = tipo_apren;
    }
}
