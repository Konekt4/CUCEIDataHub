package com.example.cuceidatahub;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class LobbyActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    private SearchView searchView;
    private Button btnUpload, btnCreateForms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby);

        String userEmail = getIntent().getStringExtra("USER_EMAIL");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchView = findViewById(R.id.searchView);
        btnUpload = findViewById(R.id.btnUpload);
        btnCreateForms = findViewById(R.id.btnCreateForms);
        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    abrirPerfil(userEmail);
                    return true;
                } else if (itemId == R.id.nav_notifications) {
                    abrirNotificaciones();
                    return true;
                } else if (itemId == R.id.nav_explorar) {
                    abrirExplorador();
                    return true;
                } else if (itemId == R.id.nav_shop) {
                    abrirTienda();
                    return true;
                }
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                buscarMaterial(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirArchivo();
            }
        });

        btnCreateForms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearFormulario();
            }
        });
    }
    private void abrirTienda() {
        Intent intent = new Intent(this, TiendaActivity.class);
        startActivity(intent);
    }
    private void abrirExplorador() {
        Intent intent = new Intent(this, ExploradorActivity.class);
        startActivity(intent);
    }
    private void abrirPerfil(String email) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("USER_EMAIL", email);
        startActivity(intent);
    }

    private void abrirNotificaciones() {
        // Asegúrate de tener una NotificationsActivity creada
        Intent intent = new Intent(this, NotificationsActivity.class);
        startActivity(intent);
    }

    private void buscarMaterial(String query) {
    }

    private void subirArchivo() {
    }

    private void crearFormulario() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigation.setSelectedItemId(R.id.nav_home);
    }
}