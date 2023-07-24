package com.example.appeducativa.actividadesSalvaLili;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.appeducativa.R;
import com.example.appeducativa.clases.Actividad;
import com.example.appeducativa.modeloapi.ApiActividades;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class ActividadMemoria extends AppCompatActivity {
    ImageButton  imb01, imb02, imb03, imb04, imb05, imb06, imb07, imb08, imb09, imb10, imb11, imb12, imb13, imb14, imb15,imb16;
    ImageButton[] tablero = new ImageButton[16];
    Button botonReiniciar, botonSalir;
    TextView textoPuntuacion;
    int puntuacion = 10;
    int aciertos;
    //ALMACENAR LAS IMAGENES
    int[] imagenes;
    int fondo;
    //ARRAYS PARA ALMACENAR
    ArrayList<Integer> arrayDesordenado;
    ImageButton primero;
    int numeroPrimero, numeroSegundo;
    boolean bloqueo = false;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria);
        init();
    }

    private void cargarTablero(){
        imb01 = findViewById(R.id.boton01);
        imb02 = findViewById(R.id.boton02);
        imb03 = findViewById(R.id.boton03);
        imb04 = findViewById(R.id.boton04);
        imb05 = findViewById(R.id.boton05);
        imb06 = findViewById(R.id.boton06);
        imb07 = findViewById(R.id.boton07);
        imb08 = findViewById(R.id.boton08);
        imb09 = findViewById(R.id.boton09);
        imb10 = findViewById(R.id.boton10);
        imb11 = findViewById(R.id.boton11);
        imb12 = findViewById(R.id.boton12);
        imb13 = findViewById(R.id.boton13);
        imb14 = findViewById(R.id.boton14);
        imb15 = findViewById(R.id.boton15);
        imb16 = findViewById(R.id.boton16);

        tablero[0] = imb01;
        tablero[1] = imb02;
        tablero[2] = imb03;
        tablero[3] = imb04;
        tablero[4] = imb05;
        tablero[5] = imb06;
        tablero[6] = imb07;
        tablero[7] = imb08;
        tablero[8] = imb09;
        tablero[9] = imb10;
        tablero[10] = imb11;
        tablero[11] = imb12;
        tablero[12] = imb13;
        tablero[13] = imb14;
        tablero[14] = imb15;
        tablero[15] = imb16;
    }
    private void cargarBotones(){
        botonReiniciar = findViewById(R.id.botonreiniciar);
        botonSalir = findViewById(R.id.botonsalir);
        botonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });

        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void cargarTexto(){
        textoPuntuacion = findViewById(R.id.puntaje);
        puntuacion = 0;
        aciertos = 0;
        textoPuntuacion.setText("Puntuacion: " + puntuacion);
    }

    private void cargarImagenes(){
        imagenes = new int[]{
                R.drawable.fondoj1,
                R.drawable.fondoj2,
                R.drawable.fondoj3,
                R.drawable.fondoj4,
                R.drawable.fondoj5,
                R.drawable.fondoj6,
                R.drawable.fondo7,
                R.drawable.fondo8
        };
        fondo = R.drawable.img;
    }

    private ArrayList<Integer> barajar(int longitud){
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i=0; i<longitud*2; i++){
            result.add(i % longitud);
        }
        Collections.shuffle(result);
        // System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    private void comprobar(int i, final ImageButton imgb){
        if(primero == null){
            primero = imgb;
            primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
            primero.setImageResource(imagenes[arrayDesordenado.get(i)]);
            primero.setEnabled(false);
            numeroPrimero = arrayDesordenado.get(i);
        } else {
            bloqueo = true;
            imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgb.setImageResource(imagenes[arrayDesordenado.get(i)]);
            imgb.setEnabled(false);
            numeroSegundo = arrayDesordenado.get(i);
            if(numeroPrimero == numeroSegundo){
                primero = null;
                bloqueo = false;
                aciertos++;
                puntuacion++;
                textoPuntuacion.setText("Puntuación: " + puntuacion);
                if(aciertos == imagenes.length){
                    Toast toast = Toast.makeText(getApplicationContext(), "Has ganado!!", Toast.LENGTH_LONG);
                    toast.show();
                    guardarActividadEnAPI();
                }
            } else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        primero.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        primero.setImageResource(fondo);
                        primero.setEnabled(true);
                        imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imgb.setImageResource(fondo);
                        imgb.setEnabled(true);
                        bloqueo = false;
                        primero = null;
                        if (puntuacion >= 10){
                            puntuacion--;
                        }

                        textoPuntuacion.setText("Puntuación: " + puntuacion);
                    }
                }, 1000);
            }
        }
    }

    private void init(){
        cargarTablero();
        cargarBotones();
        cargarTexto();
        cargarImagenes();
        arrayDesordenado = barajar(imagenes.length);
        for(int i=0; i<tablero.length; i++){
            tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            tablero[i].setImageResource(imagenes[arrayDesordenado.get(i)]);
            //tablero[i].setImageResource(fondo);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<tablero.length; i++){
                    tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
                    //tablero[i].setImageResource(imagenes[arrayDesordenado.get(i)]);
                    tablero[i].setImageResource(fondo);
                }
            }
        }, 500);
        for(int i=0; i<tablero.length; i++) {
            final int j = i;
            tablero[i].setEnabled(true);
            tablero[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!bloqueo)
                        comprobar(j, tablero[j]);
                }
            });
        }

    }

    private void guardarActividadEnAPI() {
        String nombreActividad = "Juego de Memorias"; // Nombre de la actividad
        int puntajeAlcanzado = puntuacion; // Puntaje obtenido por el usuario
        int pint_Max= 10;
        int pint_Min= 2;


        Actividad actividad = new Actividad();
        actividad.setAct_nombre(nombreActividad);
        actividad.setAct_puntaje_alcanzado(puntajeAlcanzado);
        actividad.setAct_puntaje_max(pint_Max);
        actividad.setAct_puntaje_min(pint_Min);

        ApiActividades.crearActividad(this, actividad,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Aquí puedes manejar la respuesta obtenida desde el servidor (opcional)
                        Toast.makeText(ActividadMemoria.this, "La actividad se guardó correctamente.", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error en caso de que ocurra
                        String mensajeError;

                        if (error instanceof TimeoutError) {
                            mensajeError = "Error: Tiempo de espera agotado. Por favor, verifica tu conexión a Internet.";
                        } else if (error instanceof NoConnectionError) {
                            mensajeError = "Error: No se pudo establecer una conexión con el servidor. Por favor, verifica tu conexión a Internet.";
                        } else if (error instanceof AuthFailureError) {
                            mensajeError = "Error: Falló la autenticación de la solicitud.";
                        } else if (error instanceof NetworkError) {
                            mensajeError = "Error: Problema de red. Por favor, verifica tu conexión a Internet.";
                        } else if (error instanceof ServerError) {
                            mensajeError = "Error: Problema en el servidor. Por favor, intenta nuevamente más tarde.";
                        } else if (error instanceof ParseError) {
                            mensajeError = "Error: Ocurrió un error al analizar la respuesta del servidor.";
                        } else {
                            mensajeError = "Error: Ocurrió un error en la solicitud.";
                        }

                        // Mostrar el mensaje de error en un Toast
                        Toast.makeText(ActividadMemoria.this, mensajeError, Toast.LENGTH_LONG).show();
                    }
         });
    }

}