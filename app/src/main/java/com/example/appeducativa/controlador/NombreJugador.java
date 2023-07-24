package com.example.appeducativa.controlador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.appeducativa.R;

public class NombreJugador extends AppCompatActivity {

    private TextView txtNombrePl;
    private Button btListo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre_jugador);

        txtNombrePl = findViewById(R.id.txtPlayerName);
        btListo = findViewById(R.id.btListo);


    }
}