package com.example.appeducativa.clases;

import java.sql.Time;
import java.util.Date;

public class Progreso {
    private int id_progress;
    private int prog_puntaje_total;
    private int prog_nivel;
    private Time prog_hora_promd;
    private String prog_fecha_init;
    private Date prog_fehca_final;

    public Progreso() {
    }

    public Progreso(int id_progress, int prog_puntaje_total, int prog_nivel, Time prog_hora_promd, String prog_fecha_init, Date prog_fehca_final) {
        this.id_progress = id_progress;
        this.prog_puntaje_total = prog_puntaje_total;
        this.prog_nivel = prog_nivel;
        this.prog_hora_promd = prog_hora_promd;
        this.prog_fecha_init = prog_fecha_init;
        this.prog_fehca_final = prog_fehca_final;
    }

    public int getId_progress() {
        return id_progress;
    }

    public void setId_progress(int id_progress) {
        this.id_progress = id_progress;
    }

    public int getProg_puntaje_total() {
        return prog_puntaje_total;
    }

    public void setProg_puntaje_total(int prog_puntaje_total) {
        this.prog_puntaje_total = prog_puntaje_total;
    }

    public int getProg_nivel() {
        return prog_nivel;
    }

    public void setProg_nivel(int prog_nivel) {
        this.prog_nivel = prog_nivel;
    }

    public Time getProg_hora_promd() {
        return prog_hora_promd;
    }

    public void setProg_hora_promd(Time prog_hora_promd) {
        this.prog_hora_promd = prog_hora_promd;
    }

    public String getProg_fecha_init() {
        return prog_fecha_init;
    }

    public void setProg_fecha_init(String prog_fecha_init) {
        this.prog_fecha_init = prog_fecha_init;
    }

    public Date getProg_fehca_final() {
        return prog_fehca_final;
    }

    public void setProg_fehca_final(Date prog_fehca_final) {
        this.prog_fehca_final = prog_fehca_final;
    }
}
