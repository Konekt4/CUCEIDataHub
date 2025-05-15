package com.example.cuceidatahub;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class TiendaActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tienda);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    abrirLobby();
                    return true;
                } else if (itemId == R.id.nav_shop) {
                    return true;
                } else if (itemId == R.id.nav_notifications) {
                    abrirNotificaciones();
                    return true;
                } else if (itemId == R.id.nav_explorar) {
                    abrirExplorador();
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
    private void abrirExplorador() {
        Intent intent = new Intent(this, ExploradorActivity.class);
        startActivity(intent);
    }
    private void abrirLobby() {
        Intent intent = new Intent(this, LobbyActivity.class);
        startActivity(intent);
    }

    private void abrirNotificaciones() {
        Intent intent = new Intent(this, NotificationsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigation.setSelectedItemId(R.id.nav_shop);
    }

}
