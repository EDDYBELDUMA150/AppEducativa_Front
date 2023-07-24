package com.example.appeducativa.modeloapi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appeducativa.clases.Actividad;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiActividades {

    private static final String BASE_URL = "http://10.0.2.2:8080/api/";

    public static void crearActividad(Context context, Actividad actividad, final Response.Listener<JSONObject> successListener, final Response.ErrorListener errorListener) {
        String url = BASE_URL + "actividad/create";

        System.out.println("ID RECURSO "+actividad.getRecursos().getId_recurso()+ "ID TIPO "+actividad.getTipo_aprendizaje().getId_tipo_aprend()+"ID NIVELES "+actividad.getNiveles().getId_nivel());

        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject nivelObject = new JSONObject();
            JSONObject tipoAprenObject = new JSONObject();
            JSONObject recursoObject = new JSONObject();

            nivelObject.put("id_nivel", actividad.getNiveles().getId_nivel());
            recursoObject.put("id_recurso", actividad.getRecursos().getId_recurso());
            tipoAprenObject.put("id_tipo_apren", actividad.getTipo_aprendizaje().getId_tipo_aprend());

            jsonObject.put("recursos", recursoObject);
            jsonObject.put("niveles", nivelObject);
            jsonObject.put("tipoAprendizaje", tipoAprenObject);
            jsonObject.put("act_aprendizaje", actividad.getAct_aprendizaje());
            jsonObject.put("act_descripcion", actividad.getAct_descripcion());
            jsonObject.put("act_dificultad", actividad.getAct_dificultad());
            jsonObject.put("act_estado", actividad.isAct_estado());
            jsonObject.put("act_nombre", actividad.getAct_nombre());
            jsonObject.put("act_puntaje_alcanzado", actividad.getAct_puntaje_alcanzado());
            jsonObject.put("act_puntaje_max", actividad.getAct_puntaje_max()); // Corregir esta línea
            jsonObject.put("act_puntaje_min", actividad.getAct_puntaje_min());
            jsonObject.put("act_respuesta", actividad.getAct_respuesta());

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
