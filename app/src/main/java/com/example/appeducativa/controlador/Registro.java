package com.example.appeducativa.controlador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.appeducativa.R;
import com.example.appeducativa.clases.Roles;
import com.example.appeducativa.clases.Usuario;
import com.example.appeducativa.modeloapi.ApiUsuarios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Registro extends AppCompatActivity {

    ArrayList<Roles> datos =new ArrayList<>();

    Calendar calendar = Calendar.getInstance();
    EditText NombreEdt;
    EditText ContraEdt;
    EditText ContraVeriEdt;
    EditText CorreoEdt;
    Spinner nivelAcademicoEdt;
    TextView fechaNacimientoEdt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        NombreEdt = findViewById(R.id.txtNombre);
        ContraEdt = findViewById(R.id.txtPassword);
        ContraVeriEdt = findViewById(R.id.txtVeriPassword);
        CorreoEdt = findViewById(R.id.txtCorreo);
        nivelAcademicoEdt = findViewById(R.id.cbNivelAcademico);
        fechaNacimientoEdt = findViewById(R.id.txtViewFechaN);
        Button seleccFecha = findViewById(R.id.btFechaSeleccion);
        Button registrarBt = findViewById(R.id.btRegis_registro);

        itemsSpinner();

        seleccFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirDatePicker();
            }
        });

        registrarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario();
            }
        });
    }

    private void abrirDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Crear un DatePickerDialog y configurar el Listener de fecha seleccionada
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                // Formatear el mes con dos dígitos
                String MesDosDigitos = String.format("%02d", month);
                String DiaDosDigitos = String.format("%02d", dayOfMonth);

                String fechaSeleccionada = year + "-" + MesDosDigitos + "-" + DiaDosDigitos;
                fechaNacimientoEdt.setText(fechaSeleccionada);

            }
        }, year, month, dayOfMonth);

        datePickerDialog.show();
    }

        private void registrarUsuario(){

        String nombre = NombreEdt.getText().toString();
        String contra = ContraEdt.getText().toString();
        String correo = CorreoEdt.getText().toString();
        String nivelAcademico = nivelAcademicoEdt.getSelectedItem().toString();
        String fechaNacimiento = fechaNacimientoEdt.getText().toString();
        Roles rol = new Roles();
        rol.setId_rol(2);
        rol.setRol_nombre("Usuarios");

            Usuario usreg = new Usuario(nombre, contra, correo, nivelAcademico, fechaNacimiento, rol);

            ApiUsuarios.crearUsuario(this, usreg, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // Aquí procesas la respuesta obtenida
                    // manejaJson(response);
                    Toast.makeText(Registro.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    limpiar();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    mostrarMensaje("Volley error");
                    error.printStackTrace();
                }
            });
    }

    private void mostrarInicioActivity() {

    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void itemsSpinner() {
        List<String> spinnerItems = new ArrayList<>();
        spinnerItems.add("Ninguno");
        spinnerItems.add("1° - 3° año de Eduacion");
        spinnerItems.add("4° - 6° año de Eduacion");
        spinnerItems.add("7° - 9° año de Eduacion");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerItems);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        nivelAcademicoEdt = findViewById(R.id.cbNivelAcademico);
        nivelAcademicoEdt.setAdapter(spinnerAdapter);
    }

    private void limpiar(){
        NombreEdt.setText("");
        CorreoEdt.setText("");
        ContraEdt.setText("");
        ContraVeriEdt.setText("");
        fechaNacimientoEdt.setText("0000-00-00");
        nivelAcademicoEdt.setSelection(0);
    }


}