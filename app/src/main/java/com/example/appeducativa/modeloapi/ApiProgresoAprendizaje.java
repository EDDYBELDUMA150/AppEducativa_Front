package com.example.appeducativa.modeloapi;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appeducativa.clases.Progreso_Aprendizaje;
import com.example.appeducativa.clases.Resultados;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiProgresoAprendizaje {

    private static final String BASE_URL = "http://10.0.2.2:8080/api/";
    public static void crearProgresoApren(Context context, Progreso_Aprendizaje progreso_aprendizaje, final Response.Listener<JSONObject> successListener, final Response.ErrorListener errorListener) {
        String url = BASE_URL + "progresoAprendizaje/create";

        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject tipoAprendObject = new JSONObject();
            JSONObject resultadoObject = new JSONObject();

            System.out.println(progreso_aprendizaje.getId_resultado().getId_resultado());

            resultadoObject.put("id_resultado", progreso_aprendizaje.getId_resultado().getId_resultado());
            tipoAprendObject.put("id_tipo_apren", progreso_aprendizaje.getId_tipo_apren().getId_tipo_aprend());

            jsonObject.put("resultados", resultadoObject);
            jsonObject.put("tipoAprendizaje", tipoAprendObject);
            jsonObject.put("progapr_nombre", progreso_aprendizaje.getProgApren_nombre());
            jsonObject.put("progapr_punntaje_aprendizaje", progreso_aprendizaje.getProgApren_puntaje());
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

    public LiveData<Integer> getIdProgApLiveData(Context context) {
        MutableLiveData<Integer> idLiveData = new MutableLiveData<>();

        String url = BASE_URL + "progresoAprendizaje/latest";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int idActividad = response.getInt("id_proa");
                            idLiveData.setValue(idActividad);
                        } catch (JSONException e) {
                            idLiveData.setValue(null);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        idLiveData.setValue(null);
                    }
                }
        );

        Volley.newRequestQueue(context).add(request);

        return idLiveData;
    }
}
