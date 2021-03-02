package com.adrian.androidfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import android.view.MenuItem;
import android.widget.FrameLayout;


import com.adrian.androidfinal.adaptador.AdaptadorEscudos;
import com.adrian.androidfinal.adaptador.AdaptadorFutbol;
import com.adrian.androidfinal.fragments.FragmentDetalles;
import com.adrian.androidfinal.fragments.FragmentEscudo;
import com.adrian.androidfinal.fragments.FragmentFutbol;
import com.adrian.androidfinal.utils.Equipo;
import com.adrian.androidfinal.utils.Liga;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements AdaptadorFutbol.OnLigaListener, AdaptadorEscudos.OnEscudoListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigation;
    private FrameLayout sitioFragment;
    private FragmentManager fragMan;
    private FragmentTransaction fragTran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instancias();
        acciones();
        fragMan = getSupportFragmentManager();
        fragTran = fragMan.beginTransaction();
        fragTran.replace(R.id.pintar_fragments, new FragmentFutbol(), "tag");
        fragTran.addToBackStack("ligas");
        fragTran.commit();

    }

    private void acciones() {
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.opcion_futbol_1:
                        fragMan = getSupportFragmentManager();
                        fragTran = fragMan.beginTransaction();
                        fragTran.replace(R.id.pintar_fragments, new FragmentFutbol(), "Pantalla_Futbol");
                        fragTran.addToBackStack("tag");
                        fragTran.commit();
                        break;
                    case R.id.opcion_favoritos_2:

                        break;
                    case R.id.opcion_salir_3:
                        finish();
                        break;
                }
                return true;
            }
        });
    }
    private void instancias() {
        sitioFragment = findViewById(R.id.pintar_fragments);
        drawerLayout = findViewById(R.id.main_drawer);
        navigation = findViewById(R.id.menu_navigation);
    }

    @Override
    public void onLigaSelected(Liga liga) {
        fragMan = getSupportFragmentManager();
        fragTran = fragMan.beginTransaction();
        fragTran.replace(R.id.pintar_fragments, FragmentEscudo.newInstance(liga), "");
        fragTran.addToBackStack("tag1");
        fragTran.commit();

    }

    @Override
    public void onEquipoListener(Equipo equipo) {
        fragMan = getSupportFragmentManager();
        fragTran = fragMan.beginTransaction();
        fragTran.replace(R.id.pintar_fragments, FragmentDetalles.newInstance(equipo), "");
        fragTran.addToBackStack("tag2");
        fragTran.commit();
    }
}