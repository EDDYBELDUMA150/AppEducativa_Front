package com.example.appeducativa.actividadesSalvaLili;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
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
import com.example.appeducativa.actividadesRaul.ActividadUno;
import com.example.appeducativa.clases.Actividad;
import com.example.appeducativa.clases.Jugador;
import com.example.appeducativa.clases.Niveles;
import com.example.appeducativa.clases.Progreso;
import com.example.appeducativa.clases.Progreso_Aprendizaje;
import com.example.appeducativa.clases.Recursos;
import com.example.appeducativa.clases.Resultados;
import com.example.appeducativa.clases.Tipo_Aprendizaje;
import com.example.appeducativa.clases.Usuario;
import com.example.appeducativa.modeloapi.ApiActividades;
import com.example.appeducativa.modeloapi.ApiJugador;
import com.example.appeducativa.modeloapi.ApiJugadores;
import com.example.appeducativa.modeloapi.ApiProgreso;
import com.example.appeducativa.modeloapi.ApiProgresoAprendizaje;
import com.example.appeducativa.modeloapi.ApiResultados;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class ActividadMemoria extends AppCompatActivity {
    ImageButton imb01, imb02, imb03, imb04, imb05, imb06, imb07, imb08, imb09, imb10, imb11, imb12, imb13, imb14, imb15, imb16;
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
    private int selectedImageIndex = -1;
    private int[] images = {R.drawable.bici, R.drawable.carro, R.drawable.monorail, R.drawable.tren};
    int id_usu;

    String nombreActividad = "Juego de Memorias"; // Nombre de la actividad

    private ApiActividades apiActividad;
    private ApiResultados apiResult;
    private ApiProgresoAprendizaje apiProgresoAprendizaje;
    private ApiProgreso apiProgreso;

    Actividad actividad = new Actividad();
    Resultados resultados = new Resultados();
    Progreso_Aprendizaje progreso_aprendizaje = new Progreso_Aprendizaje();
    Progreso progreso = new Progreso();
    Jugador jugador = new Jugador();
    Tipo_Aprendizaje tipo_aprendizaje = new Tipo_Aprendizaje();
    Usuario userid = new Usuario();

    // Configurar la visibilidad del botón a invisible

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoria);

        id_usu = getIntent().getIntExtra("player", 0);

        init();
    }

    private void cargarTablero() {
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

    private void cargarBotones() {
        botonReiniciar = findViewById(R.id.botonreiniciar);
        botonSalir = findViewById(R.id.botonsalir);
        botonReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // init();
                guardarActividadEnAPI();
                getIdMaxAct();
                getIdMaxResult();
                getIdMaxProa();
                getIdMaxProgre();
            }
        });

        botonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void cargarTexto() {
        textoPuntuacion = findViewById(R.id.puntaje);
        puntuacion = 0;
        aciertos = 0;
        textoPuntuacion.setText("Puntuacion: " + puntuacion);
    }

    private void cargarImagenes() {
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

    private ArrayList<Integer> barajar(int longitud) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < longitud * 2; i++) {
            result.add(i % longitud);
        }
        Collections.shuffle(result);
        // System.out.println(Arrays.toString(result.toArray()));
        return result;
    }

    private void comprobar(int i, final ImageButton imgb) {
        if (primero == null) {
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
            if (numeroPrimero == numeroSegundo) {
                primero = null;
                bloqueo = false;
                aciertos++;
                puntuacion++;
                textoPuntuacion.setText("Puntuación: " + puntuacion);
                if (aciertos == imagenes.length) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Has ganado!!", Toast.LENGTH_LONG);
                    toast.show();
                    guardarActividadEnAPI();
                    getIdMaxAct();
                    getIdMaxResult();
                    getIdMaxProa();
                    getIdMaxProgre();
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
                        if (puntuacion >= 10) {
                            puntuacion--;
                        }

                        textoPuntuacion.setText("Puntuación: " + puntuacion);
                    }
                }, 1000);
            }
        }
    }

    private void init() {
        cargarTablero();
        cargarBotones();
        cargarTexto();
        cargarImagenes();
        arrayDesordenado = barajar(imagenes.length);
        for (int i = 0; i < tablero.length; i++) {
            tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            tablero[i].setImageResource(imagenes[arrayDesordenado.get(i)]);
            //tablero[i].setImageResource(fondo);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < tablero.length; i++) {
                    tablero[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
                    //tablero[i].setImageResource(imagenes[arrayDesordenado.get(i)]);
                    tablero[i].setImageResource(fondo);
                }
            }
        }, 500);
        for (int i = 0; i < tablero.length; i++) {
            final int j = i;
            tablero[i].setEnabled(true);
            tablero[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!bloqueo)
                        comprobar(j, tablero[j]);
                }
            });
        }

    }

    private void guardarActividadEnAPI() {
        int puntajeAlcanzado = puntuacion; // Puntaje obtenido por el usuario
        int pint_Max = 10;
        int pint_Min = 2;

        Niveles niveles = new Niveles();
        niveles.setId_nivel(1);

        Recursos recursos = new Recursos();
        recursos.setId_recurso(1);

        tipo_aprendizaje.setId_tipo_aprend(1);

        actividad.setAct_nombre(nombreActividad);
        actividad.setAct_puntaje_alcanzado(puntajeAlcanzado);
        actividad.setAct_puntaje_max(pint_Max);
        actividad.setAct_puntaje_min(pint_Min);
        actividad.setRecursos(recursos);
        actividad.setNiveles(niveles);
        actividad.setTipo_aprendizaje(tipo_aprendizaje);

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


    private int getRandomImageIndex() {
        Random random = new Random();
        return random.nextInt(images.length);
    }

    public void guardarApiResultado() {

        resultados.setRe_puntaje(puntuacion);
        resultados.setActividad(actividad);
        ApiResultados.crearResultado(this, resultados, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mostrarMensaje("Resultado tambien agregado");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mostrarMensaje("Error al agregar el resultado");
            }
        });
    }

    public void getIdMaxAct() {
        apiActividad = new ApiActividades();

        LiveData<Integer> idActividadLiveData = apiActividad.getIdActividadLiveData(this);

        idActividadLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer idActividad) {
                if (idActividad != null) {
                    // Aquí puedes usar el ID más alto en tu lógica
                    actividad.setId_activ(idActividad);
                    System.out.println("GUARDODOO :: "+ actividad.getId_activ());
                    guardarApiResultado();
                } else {
                    // Manejar el caso de error
                    mostrarMensaje("Error al obtener el ID más alto desde la respuesta.");
                }
            }
        });
    }

    public void getIdMaxResult() {
        apiResult = new ApiResultados();

        LiveData<Integer> idLiveData = apiResult.getIdResultadoLiveData(this);

        idLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer idResult) {
                if (idResult != null) {
                    // Aquí puedes usar el ID más alto en tu lógica
                    resultados.setId_resultado(idResult);

                    guardarApiProgApr();
                } else {
                    // Manejar el caso de error
                    mostrarMensaje("Error al obtener el ID más alto desde la respuesta.");
                }
            }
        });
    }

    public void getIdMaxProa() {
        apiProgresoAprendizaje = new ApiProgresoAprendizaje();

        LiveData<Integer> idLiveData = apiProgresoAprendizaje.getIdProgApLiveData(this);

        idLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer idResult) {
                if (idResult != null) {
                    // Aquí puedes usar el ID más alto en tu lógica
                    progreso_aprendizaje.setId_prog_aprend(idResult);

                    guardarApiProg();
                } else {
                    // Manejar el caso de error
                    mostrarMensaje("Error al obtener el ID más alto desde la respuesta.");
                }
            }
        });
    }

    public void getIdMaxProgre() {
        apiProgreso = new ApiProgreso();

        LiveData<Integer> idLiveData = apiProgreso.getIdProgLiveData(this);

        idLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer idResult) {
                if (idResult != null) {
                    // Aquí puedes usar el ID más alto en tu lógica
                    progreso.setId_progress(idResult);

                    guardarJugador();
                } else {
                    // Manejar el caso de error
                    mostrarMensaje("Error al obtener el ID más alto desde la respuesta.");
                }
            }
        });
    }

    public void guardarJugador() {
        System.out.println("ID recibooo activ    >> "+actividad.getId_activ());


        userid.setId_usuario(id_usu);

        jugador.setUsuarios(userid);
        jugador.setActividad(actividad);
        jugador.setProgreso(progreso);
        jugador.setNombre("Eddy150");

        System.out.println("ID DE ACTIVIDAD :: "+ jugador.getActividad().getId_activ());
        System.out.println("ID PROGRESO:::" + jugador.getProgreso().getId_progress());
        ApiJugador.crearJugador(this, jugador, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Jugador tambien agregado");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mostrarMensaje("Error al Jugador el resultado");
            }
        });
    }

    public void guardarApiProgApr() {
        System.out.println("ccccccccc:::   "+resultados.getId_resultado());
        progreso_aprendizaje.setId_resultado(resultados);
        System.out.println("ESTE EL ES EL ID DE RESUL   "+ progreso_aprendizaje.getId_resultado().getId_resultado());
        progreso_aprendizaje.setId_tipo_apren(tipo_aprendizaje);
        progreso_aprendizaje.setProgApren_nombre(nombreActividad);
        progreso_aprendizaje.setProgApren_puntaje(puntuacion);
        ApiProgresoAprendizaje.crearProgresoApren(this, progreso_aprendizaje, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("ProgApren tambien agregado");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mostrarMensaje("Error al ProgApren el resultado");
            }
        });
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    public void guardarApiProg() {

        progreso.setProgreso_aprendizaje(progreso_aprendizaje);
        progreso.setProg_nivel(1);
        progreso.setProg_puntaje_total(puntuacion);
        progreso.setProg_fecha_init(getCurrentDate());
        ApiProgreso.crearProgreso(this, progreso, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Progreso tambien agregado");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mostrarMensaje("Error al Progreso el resultado");
            }
        });
    }

    private String getCurrentDate() {
        // Obtener la fecha actual como un objeto Date
        Date date = new Date();

        // Definir el formato deseado para la fecha (yyyy-MM-dd)
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // Formatear la fecha como una cadena en el formato especificado
        String formattedDate = dateFormat.format(date);

        return formattedDate;
    }
}