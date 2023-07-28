package com.example.appeducativa.controlador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appeducativa.R;
import com.example.appeducativa.actividadesRaul.ActividadUno;
import com.example.appeducativa.actividadesSalvaLili.ActividadMemoria;
import com.example.appeducativa.actividadesSalvaLili.Categoritas;
import com.example.appeducativa.actividadesSalvaLili.QuestionsActivity;

public class SelectActivity extends AppCompatActivity {

    private Button btJugarJu;
    private Button btComprension;
    private Button btLogica;

    int id_usu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        btJugarJu = findViewById(R.id.btJuegos);
        btComprension = findViewById(R.id.btComprension);
        btLogica = findViewById(R.id.btLogica);

        id_usu = getIntent().getIntExtra("player", 0);

        btJugarJu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarJuegoActivityLili();
            }
        });

        btComprension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarJuegoSalva();
            }
        });

        btLogica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarJuegoRaul();
            }
        });
    }


    private void mostrarJuegoActivityLili() {
        Intent intent = new Intent(this, ActividadMemoria.class);
        intent.putExtra("player", id_usu);
        startActivity(intent);
    }

    private void mostrarJuegoSalva() {
        Intent intent = new Intent(this, QuestionsActivity.class);
        intent.putExtra("player", id_usu);
        intent.putExtra("player", id_usu);
        startActivity(intent);
    }

    private void mostrarJuegoRaul() {
        int selectedSet = 1; // Reemplaza con el conjunto seleccionado

        Intent intent = new Intent(this, ActividadUno.class);
        intent.putExtra("player", id_usu);
        intent.putExtra("selected_set", selectedSet);
        startActivity(intent);
    }
}