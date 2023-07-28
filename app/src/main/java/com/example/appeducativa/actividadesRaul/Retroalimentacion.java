package com.example.appeducativa.actividadesRaul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appeducativa.R;
import com.example.appeducativa.controlador.Miperfil;

public class Retroalimentacion extends AppCompatActivity {

    private TextView txtResultado;
    private TextView txtRetroalimentacion;
    private TextView txtResultados;
    private Button btSiguiente;
    private Button btAnterior;
    private int[] images = {R.drawable.bici, R.drawable.carro, R.drawable.monorail, R.drawable.tren};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retroalimentacion);

        txtResultado = findViewById(R.id.txtBuenoMalo);
        txtRetroalimentacion = findViewById(R.id.TxtRetroalimentacion);
        txtResultados = findViewById(R.id.txtResultado);
        btSiguiente = findViewById(R.id.btnRepito);

        // Obtener los datos pasados a través del Intent
        boolean respuestaCorrecta = getIntent().getBooleanExtra("respuestaCorrecta", false);
        int puntaje = getIntent().getIntExtra("puntaje", 0);
        int actividad = getIntent().getIntExtra("actividad", 1);

        // Actualizar los TextViews según los datos obtenidos
        if (respuestaCorrecta) {
            txtResultado.setText("¡Correcto!");
            txtResultado.setTextColor(Color.GREEN); // Cambiar color del texto a verde
            txtRetroalimentacion.setText("¡Felicitaciones, has acertado!");
            txtResultados.setText("Puntaje: " + puntaje);
            txtResultados.setTextColor(Color.BLUE); // Cambiar color del texto a verde
            btSiguiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (actividad == 1) {
                        mostrarActividad2();
                    } else if (actividad == 2) {
                        mostrarActividad3();
                    } else {
                        mostrarMensaje("Has terminado las activiades de logica!");
                    }
                }
            });
        } else {
            txtResultado.setText("¡Incorrecto!");
            txtResultado.setTextColor(Color.RED); // Cambiar color del texto a rojo
            txtRetroalimentacion.setText("La respuesta era incorrecta.");
            txtResultados.setText("Puntaje: " + puntaje);
            txtResultados.setTextColor(Color.BLUE); // Cambiar color del texto a rojo
            btSiguiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (actividad == 1) {
                        mostrarActividad2();
                    } else if (actividad == 2) {
                        mostrarActividad3();
                    } else {
                        mostrarMensaje("Has terminado las activiades de logica!");
                    }
                }
            });
        }
    }

    private void mostrarActividad2() {
        Intent intent = new Intent(this, ActividadDos.class);
        startActivity(intent);
    }

    private void mostrarActividad3() {
        Intent intent = new Intent(this, ActividadDosPalabras.class);
        startActivity(intent);
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

}