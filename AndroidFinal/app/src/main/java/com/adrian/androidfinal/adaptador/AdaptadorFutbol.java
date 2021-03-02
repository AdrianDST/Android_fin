package com.adrian.androidfinal.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adrian.androidfinal.R;
import com.adrian.androidfinal.utils.Liga;

import java.util.ArrayList;

public class AdaptadorFutbol extends RecyclerView.Adapter<AdaptadorFutbol.MiHolder> {

    private Context context;
    private ArrayList<Liga> listaLigas;
    private  OnLigaListener listener;

    public void cargadorLiga (Liga liga){
        listaLigas.add(liga);
        this.notifyDataSetChanged();
    }

    public AdaptadorFutbol(Context context) {
        this.context = context;
        this.listaLigas = new ArrayList<>();
        listener = (OnLigaListener) context;
    }

    @NonNull
    @Override
    public MiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_liga, parent, false);
        return new MiHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MiHolder holder, int position) {

        final Liga ligapresente = listaLigas.get(position);
        holder.getNombreLiga().setText(ligapresente.getNombre());

        holder.nombreLiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onLigaSelected(ligapresente);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaLigas.size();
    }

    public interface OnLigaListener {
        void onLigaSelected(Liga liga);
    }

    class MiHolder extends RecyclerView.ViewHolder {
        private TextView nombreLiga;

        public MiHolder(@NonNull View itemView) {
            super(itemView);
            nombreLiga = itemView.findViewById(R.id.recycler_item);
        }

        public TextView getNombreLiga() {
            return nombreLiga;
        }
    }






}