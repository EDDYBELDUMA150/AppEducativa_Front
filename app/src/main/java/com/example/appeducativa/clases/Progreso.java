package com.example.appeducativa.clases;

import java.util.Date;

public class Progreso {
    private int id_progress;
    private int prog_puntaje_total;
    private Date prog_fecha_init;
    private Date prog_fehca_final;
    private int player_id;

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

    public Date getProg_fecha_init() {
        return prog_fecha_init;
    }

    public void setProg_fecha_init(Date prog_fecha_init) {
        this.prog_fecha_init = prog_fecha_init;
    }

    public Date getProg_fehca_final() {
        return prog_fehca_final;
    }

    public void setProg_fehca_final(Date prog_fehca_final) {
        this.prog_fehca_final = prog_fehca_final;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }
}
