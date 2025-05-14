package com.example.cuceidatahub;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TiendaActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tienda);

        bottomNavigation = findViewById(R.id.bottom_navigation);

    }
    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigation.setSelectedItemId(R.id.nav_shop);
    }

}
