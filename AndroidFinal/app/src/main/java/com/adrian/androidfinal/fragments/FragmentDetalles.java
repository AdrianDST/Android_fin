package com.adrian.androidfinal.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.adrian.androidfinal.R;
import com.adrian.androidfinal.dialogos.Dialogo;
import com.adrian.androidfinal.utils.Equipo;
import com.bumptech.glide.Glide;


public class FragmentDetalles extends Fragment {
    private Equipo equipo;
    private Button botonRedes,botonFavorito;
    private ImageView imageView;
    private TextView nombreEquipo,descripcionEquipo;
    public void meterDetalle(){
        nombreEquipo.setText(equipo.getNombreEs());
        descripcionEquipo.setText(equipo.getDescripcion());
        Glide.with(getContext()).load(equipo.getBanner()).into(imageView);
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getArguments()!=null){
            equipo= (Equipo) this.getArguments().getSerializable("equipo");
        }


    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.item_descripcion,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        instancias();
        meterDetalle();
        acciones();

    }
    public static FragmentDetalles newInstance(Equipo equipo) {
        Bundle args = new Bundle();
        args.putSerializable("equipo",equipo);
        FragmentDetalles fragment = new FragmentDetalles();
        fragment.setArguments(args);
        return fragment;
    }
    public void instancias(){
    botonRedes = getView().findViewById(R.id.boton_redes);
    botonFavorito = getView().findViewById(R.id.boton_favorito);
    imageView = getView().findViewById(R.id.imagen_banner);
    nombreEquipo = getView().findViewById(R.id.nombre_equipo);
    descripcionEquipo = getView().findViewById(R.id.texto_descripcion);
    }
    public void acciones(){
    botonRedes.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Dialogo dialogo = Dialogo.newInstance(equipo);
            dialogo.show(getFragmentManager(),"tag");
        }
    });
    botonFavorito.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    });
    }

}
