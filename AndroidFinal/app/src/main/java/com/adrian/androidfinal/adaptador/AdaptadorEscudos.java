package com.adrian.androidfinal.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adrian.androidfinal.R;
import com.adrian.androidfinal.utils.Equipo;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdaptadorEscudos extends RecyclerView.Adapter<AdaptadorEscudos.MiHolder> {

    private Context context;
    private ArrayList<Equipo> listaEscudos;
    private OnEscudoListener escudoListener;

    public void MeterEscudo (Equipo equipo){
        listaEscudos.add(equipo);
        this.notifyDataSetChanged();
    }

    public AdaptadorEscudos (Context context){
        this.context=context;
        this.listaEscudos= new ArrayList<>();
        escudoListener = (OnEscudoListener) context;
    }


    @NonNull
    @Override
    public MiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_escudos, parent, false);
        return new AdaptadorEscudos.MiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiHolder holder, int position) {
        Equipo equipoEscudo = listaEscudos.get(position);
        Glide.with(context).load(equipoEscudo.getEscudo()).into(holder.imagenEscudo);
        holder.botonDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escudoListener.onEquipoListener(equipoEscudo);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listaEscudos.size();
    }
    public interface OnEscudoListener {
        void onEquipoListener(Equipo equipo);
    }


    class MiHolder extends RecyclerView.ViewHolder {
        private ImageView imagenEscudo;
        private Button botonDetalle;


        public MiHolder(@NonNull View itemView) {
            super(itemView);
            imagenEscudo = itemView.findViewById(R.id.imagen_escudo);
            botonDetalle = itemView.findViewById(R.id.boton_detalle);

        }
    }
}
