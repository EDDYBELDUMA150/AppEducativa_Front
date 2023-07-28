package com.example.appeducativa.clases;

public class Recursos {
    private int id_recurso;
    private String rec_enlaces;

    private String rec_lec;

    public Recursos() {
    }

    public Recursos(int id_recurso, String rec_enlaces, String rec_lec) {
        this.id_recurso = id_recurso;
        this.rec_enlaces = rec_enlaces;
        this.rec_lec = rec_lec;
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

    public String getRec_lec() {
        return rec_lec;
    }

    public void setRec_lec(String rec_lec) {
        this.rec_lec = rec_lec;
    }
}
