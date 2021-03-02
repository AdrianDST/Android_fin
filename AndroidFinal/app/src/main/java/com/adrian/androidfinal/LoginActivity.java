package com.adrian.androidfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText textoContraseña, textoUsuario;
    private Button botonRegistro,botonLogear;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        instancias();
        acciones();
    }

    private void acciones() {
        botonRegistro.setOnClickListener(this);
        botonLogear.setOnClickListener(this);
    }

    private void instancias() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        textoContraseña = findViewById(R.id.contraseña);
        textoUsuario = findViewById(R.id.usuario);
        botonRegistro= findViewById(R.id.boton_registro);
        botonLogear = findViewById(R.id.boton_log);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.boton_registro:
                firebaseAuth.createUserWithEmailAndPassword(textoUsuario.getText().toString(),textoContraseña.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Log.v("usuarios","el usuario se ha creado correctamente");
                                    firebaseUser = firebaseAuth.getCurrentUser();
                                    Log.v("usuarios",firebaseUser.getUid());
                                }else {
                                    Log.v("usuarios","el usuario no se ha creado correctamente");
                                }
                            }
                        });


                break;
            case R.id.boton_log:
                firebaseAuth.signInWithEmailAndPassword(textoUsuario.getText().toString(),textoContraseña.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.v("usuarios","Te has logeado correctamente");
                            Log.v("usuarios",firebaseAuth.getCurrentUser().getUid());
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Log.v("usuarios","login incorrecto");
                        }
                    }
                });
                break;


        }
    }
}