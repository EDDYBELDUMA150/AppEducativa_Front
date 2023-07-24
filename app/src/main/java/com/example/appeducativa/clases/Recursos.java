package com.example.appeducativa.clases;

public class Recursos {
    private int id_recurso;
    private String rec_enlaces;

    public Recursos() {
    }

    public Recursos(int id_recurso, String rec_enlaces) {
        this.id_recurso = id_recurso;
        this.rec_enlaces = rec_enlaces;
    }

    public int getId_recurso() {
        return id_recurso;
    }

    public void setId_recurso(int id_recurso) {
        this.id_recurso = id_recurso;
    }

    public String getRec_enlaces() {
        return rec_enlaces;
    }

    public void setRec_enlaces(String rec_enlaces) {
        this.rec_enlaces = rec_enlaces;
    }
}
