package com.example.appeducativa.clases;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Usuario implements Serializable {
    private int id_usuario;
    private String usu_nombre;
    private String usu_password;
    private String usu_correo;
    private String usu_nivelacademico;
    private String usu_fechaNacimiento;

    private String usu_fecha_inic;
    private Roles id_rol;

    public Usuario() {
    }

    public Usuario(String usu_nombre, String usu_password, String usu_correo, String usu_nivelacademico, String usu_fechaNacimiento, Roles id_rol) {
        this.usu_nombre = usu_nombre;
        this.usu_password = usu_password;
        this.usu_correo = usu_correo;
        this.usu_nivelacademico = usu_nivelacademico;
        this.usu_fechaNacimiento = usu_fechaNacimiento;
        this.id_rol = id_rol;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsu_nombre() {
        return usu_nombre;
    }

    public void setUsu_nombre(String usu_nombre) {
        this.usu_nombre = usu_nombre;
    }

    public String getUsu_password() {
        return usu_password;
    }

    public void setUsu_password(String usu_password) {
        this.usu_password = usu_password;
    }

    public String getUsu_correo() {
        return usu_correo;
    }

    public void setUsu_correo(String usu_correo) {
        this.usu_correo = usu_correo;
    }

    public String getUsu_nivelacademico() {
        return usu_nivelacademico;
    }

    public void setUsu_nivelacademico(String usu_nivelacademico) {
        this.usu_nivelacademico = usu_nivelacademico;
    }

    public String getUsu_fechaNacimiento() {
        return usu_fechaNacimiento;
    }

    public void setUsu_fechaNacimiento(String usu_fechaNacimiento) {
        this.usu_fechaNacimiento = usu_fechaNacimiento;
    }

    public Roles getId_rol() {
        return id_rol;
    }

    public void setId_rol(Roles id_rol) {
        this.id_rol = id_rol;
    }

    public String getUsu_fecha_inic() {
        return usu_fecha_inic;
    }

    public void setUsu_fecha_inic(String usu_fecha_inic) {
        this.usu_fecha_inic = usu_fecha_inic;
    }
}
