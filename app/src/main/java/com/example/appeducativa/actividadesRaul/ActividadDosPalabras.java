package com.example.appeducativa.actividadesRaul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.appeducativa.R;
import com.example.appeducativa.clases.Actividad;
import com.example.appeducativa.clases.Niveles;
import com.example.appeducativa.clases.Recursos;
import com.example.appeducativa.clases.Tipo_Aprendizaje;
import com.example.appeducativa.modeloapi.ApiActividades;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ActividadDosPalabras extends AppCompatActivity {

    private ImageView imageView;
    private int[] images = {R.drawable.bici, R.drawable.carro, R.drawable.monorail, R.drawable.tren};
    private String[] palabras;
    private List<String> palabrasDisponibles;
    private int score = 0;
    private TextView txtPuntaje;

    private String getResourceName(int resourceId) {
        return getResources().getResourceEntryName(resourceId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_dos_palabras);

        palabras = new String[]{getResourceName(R.drawable.bici), getResourceName(R.drawable.carro), getResourceName(R.drawable.monorail), getResourceName(R.drawable.tren)};

        palabrasDisponibles = new ArrayList<>(Arrays.asList(palabras));

        imageView = findViewById(R.id.imageView2);

        txtPuntaje = findViewById(R.id.txtpuntajesabio);
        TextView txtOrden = findViewById(R.id.txtorden);

        RadioButton radioButton1 = findViewById(R.id.radioButton7);
        RadioButton radioButton2 = findViewById(R.id.radioButton5);
        RadioButton radioButton3 = findViewById(R.id.radioButton3);
        RadioButton radioButton4 = findViewById(R.id.radioButton6);
        Random random = new Random();

        int randomIndex1 = random.nextInt(palabrasDisponibles.size());
        String palabraSeleccionada1 = palabrasDisponibles.get(randomIndex1);
        palabrasDisponibles.remove(randomIndex1);

        int randomIndex2 = random.nextInt(palabrasDisponibles.size());
        String palabraSeleccionada2 = palabrasDisponibles.get(randomIndex2);
        palabrasDisponibles.remove(randomIndex2);

        int randomIndex3 = random.nextInt(palabrasDisponibles.size());
        String palabraSeleccionada3 = palabrasDisponibles.get(randomIndex3);
        palabrasDisponibles.remove(randomIndex3);

        int randomIndex4 = random.nextInt(palabrasDisponibles.size());
        String palabraSeleccionada4 = palabrasDisponibles.get(randomIndex4);
        palabrasDisponibles.remove(randomIndex4);

        radioButton1.setText(palabraSeleccionada1);
        radioButton2.setText(palabraSeleccionada2);
        radioButton3.setText(palabraSeleccionada3);
        radioButton4.setText(palabraSeleccionada4);

        // Obtener el índice seleccionado del Intent
        int selectedImageIndex = getIntent().getIntExtra("selectedImageIndex", -1);

        // Verificar si se proporcionó un índice válido
        if (selectedImageIndex != -1 && selectedImageIndex < images.length) {
            int imageRes = images[selectedImageIndex];
            imageView.setImageResource(imageRes);
        }

        Button btnEnviarRespuesta = findViewById(R.id.btnEnviarRespuesta);
        btnEnviarRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la respuesta seleccionada por el usuario
                String respuesta = obtenerRespuestaSeleccionada();

                // Obtener el nombre de la imagen desde el ImageView
                String nombreImagen = getResourceName(images[selectedImageIndex]);

                // Verificar si la respuesta es correcta
                if (respuesta.equals(nombreImagen)) {
                    // Respuesta correcta
                    Toast.makeText(ActividadDosPalabras.this, "Respuesta correcta", Toast.LENGTH_SHORT).show();
                    score += 1; // Sumar 1 punto por una respuesta correcta
                } else {
                    // Respuesta incorrecta
                    Toast.makeText(ActividadDosPalabras.this, "Respuesta incorrecta", Toast.LENGTH_SHORT).show();
                    score -= 1; // Restar 1 punto por una respuesta incorrecta

                    // Verificar si el puntaje es negativo y establecerlo a cero si es el caso
                    if (score < 0) {
                        score = 0;
                    }
                }

                // Mostrar el puntaje actualizado en el TextView txtPuntaje
                txtPuntaje.setText("Puntaje: " + score);

                // Obtener el puntaje desde el TextView txtpuntajesabio
                int puntaje = Integer.parseInt(txtPuntaje.getText().toString().replaceAll("[^0-9]", ""));

                // Obtener el nombre desde el TextView txtnombre
                String nombre = ((TextView) findViewById(R.id.txtnombre)).getText().toString();
                String descripcion = txtOrden.getText().toString();
                int puntuacionmax = 1;
                int puntuacionmin = 0;
                String dificultad = "1";
                String aprendizaje = "Logico";

                Niveles niveles = new Niveles();
                niveles.setId_nivel(1);

                Recursos recursos = new Recursos();
                recursos.setId_recurso(1);

                Tipo_Aprendizaje tipo_aprendizaje = new Tipo_Aprendizaje();
                tipo_aprendizaje.setId_tipo_aprend(1);

                // Crear un objeto Actividad con la respuesta, el nombre de la imagen y otros datos
                Actividad actividad = new Actividad();
                actividad.setAct_respuesta(respuesta);
                actividad.setAct_puntaje_alcanzado(puntaje); // Usar el puntaje actualizado
                actividad.setAct_puntaje_max(puntuacionmax);
                actividad.setAct_puntaje_min(puntuacionmin);
                actividad.setAct_dificultad(dificultad);
                actividad.setAct_nombre(nombre);
                actividad.setAct_aprendizaje(aprendizaje);
                actividad.setAct_descripcion(descripcion);
                actividad.setRecursos(recursos);
                actividad.setNiveles(niveles);
                actividad.setTipo_aprendizaje(tipo_aprendizaje);

                // Enviar la actividad a la API utilizando el método crearActividad de ApiTipoAprendizaje
                ApiActividades.crearActividad(ActividadDosPalabras.this, actividad, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(ActividadDosPalabras.this, "Actividad enviada a la API correctamente", Toast.LENGTH_SHORT).show();
                        // Pasar a la actividad de Retroalimentacion
                        Intent intent = new Intent(ActividadDosPalabras.this, Retroalimentacion.class);
                        intent.putExtra("respuestaCorrecta", respuesta.equals(nombreImagen));
                        intent.putExtra("puntaje", score);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActividadDosPalabras.this, "Error al enviar la actividad a la API: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private String obtenerRespuestaSeleccionada() {
        //Consiguiendo respuestas xD
        RadioButton radioButton1 = findViewById(R.id.radioButton7);
        RadioButton radioButton2 = findViewById(R.id.radioButton5);
        RadioButton radioButton3 = findViewById(R.id.radioButton3);
        RadioButton radioButton4 = findViewById(R.id.radioButton6);

        if (radioButton1.isChecked()) {
            return radioButton1.getText().toString();
        } else if (radioButton2.isChecked()) {
            return radioButton2.getText().toString();
        } else if (radioButton3.isChecked()) {
            return radioButton3.getText().toString();
        } else if (radioButton4.isChecked()) {
            return radioButton4.getText().toString();
        } else {
            //por si esta pendejo y no selecciono
            return "";
        }
    }
}