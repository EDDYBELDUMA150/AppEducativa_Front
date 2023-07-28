package com.example.appeducativa.actividadesRaul;

import androidx.annotation.Nullable;
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
import com.example.appeducativa.clases.Actividad;
import com.example.appeducativa.clases.Niveles;
import com.example.appeducativa.clases.Recursos;
import com.example.appeducativa.clases.Tipo_Aprendizaje;
import com.example.appeducativa.modeloapi.ApiActividades;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ActividadDos extends AppCompatActivity {
    private int selectedImageIndex = -1;
    private Button[] botones;
    private Integer[] imagenes = {R.drawable.carro, R.drawable.tren, R.drawable.monorail, R.drawable.bici};
    private int[] images = {R.drawable.bici, R.drawable.carro, R.drawable.monorail, R.drawable.tren};
    private List<Integer> imagenesDisponibles;
    private Random random;
    private TextView txtpalabra;
    private String nombreImagen;
    private int puntaje = 0;
    private TextView txtPuntaje;
    private static final int REQUEST_CODE_ACTIVIDAD_ANTERIOR = 1;
    private String nombreImagenAleatoria;
    private String nombreImagenBoton;
    private static final int MAX_INTENTOS = 4;
    private int intentosRealizados = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos);

        botones = new Button[4];
        botones[0] = findViewById(R.id.btnopciona);
        botones[1] = findViewById(R.id.btnopcionb);
        botones[2] = findViewById(R.id.btnopcionc);
        botones[3] = findViewById(R.id.btnopciond);
        txtpalabra = findViewById(R.id.txtpalabra);

        imagenesDisponibles = new ArrayList<>(Arrays.asList(imagenes));
        random = new Random();
        asignarFondosAleatorios();
        txtPuntaje = findViewById(R.id.textView8);
        updatePuntaje();

        // Configurar el clic en los botones
        for (Button boton : botones) {
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Obtener el nombre de la imagen aleatoria (que se muestra en el TextView)
                    nombreImagenAleatoria = txtpalabra.getText().toString(); // Eliminar la declaración local aquí

                    // Obtener el nombre de la imagen del botón seleccionado
                    nombreImagenBoton = getResourceName((Integer) boton.getTag()); // Eliminar la declaración local aquí

                    // Obtener la respuesta del usuario (puedes obtenerla desde un EditText u otra fuente)
                    String respuestaUsuario = "Respuesta del usuario";

                    intentosRealizados++;
                    if (intentosRealizados >= MAX_INTENTOS) {
                        // Si se han realizado los 4 intentos, enviar el puntaje final a la API
                        enviarPuntajeFinalALaAPI(puntaje);
                    } else {
                        // Comparar si los nombres de las imágenes son iguales
                        if (nombreImagenAleatoria.equals(nombreImagenBoton)) {
                            // Respuesta correcta
                            Toast.makeText(ActividadDos.this, "Respuesta correcta", Toast.LENGTH_SHORT).show();
                            puntaje++; // Incrementar el puntaje en caso de respuesta correcta
                        } else {
                            // Respuesta incorrecta
                            Toast.makeText(ActividadDos.this, "Respuesta incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }

                    // Actualizar el puntaje en la interfaz de usuario
                    updatePuntaje();

                    // Generar una lista de imágenes aleatorias para cada botón
                    asignarFondosAleatorios();
                }
            });
        }
    }
    private void updatePuntaje() {
        txtPuntaje.setText(String.valueOf(puntaje));
    }

    private void asignarFondosAleatorios() {
        if (imagenesDisponibles.isEmpty()) {
            imagenesDisponibles = new ArrayList<>(Arrays.asList(imagenes));
        }

        Collections.shuffle(imagenesDisponibles, random);

        // Seleccionar una imagen aleatoria y obtener su nombre
        int imagenAleatoria = imagenesDisponibles.get(0);
        nombreImagenAleatoria = getResourceName(imagenAleatoria);
        txtpalabra.setText(nombreImagenAleatoria);

        for (int i = 0; i < botones.length; i++) {
            if (!imagenesDisponibles.isEmpty()) {
                imagenAleatoria = imagenesDisponibles.remove(0);
                botones[i].setBackgroundResource(imagenAleatoria);
                botones[i].setTag(imagenAleatoria);
            }
        }
    }

    private String getResourceName(int imageId) {
        return getResources().getResourceEntryName(imageId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ACTIVIDAD_ANTERIOR && resultCode == RESULT_OK && data != null) {
            int puntajeAnterior = data.getIntExtra("puntaje", 0);
            puntaje += puntajeAnterior;
            updatePuntaje();
        }
    }

    private void enviarPuntajeFinalALaAPI(int puntajeFinal) {

        Niveles niveles = new Niveles();
        niveles.setId_nivel(2);

        Recursos recursos = new Recursos();
        recursos.setId_recurso(1);

        Tipo_Aprendizaje tipo_aprendizaje = new Tipo_Aprendizaje();
        tipo_aprendizaje.setId_tipo_aprend(3);

        String nombre = ((TextView) findViewById(R.id.txtnombre)).getText().toString();
        TextView txtOrdendos = findViewById(R.id.txtorden);
        String descripcion = txtOrdendos.getText().toString();
        int puntuacionmax = 4;
        int puntuacionmin = 0;
        String dificultad = "Medio";
        String aprendizaje = "Aprendizaje Lógico";
        // Creamos una actividad con el puntaje final y lo enviamos a la API
        Actividad actividad = new Actividad();
        actividad.setAct_respuesta("Puntaje final del jugador: " + puntajeFinal);
        actividad.setAct_puntaje_alcanzado(puntaje); // Usar el puntaje actualizado
        actividad.setAct_puntaje_max(puntuacionmax);
        actividad.setAct_puntaje_min(puntuacionmin);
        actividad.setAct_dificultad(dificultad);
        actividad.setAct_nombre(nombre);
        actividad.setAct_aprendizaje(aprendizaje);
        actividad.setAct_descripcion(descripcion);
        actividad.setTipo_aprendizaje(tipo_aprendizaje);
        actividad.setRecursos(recursos);
        actividad.setNiveles(niveles);

        ApiActividades.crearActividad(ActividadDos.this, actividad, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Aquí puedes manejar la respuesta de éxito si lo deseas
                Toast.makeText(ActividadDos.this, "Puntaje final enviado a la API correctamente", Toast.LENGTH_SHORT).show();

                // Una vez que se ha enviado el puntaje final, inicia la actividad Retroalimentacion
                iniciarActividadRetroalimentacion();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Aquí puedes manejar el error si lo deseas
                Toast.makeText(ActividadDos.this, "Error al enviar el puntaje final a la API: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void iniciarActividadRetroalimentacion() {
        // Iniciar la actividad Retroalimentacion y mostrar el mensaje "Correcto"
        Intent intent = new Intent(ActividadDos.this, ActividadDosPalabras.class);
        /*intent.putExtra("respuestaCorrecta", true); // Indicar que la respuesta es correcta
        intent.putExtra("puntaje", puntaje); // Pasar el puntaje a la actividad Retroalimentacion*/
        selectedImageIndex = getRandomImageIndex();
        intent.putExtra("selectedImageIndex", selectedImageIndex);
        startActivity(intent);
        finish(); // Finalizar la actividad actual para que no se pueda volver atrás
    }

    private int getRandomImageIndex() {
        Random random = new Random();
        return random.nextInt(images.length);
    }
}