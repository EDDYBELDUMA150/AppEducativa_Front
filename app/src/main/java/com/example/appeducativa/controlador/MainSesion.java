package com.example.appeducativa.controlador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.appeducativa.R;
import com.example.appeducativa.clases.Usuario;
import com.example.appeducativa.modeloapi.ApiUsuarios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;

public class MainSesion extends AppCompatActivity {

    private EditText correoEditText;
    private EditText contraseñaEditText;
    private Button iniciarSesionButton;
    private Button registroButton;

    ArrayList<Usuario> datos =new ArrayList<>();

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insesion);

        correoEditText = findViewById(R.id.txtInicioS_email);
        contraseñaEditText = findViewById(R.id.txtInicioS_Clave);
        iniciarSesionButton = findViewById(R.id.btInicioS_inicioSesion);
        registroButton = findViewById(R.id.btInicioS_registrarse);



        iniciarSesionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciaSesion(correoEditText.getText().toString(), contraseñaEditText.getText().toString());
                //obtenerId();
            }
        });

        registroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarRegistroActivity();
            }
        });
    }


    public void iniciaSesion(String correo, String clave) {

        ApiUsuarios.loginApi(this, correo, clave,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Obtener el mensaje de la respuesta
                            String message = response.getString("message");
                            Usuario us = new Usuario();
                            us.setId_usuario(Integer.parseInt(response.getString("id_usuario")));
                            us.setUsu_correo(response.getString("correo"));
                            us.setUsu_fechaNacimiento(response.getString("usu_fecha_nacimiento"));
                            us.setUsu_nivelacademico(response.getString("usu_nivelacademico"));
                            us.setUsu_nombre(response.getString("usu_nombre"));
                            us.setUsu_fecha_inic(response.getString("usu_fecha_inic"));

                            enviarDataPerfil(us);
                            enviarDataJugador(us);

                            // Manejar diferentes casos de respuesta
                            if (message.equals("Inicio de sesión exitoso")) {
                                // Las credenciales son válidas, mostrar la siguiente actividad
                                mostrarSegundaActivity();
                            } else if (message.equals("Usuario no encontrado")) {
                                // Usuario no encontrado, mostrar mensaje de error
                                mostrarMensaje("Usuario no encontrado. Verifica tus credenciales.");
                            } else if (message.equals("Contraseña incorrecta")) {
                                // Contraseña incorrecta, mostrar mensaje de error
                                mostrarMensaje("Contraseña incorrecta. Verifica tus credenciales.");
                            } else {
                                // Otro mensaje de error desconocido, mostrar mensaje genérico
                                mostrarMensaje("Error en el inicio de sesión. Inténtalo de nuevo más tarde.");
                            }
                        } catch (JSONException e) {
                            // Error al procesar la respuesta JSON
                            mostrarMensaje("Error en el formato de la respuesta del servidor.");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        mostrarMensaje("Error de conexión. Verifica tu conexión a Internet.");
                    }
                });
    }
    private boolean validateCredentials(String correo, String clave, JSONArray usuarios) {
        for (int i = 0; i < usuarios.length(); i++) {
            try {
                JSONObject usuario = usuarios.getJSONObject(i);
                String correoUsuario = usuario.getString("usu_correo");
                String claveUsuario = usuario.getString("usu_contra");

                // Verificar si los datos ingresados coinciden con los datos del usuario actual
                if (correo.equals(correoUsuario) && clave.equals(claveUsuario)) {
                    return true; // Las credenciales son válidas
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return false; // Las credenciales son inválidas
    }

    private void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void mostrarSegundaActivity() {
        Intent intent = new Intent(this, InicioApp.class);
        startActivity(intent);
    }

    private void mostrarRegistroActivity() {
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }

    public void enviarDataPerfil(Usuario us){
        Miperfil perfil = new Miperfil();
        perfil.setUsuario(us);
    }

    public void enviarDataJugador(Usuario us){
        NombreJugador nombreJugador = new NombreJugador();
        nombreJugador.setUsuario(us);
    }
}