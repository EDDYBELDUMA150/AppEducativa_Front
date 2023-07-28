package com.example.appeducativa.clases;

public class Progreso_Aprendizaje {
    private int id_prog_aprend;
    private String progApren_nombre;
    private int progApren_puntaje;
    private Tipo_Aprendizaje id_tipo_apren;
    private Resultados id_resultado;
    private Progreso id_progress;

    public Progreso_Aprendizaje() {
    }

    public Progreso_Aprendizaje(int id_prog_aprend, String progApren_nombre, int progApren_puntaje, Tipo_Aprendizaje id_tipo_apren, Resultados id_resultado, Progreso id_progress) {
        this.id_prog_aprend = id_prog_aprend;
        this.progApren_nombre = progApren_nombre;
        this.progApren_puntaje = progApren_puntaje;
        this.id_tipo_apren = id_tipo_apren;
        this.id_resultado = id_resultado;
        this.id_progress = id_progress;
    }

    public int getId_prog_aprend() {
        return id_prog_aprend;
    }

    public void setId_prog_aprend(int id_prog_aprend) {
        this.id_prog_aprend = id_prog_aprend;
    }

    public String getProgApren_nombre() {
        return progApren_nombre;
    }

    public void setProgApren_nombre(String progApren_nombre) {
        this.progApren_nombre = progApren_nombre;
    }

    public int getProgApren_puntaje() {
        return progApren_puntaje;
    }

    public void setProgApren_puntaje(int progApren_puntaje) {
        this.progApren_puntaje = progApren_puntaje;
    }

    public Tipo_Aprendizaje getId_tipo_apren() {
        return id_tipo_apren;
    }

    public void setId_tipo_apren(Tipo_Aprendizaje id_tipo_apren) {
        this.id_tipo_apren = id_tipo_apren;
    }

    public Resultados getId_resultado() {
        return id_resultado;
    }

    public void setId_resultado(Resultados id_resultado) {
        this.id_resultado = id_resultado;
    }

    public Progreso getId_progress() {
        return id_progress;
    }

    public void setId_progress(Progreso id_progress) {
        this.id_progress = id_progress;
    }
}
