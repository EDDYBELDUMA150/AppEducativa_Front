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

        ApiUsuarios.obtenerUsuarioPorCorreo(this, correo,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Aquí procesas la respuesta obtenida
                        if (response != null) {
                            // Obtener la contraseña encriptada del usuario del JSON
                            String claveEncriptada = null;
                            try {
                                claveEncriptada = response.getString("usu_contra");
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                            // Verificar si la contraseña ingresada coincide con la contraseña encriptada
                            if (BCrypt.checkpw(clave, claveEncriptada)) {
                                // Las credenciales son válidas
                                mostrarSegundaActivity();
                            } else {
                                // Las credenciales son inválidas
                                mostrarMensaje("Credenciales inválidas");
                            }
                        } else {
                            // Usuario no encontrado
                            mostrarMensaje("Usuario no encontrado");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mostrarMensaje("Volley error");
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

    private void manejaJson(@NonNull JSONArray jsonArray){
        for (int i=0; i<jsonArray.length();i++){
            JSONObject jsonObject=null;
            Usuario publicacion = new Usuario();
            try {
                jsonObject=jsonArray.getJSONObject(i);
                publicacion.setUsu_password(jsonObject.getString("usu_contra"));
                publicacion.setUsu_correo(jsonObject.getString("usu_correo"));
                datos.add(publicacion);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
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
}