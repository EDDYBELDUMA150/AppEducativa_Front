package com.example.appeducativa.controlador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appeducativa.R;
import com.example.appeducativa.actividadesSalvaLili.ActividadMemoria;
import com.example.appeducativa.actividadesSalvaLili.Categoritas;
import com.example.appeducativa.actividadesSalvaLili.QuestionsActivity;
import com.example.appeducativa.clases.Usuario;

public class InicioApp extends AppCompatActivity {

    private Button btJugar;
    private Button btAjustes;
    private Button btPerfil;
    private Usuario usuario;
    int id_usu;

    private Button starButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btJugar = findViewById(R.id.btJugar);
        btAjustes = findViewById(R.id.btAjustes);
        btPerfil = findViewById(R.id.btMiPerfil);

        id_usu = getIntent().getIntExtra("player", 0);


        btJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectActiv();
            }
        });

        btAjustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirPaginaWeb();
            }
        });

        btPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarPerfil();
            }
        });
    }

    private void selectActiv() {
        Intent intent = new Intent(this, SelectActivity.class);
        intent.putExtra("player", id_usu); //ENVIAMOS EL ID DEL JUGADOR
        startActivity(intent);
    }

    private void mostrarPerfil() {
        Intent intent = new Intent(this, Miperfil.class);
        startActivity(intent);
    }

    private void abrirPaginaWeb() {
        String url = "http://10.0.2.2:4200/home"; // URL de la página web que deseas abrir
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }


    public int traerDatos(Usuario us){
        int id = us.getId_usuario();
        return id;
    }



}