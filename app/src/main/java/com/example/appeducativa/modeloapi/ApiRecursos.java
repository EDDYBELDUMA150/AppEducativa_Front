package com.example.appeducativa.modeloapi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appeducativa.clases.Recursos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiRecursos {
    private static final String BASE_URL = "http://10.0.2.2:8080/api/";

    public static void listarRecursos(Context context, final Response.Listener<List<Recursos>> successListener, final Response.ErrorListener errorListener) {
        String url = BASE_URL + "recursos/list";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            List<Recursos> recursosList = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int idRecurso = jsonObject.getInt("id_recurso");
                                String recEnlaces = jsonObject.getString("rec_enlaces");
                                String recLec = jsonObject.getString("rec_lec");

                                Recursos recurso = new Recursos(idRecurso, recEnlaces, recLec);
                                recursosList.add(recurso);
                            }
                            successListener.onResponse(recursosList);
                        } catch (JSONException e) {
                            errorListener.onErrorResponse(new VolleyError("Error al procesar la respuesta del servidor."));
                        }
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

    // Método para obtener la lectura por conjunto desde el servidor
    public static void obtenerLecturaPorConjunto(Context context, int selectedSet,
                                                 Response.Listener<JSONObject> successListener,
                                                 Response.ErrorListener errorListener) {
        String url = BASE_URL + "recursos/buscarlec/" + selectedSet; // Reemplaza "lecturas" con el endpoint correcto de lecturas en tu API

        // Realizar la solicitud al servidor usando Volley o Retrofit u otra librería similar
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // La respuesta contiene la lectura obtenida desde el servidor
                        successListener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar el error en caso de que ocurra
                        errorListener.onErrorResponse(error);
                    }
                });

        Volley.newRequestQueue(context).add(request);
    }
}
