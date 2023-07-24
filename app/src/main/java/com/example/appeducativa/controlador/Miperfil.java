package com.example.appeducativa.controlador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appeducativa.R;
import com.example.appeducativa.clases.Jugador;
import com.example.appeducativa.clases.Usuario;

public class Miperfil extends AppCompatActivity {

    private TextView Nombre;
    private TextView Correo;
    private TextView Fecha_Nac;
    private TextView Fecha_Inic;
    private TextView NivelAcade;
    private TextView NombreJugador;

    private static Usuario usuario;

    private Jugador jugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miperfil);

        Nombre = findViewById(R.id.txtPerfilNombre);
        Correo = findViewById(R.id.txtPerfilCorreo);
        Fecha_Nac = findViewById(R.id.txtPerfilFechaNac);
        Fecha_Inic = findViewById(R.id.txtPerfilFechaInic);
        NivelAcade = findViewById(R.id.txtPerfilNivelAc);
        NombreJugador = findViewById(R.id.txtPerfilNombreJuga);

        capDatos(usuario);
    }

    public void capDatos(Usuario usuario){
        // Mostrar los datos del usuario en la interfaz de usuario
        Nombre.setText(usuario.getUsu_nombre());
        Correo.setText(usuario.getUsu_correo());
        Fecha_Nac.setText(usuario.getUsu_fechaNacimiento());
        Fecha_Inic.setText(usuario.getUsu_fecha_inic());
        NivelAcade.setText(usuario.getUsu_nivelacademico());
    }

    public String jugadorNombre(Jugador jugador){
        return String.valueOf(jugador.getNombre());
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}