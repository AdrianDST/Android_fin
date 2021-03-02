package com.adrian.androidfinal.dialogos;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.adrian.androidfinal.R;
import com.adrian.androidfinal.utils.Equipo;

public class Dialogo extends DialogFragment {
    private View view;
    private Equipo equipo;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(view);

        TextView Paginaweb = view.findViewById(R.id.texto_web);
        Paginaweb.setText(equipo.getWeb());

        TextView Twitter = view.findViewById(R.id.texto_twitter);

        Twitter.setText(equipo.getTwitter());

        TextView Instagram = view.findViewById(R.id.texto_instagram);

        Instagram.setText(equipo.getInstagram());

        TextView Facebook = view.findViewById(R.id.texto_facebook);

        Facebook.setText(equipo.getFacebook());



        return builder.create();

    }


    public static Dialogo newInstance(Equipo equipo) {
        Bundle args = new Bundle();
        args.putSerializable("equipo", equipo);
        Dialogo dialogo = new Dialogo();
        dialogo.setArguments(args);
        return dialogo;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getArguments() != null) {
            equipo = (Equipo) this.getArguments().getSerializable("equipo");
        }
        view = View.inflate(context, R.layout.layout_sociales, null);


    }


}
