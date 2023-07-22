package com.example.appeducativa.modeloapi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appeducativa.clases.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiResultados {
    private static final String BASE_URL = "http://10.0.2.2:8080/api/";

    public static void obtenerResultados(Context context, final Response.Listener<JSONArray> successListener, final Response.ErrorListener errorListener) {
        String url = BASE_URL + "usuarios/list";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        successListener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorListener.onErrorResponse(error);
                    }
                });

        Volley.newRequestQueue(context).add(request);
    }

    public static void crearResultado(Context context, Usuario usuario, final Response.Listener<JSONObject> successListener, final Response.ErrorListener errorListener) {
        String url = BASE_URL + "usuarios/create";

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("usu_nombre", usuario.getUsu_nombre());
            jsonObject.put("usu_contra", usuario.getUsu_password());
            jsonObject.put("usu_correo", usuario.getUsu_correo());
            jsonObject.put("usu_nivelacademico", usuario.getUsu_nivelacademico());
            jsonObject.put("usu_fecha_nacimiento", usuario.getUsu_fechaNacimiento());
            jsonObject.put("id_rol", usuario.getId_rol());

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            successListener.onResponse(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            errorListener.onErrorResponse(error);
                        }
                    });

            Volley.newRequestQueue(context).add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void eliminarResultado(Context context, int idUsuario, final Response.Listener<String> successListener, final Response.ErrorListener errorListener) {
        String url = BASE_URL + "usuarios/delete/" + idUsuario;

        StringRequest request = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        successListener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorListener.onErrorResponse(error);
                    }
                });

        Volley.newRequestQueue(context).add(request);
    }

    public static void actualizarResultado(Context context, int idUsuario, Usuario usuario, final Response.Listener<JSONObject> successListener, final Response.ErrorListener errorListener) {
        String url = BASE_URL + "usuarios/update/" + idUsuario;

        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("usu_nombre", usuario.getUsu_nombre());
            jsonBody.put("usu_contra", usuario.getUsu_password());
            jsonBody.put("usu_correo", usuario.getUsu_correo());
            jsonBody.put("usu_nivelacademico", usuario.getUsu_nivelacademico());
            jsonBody.put("usu_fecha_nacimiento", usuario.getUsu_fechaNacimiento());
            jsonBody.put("id_rol", 1);
            // Agrega aquí el resto de los campos del usuario que deseas actualizar

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            successListener.onResponse(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            errorListener.onErrorResponse(error);
                        }
                    });

            Volley.newRequestQueue(context).add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
