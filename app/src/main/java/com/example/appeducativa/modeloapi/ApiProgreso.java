package com.example.appeducativa.modeloapi;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.appeducativa.clases.Progreso;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiProgreso {
    private static final String BASE_URL = "http://10.0.2.2:8080/api/";

    public static void crearProgreso(Context context, Progreso progreso, final Response.Listener<JSONObject> successListener, final Response.ErrorListener errorListener) {
        String url = BASE_URL + "progreso/create";

        try {
            JSONObject jsonObject = new JSONObject();
            JSONObject progAprendObject = new JSONObject();

            progAprendObject.put("id_progapre", progreso.getProgreso_aprendizaje().getId_prog_aprend());

            jsonObject.put("progresoAprendizaje", progAprendObject);
            jsonObject.put("prog_nivel", progreso.getProg_nivel());
            jsonObject.put("prog_puntaje_total", progreso.getProg_puntaje_total());
            jsonObject.put("prog_fecha_init", progreso.getProg_fecha_init());

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

    public LiveData<Integer> getIdProgLiveData(Context context) {
        MutableLiveData<Integer> idProgLiveData = new MutableLiveData<>();

        String url = BASE_URL + "progreso/latest";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int idActividad = response.getInt("id_prog");
                            idProgLiveData.setValue(idActividad);
                        } catch (JSONException e) {
                            idProgLiveData.setValue(null);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        idProgLiveData.setValue(null);
                    }
                }
        );

        Volley.newRequestQueue(context).add(request);

        return idProgLiveData;
    }


}
