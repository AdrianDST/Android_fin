package com.adrian.androidfinal.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adrian.androidfinal.R;
import com.adrian.androidfinal.adaptador.AdaptadorFutbol;
import com.adrian.androidfinal.utils.Liga;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FragmentFutbol extends Fragment {

    private AdaptadorFutbol adaptadorFutbol;
    private RecyclerView recyclerFutbol;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        adaptadorFutbol = new AdaptadorFutbol(getContext());
        rellenarListas();

    }

    private void rellenarListas() {
        String url = "https://www.thesportsdb.com/api/v1/json/1/all_leagues.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                procesarPeticion(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("errores", error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }

    private void procesarPeticion(JSONObject response) {
        try {

            JSONArray arrayLigas = response.getJSONArray("leagues");
            for (int i = 0; i < arrayLigas.length(); i++) {
                JSONObject liga = arrayLigas.getJSONObject(i);
                String idLiga = liga.getString("idLeague");
                String nombreLiga = liga.getString("strLeague");
                adaptadorFutbol.cargadorLiga(new Liga(nombreLiga,idLiga));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_recycler, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        instancias();
        meterEquipos();
    }

    private void meterEquipos() {
        recyclerFutbol.setAdapter(adaptadorFutbol);
        recyclerFutbol.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

    }

    private void instancias() {
        recyclerFutbol = getView().findViewById(R.id.recycler_layout);
    }

}