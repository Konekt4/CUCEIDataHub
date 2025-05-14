package com.example.cuceidatahub;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ProfileActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private TextView tvNombre, tvMatricula, tvCorreo;
    private Button btnCerrar;
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        // Inicializar vistas
        tvNombre = findViewById(R.id.mostrar_nombre);
        tvMatricula = findViewById(R.id.matricula);
        tvCorreo = findViewById(R.id.correo);
        btnCerrar = findViewById(R.id.button3);
        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    abrirLobby();
                    return true;
                } else if (itemId == R.id.nav_profile) {
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

        dbHelper = new DatabaseHelper(this);

        String correoUsuario = obtenerCorreoUsuarioLogueado();

        cargarDatosUsuario(correoUsuario);

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad
            }
        });
    }

    private String obtenerCorreoUsuarioLogueado() {
        return "usuario@ejemplo.com";
    }

    private void cargarDatosUsuario(String correo) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columnas = {
                DatabaseHelper.COLUMN_NOMBRE,
                DatabaseHelper.COLUMN_APELLIDO_P,
                DatabaseHelper.COLUMN_APELLIDO_M,
                DatabaseHelper.COLUMN_MATRICULA,
                DatabaseHelper.COLUMN_EMAIL
        };

        String filtro = DatabaseHelper.COLUMN_EMAIL + " = ?";
        String[] argumentosFiltro = { correo };

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USUARIOS,   // Tabla
                columnas,                       // Columnas a devolver
                filtro,                         // WHERE
                argumentosFiltro,               // Valores para WHERE
                null,                           // GROUP BY
                null,                           // HAVING
                null                            // ORDER BY
        );

        if (cursor.moveToFirst()) {
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOMBRE));
            String apellidoP = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_APELLIDO_P));
            String apellidoM = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_APELLIDO_M));
            String matricula = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_MATRICULA));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL));

            String nombreCompleto = nombre + " " + apellidoP;
            if (apellidoM != null && !apellidoM.isEmpty()) {
                nombreCompleto += " " + apellidoM;
            }

            tvNombre.setText(nombreCompleto);
            tvMatricula.setText(matricula);
            tvCorreo.setText(email);

        } else {
            tvNombre.setText("Usuario no encontrado");
        }
        cursor.close();
    }

    private void abrirTienda() {
        Intent intent = new Intent(this, TiendaActivity.class);
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
        // Asegúrate de tener una NotificationsActivity creada
        Intent intent = new Intent(this, NotificationsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigation.setSelectedItemId(R.id.nav_profile);
    }
}