package com.example.appeducativa.actividadesSalvaLili;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import com.example.appeducativa.R;
import com.example.appeducativa.controlador.InicioApp;

public class setsActivity extends AppCompatActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_tipo_aprendizaje);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));

        gridView = findViewById(R.id.gridview);

        GridAdater adater = new GridAdater(3);
        gridView.setAdapter(adater);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Crear un Intent para volver a la actividad anterior (en este caso, la actividad principal)
            Intent upIntent = new Intent(this, InicioApp.class);

            // Agregar el conjunto seleccionado como extra en el Intent
            upIntent.putExtra("selected_set", 1); // Aquí, seleccionamos el conjunto 1 por defecto al volver

            // Navegar hacia la actividad principal con el conjunto seleccionado
            NavUtils.navigateUpTo(this, upIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}