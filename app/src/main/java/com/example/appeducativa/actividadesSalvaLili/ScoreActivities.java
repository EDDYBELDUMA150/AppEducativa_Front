package com.example.appeducativa.actividadesSalvaLili;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.appeducativa.R;
import com.example.appeducativa.controlador.InicioApp;
import com.example.appeducativa.controlador.SelectActivity;

import java.util.ArrayList;

public class ScoreActivities extends AppCompatActivity {

    private TextView scored, total;
    private Button doneBtn, nextbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_activities);
        scored = findViewById(R.id.scored_txt);
        total = findViewById(R.id.total_txt);
        doneBtn = findViewById(R.id.done_btn);
        nextbtn = findViewById(R.id.next_btn);
        scored.setText(String.valueOf(getIntent().getIntExtra("score", 0)));
        total.setText("de " + String.valueOf(getIntent().getIntExtra("total", 0)));

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener el valor del conjunto seleccionado actual
                int selectedSet = getIntent().getIntExtra("selected_set", 1);
                System.out.println(selectedSet);
                // Incrementar el valor del conjunto seleccionado para pasar al siguiente
                //selectedSet++;
                System.out.println(selectedSet);
                // Verificar si hay más conjuntos disponibles o regresar a MainActivity
                if (selectedSet > 3) {
                    // Regresar a MainActivity
                    showCompletionDialog();
                } else {
                    // Iniciar QuestionsActivity con el siguiente conjunto seleccionado
                    Toast.makeText(ScoreActivities.this, "Mostrando conjunto " + selectedSet, Toast.LENGTH_SHORT).show(); // Agregar este Toast para verificar el valor de selectedSet
                    Intent questionsIntent = new Intent(ScoreActivities.this, QuestionsActivity.class);
                    questionsIntent.putExtra("selected_set", selectedSet);
                    startActivity(questionsIntent);
                }

            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void showCompletionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ScoreActivities.this);
        builder.setTitle("¡Felicidades!");
        builder.setMessage("Has completado todas las actividades de Lectura comprensiva. ¡Buen trabajo! Te recomiendo que realices las demás actividades.");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Aquí puedes realizar alguna acción si el usuario acepta (por ejemplo, volver a la actividad principal)
                Intent intent = new Intent(ScoreActivities.this, SelectActivity.class);
                startActivity(intent);
                finish(); // Finaliza la actividad actual para que no se pueda volver a ella desde la siguiente
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}