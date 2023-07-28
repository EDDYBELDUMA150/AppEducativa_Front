package com.example.appeducativa.actividadesRaul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

import java.util.Random;

public class ActividadUno extends AppCompatActivity {

    private ImageView imageView;
    private int[] images = {R.drawable.bici, R.drawable.carro, R.drawable.monorail, R.drawable.tren};
    private String[] nombresImagenes = {"bici", "carro", "monorail", "tren"};
    private int actv =1;
    private int currentIndex = 0;
    private ProgressBar progressBar;
    private EditText editText;
    private int score = 0;
    private TextView txtPuntaje;
    private TextView txtPalabraOculta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividaduno);

        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.pgractuno);
        editText = findViewById(R.id.edittextrespuesta);
        txtPuntaje = findViewById(R.id.txtpuntaje);
        txtPalabraOculta = findViewById(R.id.txtPalabraOculta);

        updateProgressBar(score);

        // Obtener el índice seleccionado de forma aleatoria
        currentIndex = getRandomImageIndex();

        // Verificar si se proporcionó un índice válido
        if (currentIndex != -1 && currentIndex < images.length) {
            int imageRes = images[currentIndex];
            imageView.setImageResource(imageRes);
        } else {
            // Si no se pudo obtener una imagen válida, finalizar la actividad actual
            finish();
            return;
        }

        // Mostrar la primera imagen y el nombre a describir
        siguienteImagen();

        // Mostrar la primera palabra oculta al inicio
        String nombreImagenActual = nombresImagenes[currentIndex];
        String palabraOculta = obtenerPalabraOculta(nombreImagenActual);
        txtPalabraOculta.setText(palabraOculta);
    }

    private void siguienteImagen() {
        // Mostrar la imagen actual
        int imageRes = images[currentIndex];
        imageView.setImageResource(imageRes);

        // Obtener el nombre de la imagen a describir
        String nombreImagenActual = nombresImagenes[currentIndex];

        // Actualizar el TextView para mostrar la palabra oculta con la primera y última letra
        String palabraOculta = obtenerPalabraOculta(nombreImagenActual);
        txtPalabraOculta.setText(palabraOculta);

        // Mostrar un Toast con el nombre de la siguiente imagen a describir
        Toast.makeText(ActividadUno.this, "Describe la imagen de un/a " + nombreImagenActual, Toast.LENGTH_SHORT).show();
    }

    public void onBtnEnviarRespuestaClick(View view) {
        // Obtener la respuesta del usuario desde el EditText
        String respuestaUsuario = editText.getText().toString().trim();

        // Obtener el nombre de la imagen actual para comparar con la respuesta del usuario
        String nombreImagenActual = nombresImagenes[currentIndex];

        // Comparar la respuesta del usuario con el nombre de la imagen actual (ignorar mayúsculas/minúsculas)
        if (respuestaUsuario.equalsIgnoreCase(nombreImagenActual)) {
            // Respuesta correcta, incrementar el puntaje
            score++;
        } else {
            // Respuesta incorrecta, puedes hacer alguna acción o penalizar el puntaje si es necesario
            // Por ejemplo, podrías restar puntos o mantener el puntaje sin cambios.
            // Aquí, no se realizará ninguna acción especial para respuestas incorrectas.
        }

        // Actualizar la vista del puntaje
        txtPuntaje.setText("Puntaje: " + score);

        // Resto del código existente...

        // Incrementar el índice para mostrar la siguiente imagen
        currentIndex++;
        if (currentIndex < images.length) {
            // Mostrar la siguiente imagen
            siguienteImagen();
        } else {
            // Pasar a la actividad de Retroalimentacion
            enviarPuntajeFinalALaAPI(score);
        }
    }

    public void updateProgressBar(int progress) {
        progressBar.setProgress(progress);
    }

    private int getRandomImageIndex() {
        Random random = new Random();
        return random.nextInt(images.length);
    }

    private void enviarPuntajeFinalALaAPI(int puntajeFinal) {
        Niveles niveles = new Niveles();
        niveles.setId_nivel(2);

        Recursos recursos = new Recursos();
        recursos.setId_recurso(1);

        Tipo_Aprendizaje tipo_aprendizaje = new Tipo_Aprendizaje();
        tipo_aprendizaje.setId_tipo_aprend(3);

        String nombre = ((TextView) findViewById(R.id.txtnombre)).getText().toString();
        TextView txtOrdentres = findViewById(R.id.txtorden);
        String descripcion = txtOrdentres.getText().toString();
        int puntuacionmax = 4;
        int puntuacionmin = 0;
        String dificultad = "Medio";
        String aprendizaje = "Aprendizaje Lógico";
        // Creamos una actividad con el puntaje final y lo enviamos a la API
        Actividad actividad = new Actividad();
        actividad.setAct_respuesta("Puntaje final del jugador: " + puntajeFinal);
        actividad.setAct_puntaje_alcanzado(puntajeFinal); // Usar el puntaje actualizado
        actividad.setAct_puntaje_max(puntuacionmax);
        actividad.setAct_puntaje_min(puntuacionmin);
        actividad.setAct_dificultad(dificultad);
        actividad.setAct_nombre(nombre);
        actividad.setAct_aprendizaje(aprendizaje);
        actividad.setAct_descripcion(descripcion);
        // Creamos una actividad con la respuesta que incluye el puntaje obtenido
        actividad.setAct_respuesta("Puntaje final del jugador actividaduno: " + puntajeFinal); // Concatenar el puntaje
        actividad.setTipo_aprendizaje(tipo_aprendizaje);
        actividad.setNiveles(niveles);
        actividad.setRecursos(recursos);

        ApiActividades.crearActividad(ActividadUno.this, actividad, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Aquí puedes manejar la respuesta de éxito si lo deseas
                Toast.makeText(ActividadUno.this, "Puntaje final enviado a la API correctamente", Toast.LENGTH_SHORT).show();

                // Una vez que se ha enviado el puntaje final, pasar a la actividad Retroalimentacion
                Intent intent = new Intent(ActividadUno.this, ActividadDos.class);
                //intent.putExtra("puntaje", score);
                //intent.putExtra("respuestaCorrecta", true); // Siempre indicar que la respuesta es correcta
                startActivity(intent);

                // Finalizar la actividad actual para evitar que el usuario regrese a esta actividad desde Retroalimentacion
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Aquí puedes manejar el error si lo deseas
                Toast.makeText(ActividadUno.this, "Error al enviar el puntaje final a la API: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void enviarActividadALaAPI(String nombreImagen, String respuestaUsuario) {
        Actividad actividad = new Actividad();
        actividad.setAct_nombre(nombreImagen);
        actividad.setAct_respuesta(respuestaUsuario);

        ApiActividades.crearActividad(ActividadUno.this, actividad, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Aquí puedes manejar la respuesta de éxito si lo deseas
                Toast.makeText(ActividadUno.this, "Actividad enviada a la API correctamente", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Aquí puedes manejar el error si lo deseas
                Toast.makeText(ActividadUno.this, "Error al enviar la actividad a la API: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String obtenerPalabraOculta(String nombreImagenActual) {
        if (nombreImagenActual.length() < 3) {
            // Si el nombre de la imagen tiene menos de 3 caracteres, mostrarlo tal cual
            return nombreImagenActual;
        } else {
            // Construir la palabra oculta mostrando solo la primera y última letra
            StringBuilder palabraOcultaBuilder = new StringBuilder();
            palabraOcultaBuilder.append(nombreImagenActual.charAt(0)); // Mostrar la primera letra

            for (int i = 1; i < nombreImagenActual.length() - 1; i++) {
                palabraOcultaBuilder.append("_"); // Ocultar letras intermedias con guiones bajos
            }

            palabraOcultaBuilder.append(nombreImagenActual.charAt(nombreImagenActual.length() - 1)); // Mostrar la última letra
            return palabraOcultaBuilder.toString();
        }
    }
}