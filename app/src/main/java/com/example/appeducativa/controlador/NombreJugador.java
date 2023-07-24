package com.example.appeducativa.controlador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.appeducativa.R;
import com.example.appeducativa.clases.Jugador;
import com.example.appeducativa.clases.Usuario;
import com.example.appeducativa.modeloapi.ApiJugadores;

import org.json.JSONObject;

public class NombreJugador extends AppCompatActivity {

    private TextView txtNombrePl;
    private Button btListo;

    private Jugador jugador;

    private static Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre_jugador);

        txtNombrePl = findViewById(R.id.txtPlayerName);
        btListo = findViewById(R.id.btListo);

        capDatos(usuario);

        btListo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearJugador();
            }
        });
    }

    public void crearJugador(){
        Jugador JugadorEN = new Jugador();
        JugadorEN.setUsuarios(usuario);
        JugadorEN.setNombre(txtNombrePl.toString());
        ApiJugadores.crearJugador(this, JugadorEN, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mostrarMensaje("Jugador "+JugadorEN.getNombre()+" creado correctamente");
                mostrarInicio();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mostrarMensaje("Error en la creacion");
            }
        });
    }

    public void capDatos(Usuario usuario){
        // Mostrar los datos del usuario en la interfaz de usuario

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void mostrarInicio() {
        Intent intent = new Intent(this, MainSesion.class);
        startActivity(intent);
    }
}