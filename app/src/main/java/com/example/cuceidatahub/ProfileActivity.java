package com.example.cuceidatahub;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private TextView tvNombre, tvMatricula, tvCorreo;
    private Button btnCerrar;
    private ImageView ivFotoPerfil;
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
        ivFotoPerfil = findViewById(R.id.imageView3);
        bottomNavigation = findViewById(R.id.bottom_navigation);

        // Inicializar helper de base de datos
        dbHelper = new DatabaseHelper(this);

        // Obtener el correo del usuario logueado
        String correoUsuario = obtenerCorreoUsuarioLogueado();

        // Cargar datos del usuario
        cargarDatosUsuario(correoUsuario);

        // Configurar el botón de cerrar
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad
            }
        });
    }

    private String obtenerCorreoUsuarioLogueado() {
        // Implementa este método para obtener el correo del usuario actual
        // Puede venir de SharedPreferences, extras del Intent, etc.
        // Por ahora devuelve un valor fijo - reemplaza con tu implementación real
        return "usuario@ejemplo.com";
    }

    private void cargarDatosUsuario(String correo) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Columnas que queremos obtener
        String[] columnas = {
                DatabaseHelper.COLUMN_NOMBRE,
                DatabaseHelper.COLUMN_APELLIDO_P,
                DatabaseHelper.COLUMN_APELLIDO_M,
                DatabaseHelper.COLUMN_MATRICULA,
                DatabaseHelper.COLUMN_EMAIL
        };

        // Filtro para buscar por correo
        String filtro = DatabaseHelper.COLUMN_EMAIL + " = ?";
        String[] argumentosFiltro = { correo };

        // Ejecutar consulta
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
            // Obtener datos del cursor
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOMBRE));
            String apellidoP = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_APELLIDO_P));
            String apellidoM = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_APELLIDO_M));
            String matricula = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_MATRICULA));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL));

            // Combinar nombre completo
            String nombreCompleto = nombre + " " + apellidoP;
            if (apellidoM != null && !apellidoM.isEmpty()) {
                nombreCompleto += " " + apellidoM;
            }

            // Mostrar datos en la interfaz
            tvNombre.setText(nombreCompleto);
            tvMatricula.setText(matricula);
            tvCorreo.setText(email);

            // Aquí podrías cargar una foto de perfil si la tienes en la base de datos
            // ivFotoPerfil.setImageBitmap(...);
        } else {
            // Usuario no encontrado
            tvNombre.setText("Usuario no encontrado");
        }
        cursor.close();
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Asegurarse de que el ítem de inicio esté seleccionado
        bottomNavigation.setSelectedItemId(R.id.nav_profile);
    }
}