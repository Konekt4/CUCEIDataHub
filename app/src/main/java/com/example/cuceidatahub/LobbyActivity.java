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

        // Obtener el correo del usuario del Intent
        String userEmail = getIntent().getStringExtra("USER_EMAIL");

        // Inicializar vistas
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        searchView = findViewById(R.id.searchView);
        btnUpload = findViewById(R.id.btnUpload);
        btnCreateForms = findViewById(R.id.btnCreateForms);
        bottomNavigation = findViewById(R.id.bottom_navigation);

        // Configurar el BottomNavigationView
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                // Aquí debes reemplazar los IDs con los de tu menú bottom_nav_menu.xml
                if (itemId == R.id.nav_home) {
                    // Ya estamos en Lobby, no hacemos nada
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    abrirPerfil(userEmail);
                    return true;
                } else if (itemId == R.id.nav_notifications) {
                    abrirNotificaciones();
                    return true;
                }
                return false;
            }
        });

        // Configurar el SearchView
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

        // Configurar botones
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
        // Implementa la lógica de búsqueda aquí
        // Puedes mostrar resultados en un RecyclerView o abrir otra actividad
    }

    private void subirArchivo() {
        // Implementa la lógica para subir archivos
        // Puedes usar un Intent para seleccionar archivos o abrir otra actividad
    }

    private void crearFormulario() {
        // Implementa la lógica para crear formularios
        // Puedes abrir otra actividad para este propósito
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Asegurarse de que el ítem de inicio esté seleccionado
        bottomNavigation.setSelectedItemId(R.id.nav_home);
    }
}