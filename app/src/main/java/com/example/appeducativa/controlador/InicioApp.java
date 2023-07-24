package com.example.appeducativa.controlador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appeducativa.R;
import com.example.appeducativa.actividadesSalvaLili.ActividadMemoria;
import com.example.appeducativa.actividadesSalvaLili.Categoritas;
import com.example.appeducativa.clases.Usuario;

public class InicioApp extends AppCompatActivity {

    private Button btJugar;
    private Button btAjustes;
    private Button btPerfil;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btJugar = findViewById(R.id.btJugar);
        btAjustes = findViewById(R.id.btAjustes);
        btPerfil = findViewById(R.id.btMiPerfil);

        btJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarJuegoActivityLili();
            }
        });

        btAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarJuegoActivitySalva();
            }
        });

        btPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarPerfil();
            }
        });
    }

    private void mostrarJuegoActivityLili() {
        Intent intent = new Intent(this, ActividadMemoria.class);
        startActivity(intent);
    }

    private void mostrarJuegoActivitySalva() {
        Intent intent = new Intent(this, Categoritas.class);
        startActivity(intent);
    }

    private void mostrarPerfil() {
        Intent intent = new Intent(this, Miperfil.class);
        startActivity(intent);
    }

    public int traerDatos(Usuario us){
        int id = us.getId_usuario();
        return id;
    }

}