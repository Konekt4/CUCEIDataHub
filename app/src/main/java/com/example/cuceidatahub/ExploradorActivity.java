package com.example.cuceidatahub;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ExploradorActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.explorador);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    abrirLobby();
                    return true;
                } else if (itemId == R.id.nav_explorar) {
                    return true;
                } else if (itemId == R.id.nav_shop) {
                    abrirTienda();
                    return true;
                } else if (itemId == R.id.nav_notifications) {
                    abrirNotificacion();
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    abrirPerfil();
                    return true;
                }
                return false;
            }
        });
}

    private void abrirPerfil() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
    private void abrirNotificacion() {
        Intent intent = new Intent(this, NotificationsActivity.class);
        startActivity(intent);
    }
    private void abrirLobby() {
        Intent intent = new Intent(this, LobbyActivity.class);
        startActivity(intent);
    }

    private void abrirTienda() {
        Intent intent = new Intent(this, TiendaActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigation.setSelectedItemId(R.id.nav_explorar);
    }

}
