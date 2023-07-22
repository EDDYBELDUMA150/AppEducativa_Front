package com.example.appeducativa.modeloapi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class ApiRoles {
    private static final String BASE_URL = "http://10.0.2.2:8080/api/";

    public static void obtenerRoles(Context context, final Response.Listener<JSONArray> successListener, final Response.ErrorListener errorListener) {
        String url = BASE_URL + "roles/list";

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
}
