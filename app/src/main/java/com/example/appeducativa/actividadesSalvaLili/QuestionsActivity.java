package com.example.appeducativa.actividadesSalvaLili;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.example.appeducativa.clases.Niveles;
import com.example.appeducativa.clases.Recursos;
import com.example.appeducativa.clases.Tipo_Aprendizaje;
import com.example.appeducativa.modeloapi.ApiActividades;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {

    private TextView question,noIndicator;
    private FloatingActionButton bookmarkbtn;
    private LinearLayout optionsContainer;
    private Button shareBtn, nextBtn;
    private int count = 0;
    private List<QuestionModel> list;
    private List<String> userAnswers = new ArrayList<>();

    private int position=0;
    private int score=0;
    private String dialogText;

    int selectedSet=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        question = findViewById(R.id.questions);
        noIndicator = findViewById(R.id.no_indicator);
        bookmarkbtn = findViewById(R.id.btnOpenDialog);
        optionsContainer = findViewById(R.id.options_container);
        shareBtn = findViewById(R.id.share_btn);
        nextBtn = findViewById(R.id.next_btn);


        selectedSet = getIntent().getIntExtra("selected_set", 1); // El valor por defecto es 1

        // Cargar la lista de preguntas correspondiente según el conjunto seleccionado
        if (selectedSet == 1) {
            list = new ArrayList<>();
            // preguntas para el conjunto 1
            list.add(new QuestionModel("question 1: ¿Qué desayuna Alejandro cada mañana?", "Tostadas con mantequilla y mermelada","Cereales con leche","Una fruta y una galleta", "cafe con pan","Tostadas con mantequilla y mermelada"));
            list.add(new QuestionModel("question 2: ¿Qué medio de transporte utiliza?", "La bicicleta","El coche","El autobús", "El tren","El coche"));
            list.add(new QuestionModel("question 3: ¿Quién acompaña a Alejandro al colegio?", "Su madre","Su hermano","Su Padre", "Su Abuela","Su Padre"));
            list.add(new QuestionModel("question 4: ¿Qué le recuerda su madre antes de irse?", "Dar de comer al perro","Lavarse los dientes","Las cosas que debe llevar en su mochila", "Dar de comer al Gato","Las cosas que debe llevar en su mochila"));
            list.add(new QuestionModel("question 5: ¿Qué es la puntualidad según los padres de Alejandro?", "Uno de los mayores signos de educación","Algo innecesario","Nada referente a la educacion", "sin moral","Uno de los mayores signos de educación"));

            dialogText = "Cada mañana, Alejandro se levanta para ir al colegio. Desayuna tostadas con mantequilla y mermelada y un gran vaso de leche con cacao calentito. Su madre le recuerda las cosas que debe llevar en su mochila y su padre le acerca al colegio en coche para que no se le haga tarde y llegue siempre puntual, aunque los días de lluvia esto se hace muy difícil por la cantidad de coches que se agolpan en las calles de su ciudad. Y es que la puntualidad, según los padres de Alejandro, es uno de los mayores signos de educación que hay.";

            // Preguntas conjunto 2
        } else if (selectedSet == 2) {
            list = new ArrayList<>();
            // Agregar preguntas para el conjunto 2
            list.add(new QuestionModel("question 1: ¿Cuáles son los ingredientes que añaden a las hamburguesas?", "Jarabe de estrellas y polvo de mostaza marciano", "Ketchup lunar y mayonesa de venus", "Empanadas y Tortillas", "No le añade ningún ingrediente", "Jarabe de estrellas y polvo de mostaza marciano"));
            list.add(new QuestionModel("question 2: ¿Qué idioma son capaces de hablar los marcianos?", "El nuestro (Español)", "Klingon", "Marteriano", "Mandarin", "El nuestro (Español)"));
            list.add(new QuestionModel("question 3: ¿Cuántas hamburguesas son capaces de comerse en un día?", "1", "1000", "100", "500", "100"));
            list.add(new QuestionModel("question 4: ¿De qué comida son fans?", "Pizzas", "Hamburguesas", "Perritos calientes", "Mandarina", "Hamburguesas"));
            list.add(new QuestionModel("question 5: ¿Cómo son los ritmos musicales que bailan estos marcianos?", "Reguetton", "Interestelares", "Salseros", "Interestelares", "Interestelares"));

            dialogText = "Si alguna vez os encontráis con un marciano tened mucho cuidado. Dicen que son capaces de hablar nuestro idioma a la perfección y de mezclarse entre los humanos sin que nadie se percate de su presencia. Son fans de las hamburguesas, las cuales aderezan con jarabe de estrellas y polvo de mostaza totalmente marciano, y son capaces de comerse cien en un día sin que se note en sus cuerpos el más mínimo kilo de más. Pero sobre todo, de lo que son capaces, es de hacer que los niños y niñas del mundo disfruten de lo lindo al son de ritmos musicales interestelares. ¡Son muy divertidos!";

        }else if (selectedSet == 3) {
            list = new ArrayList<>();
            // Agregar preguntas para el conjunto 2
            list.add(new QuestionModel("question 1: ¿De qué trataba la charla que dieron en el colegio de Carla?", "De educación vial", "De cómo comportarse en clase", "De matemáticas", "Ninguna de las opciones", "De educación vial"));
            list.add(new QuestionModel("question 2: ¿Quién o quienes la dieron?", "Solo su maestra", "Solo un policía", "Su maestra y un policía", "El Ejercito", "Su maestra y un policía"));
            list.add(new QuestionModel("question 3: La historia sucede en el colegio de...", "Carla", "Luis", "Pedro", "Maria", "Carla"));
            list.add(new QuestionModel("question 4: Carla ha aprendido que jamás debe cruzar la calle, pero ¿en qué situación?", "Cuando el semáforo se encuentre de color rojo", "Cuando el semáforo se encuentre de color verde", "Cuando el semáforo se encuentre de color ámbar", "Cuando el semáforo se encuentre de color violeta", "Cuando el semáforo se encuentre de color rojo"));
            list.add(new QuestionModel("question 5: ¿Quiénes deben cumplir las normas de tráfico?", "Nadie, no es necesario", "Los conductores y los peatones", "Solo los conductores", "Solo los peatones", "Los conductores y los peatones"));

            dialogText = "El otro día, en el colegio de Carla, les dieron una charla sobre educación vial. Su maestra y un policía les mostraron la cantidad de peligros que existen en la carretera y que se dan cuando hacemos algo tan sencillo como cruzar la calle. Ahora Carla sabe que existen muchas normas de circulación que debemos tener presentes y cumplir como conductores y como peatones. Y sobre todo, ha aprendido que jamás debe cruzar la calle cuando el semáforo se encuentre de color rojo. Es muy importante que también, y hasta que sea más mayor, cruce de la mano de sus padres o profesores para una mayor seguridad.";

        } else {
            Toast.makeText(QuestionsActivity.this, "Error no se encuntra ninguna lista", Toast.LENGTH_SHORT).show();
        }


        list = list;

        for (int i = 0;i < 4; i++){
            optionsContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer((Button)v);
                }
            });
        }

        bookmarkbtn = findViewById(R.id.btnOpenDialog);
        bookmarkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear el diálogo emergente
                AlertDialog.Builder builder = new AlertDialog.Builder(QuestionsActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.lectura, null);
                builder.setView(dialogView);

                // Configurar el botón "Aceptar" (opcional)
                builder.setPositiveButton("Aceptar", null);

                // Obtener el textViewDialogContent y establecer el texto del diálogo
                TextView textViewDialogContent = dialogView.findViewById(R.id.textViewDialogContent);
                textViewDialogContent.setText(dialogText);

                // Mostrar el diálogo emergente
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        noIndicator.setText(position+1+"/"+list.size());



        playAnim(question,0,list.get(position).getQuestion());
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextBtn.setEnabled(true);
                nextBtn.setAlpha(0.7f);
                enableOption(true);
                position++;
                if(position == list.size()){
                    // Enviar la actividad al API
                    guardarActividadEnAPI();

                    Intent scoreIntent = new Intent(QuestionsActivity.this,ScoreActivities.class);
                    scoreIntent.putExtra("score",score);
                    scoreIntent.putExtra("total",list.size());
                    startActivity(scoreIntent);
                    finish();
                    return;
                }
                count=0;
                playAnim(question,0,list.get(position).getQuestion());
            }
        });
    }

    private void playAnim(View view, int value, String data){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animator) {
                        if(value == 0 && count < 4){
                            String option="";
                            if(count == 0){
                                option = list.get(position).getOptionA();
                            } else if (count==1) {
                                option = list.get(position).getOptionB();
                            } else if (count==2) {
                                option = list.get(position).getOptionC();
                            } else if (count==3) {
                                option = list.get(position).getOptionD();

                            }
                            playAnim(optionsContainer.getChildAt(count),0, option);
                            count++;
                        }
                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animator) {

                        if(value == 0){
                            try {
                                ((TextView)view).setText(data);
                                noIndicator.setText(position+1+"/"+list.size());
                            }catch (ClassCastException ex){
                                ((Button)view).setText(data);
                            }
                            view.setTag(data);
                            playAnim(view,1,data);

                        }
                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animator) {

                    }
                });
    }

    private void checkAnswer(Button selectOption){
        enableOption(false);
        nextBtn.setEnabled(true);
        nextBtn.setAlpha(1);
        if(selectOption.getText().toString().equals(list.get(position).getCorrectANS())){
            //correcto
            score++;
            selectOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
        }else {
            //incorrecto
            selectOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")));
            Button correctoption = (Button) optionsContainer.findViewWithTag(list.get(position).getCorrectANS());
            correctoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
        }

        userAnswers.add(selectOption.getText().toString());


    }

    private void guardarActividadEnAPI() {
        String nombreActividad = "Lectura 1"; // Nombre de la actividad
        String descripcion = "Preguntas y respuestas";
        String difcultad = "";
        if (selectedSet == 1){
            difcultad="Fácil";
        } else if (selectedSet == 2) {
            difcultad="Medio";
        } else{
            difcultad="Difícil";
        }
        int puntajeAlcanzado = score; // Puntaje obtenido por el usuario
        int pint_Max= 5;
        int pint_Min= 0;

        // Retroalimentación basada en el puntaje alcanzado
        String retroalimentacion="";
        if (puntajeAlcanzado >= 4) {
            retroalimentacion = "¡Excelente trabajo! Has obtenido un puntaje alto.";
        } else if (puntajeAlcanzado >= 3) {
            retroalimentacion = "Buen trabajo. Tu puntaje es bastante bueno.";
        } else if (puntajeAlcanzado >= 2) {
            retroalimentacion = "Puedes mejorar. Sigue practicando.";
        } else if (puntajeAlcanzado >= 1) {
            retroalimentacion = "Necesitas mejorar. Sigue intentando.";
        } else {
            retroalimentacion = "Necesitas más práctica. Sigue esforzándote.";
        }

        Niveles niveles = new Niveles();
        niveles.setId_nivel(1);

        Recursos recursos = new Recursos();
        recursos.setId_recurso(1);

        Tipo_Aprendizaje tipo_aprendizaje = new Tipo_Aprendizaje();
        tipo_aprendizaje.setId_tipo_aprend(1);

        Actividad actividad = new Actividad(nombreActividad, descripcion, difcultad, pint_Max, pint_Min, puntajeAlcanzado, "Comprensión", true, retroalimentacion, recursos, niveles, tipo_aprendizaje);

        ApiActividades.crearActividad(QuestionsActivity.this, actividad,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Aquí puedes manejar la respuesta obtenida desde el servidor (opcional)
                        Toast.makeText(QuestionsActivity.this, "La actividad se guardó correctamente.", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(QuestionsActivity.this, mensajeError, Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void crearResultado(){
        int puntajeResultado = score;

    }

    private void enableOption(boolean enabled){
        for (int i = 0;i < 4; i++){
            optionsContainer.getChildAt(i).setEnabled(enabled);
            if(enabled){
                optionsContainer.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
            }
        }
    }

}