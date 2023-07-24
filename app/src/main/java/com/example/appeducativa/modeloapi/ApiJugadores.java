package com.example.appeducativa.modeloapi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appeducativa.clases.Jugador;
import com.example.appeducativa.clases.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiJugadores {

    private static final String BASE_URL = "http://10.0.2.2:8080/api/";

    public static void crearJugador(Context context, Jugador jugador, final Response.Listener<JSONObject> successListener, final Response.ErrorListener errorListener) {
        String url = BASE_URL + "jugador/create";
        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject usuarioObject = new JSONObject();
            JSONObject actividadObject = new JSONObject();
            JSONObject progresoObject = new JSONObject();

            //ENVIA EN ID ROL
            usuarioObject.put("id_usuario", jugador.getUsuarios().getId_usuario());
            actividadObject.put("id_activ", jugador.getActividad().getId_activ());
            progresoObject.put("id_progress", jugador.getProgreso().getId_progress());

            jsonObject.put("usuarios", usuarioObject);
            jsonObject.put("actividad", actividadObject);
            jsonObject.put("progreso", progresoObject);
            jsonObject.put("player_nombre", jugador.getNombre());

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
}
