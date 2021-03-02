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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adrian.androidfinal.R;
import com.adrian.androidfinal.adaptador.AdaptadorEscudos;
import com.adrian.androidfinal.utils.Equipo;
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

public class FragmentEscudo extends Fragment {
    private RecyclerView recyclerEscudo;
    private AdaptadorEscudos adaptadorEscudos;
    private Liga liga;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getArguments() != null){
            liga = (Liga) getArguments().getSerializable("liga");
            adaptadorEscudos = new AdaptadorEscudos(getContext());
            rellenarListas();
        }
    }
    public static FragmentEscudo newInstance(Liga liga) {

        Bundle args = new Bundle();
        args.putSerializable("liga",liga);
        FragmentEscudo fragment = new FragmentEscudo();
        fragment.setArguments(args);
        return fragment;
    }
    private void rellenarListas() {
        String url = String.format("https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=%s",liga.getId());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                procesarPeticion(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("prueba", error.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }

    private void procesarPeticion(JSONObject response) {
        try {
            Log.v("prueba","procesar peticion");
            JSONArray arrayEscudo = response.getJSONArray("teams");
            for (int i = 0; i < arrayEscudo.length(); i++) {
                Log.v("prueba","procesar peticion 2");
                JSONObject equipos = arrayEscudo.getJSONObject(i);
                String nombreEquipo = equipos.getString("strTeam");
                String id = equipos.getString("idTeam");
                String facebook = equipos.getString("strFacebook");
                String instagram = equipos.getString("strInstagram");
                String escudo = equipos.getString("strTeamBadge");
                String descripcion = equipos.getString("strDescriptionEN");
                String banner = equipos.getString("strTeamBanner");
                String twitter = equipos.getString("strTwitter");
                String paginaweb = equipos.getString("strWebsite");
                adaptadorEscudos.MeterEscudo(new Equipo(nombreEquipo,descripcion,banner,escudo,twitter,facebook,instagram,paginaweb,id));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.layout_recycler,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        instancias();
        meterEscudos();
    }
    public void meterEscudos(){
        recyclerEscudo.setAdapter(adaptadorEscudos);
        recyclerEscudo.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }
    public void instancias(){
        recyclerEscudo = getView().findViewById(R.id.recycler_layout);
    }
}
