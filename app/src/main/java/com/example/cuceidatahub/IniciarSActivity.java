package com.example.cuceidatahub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class IniciarSActivity extends AppCompatActivity {

    private Button crearCuenta;
    private EditText editTextEmail, editTextPassword;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciars);

        crearCuenta = findViewById(R.id.button2);
        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextTextPassword);

        dbHelper = new DatabaseHelper(this);

        crearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IniciarSActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });

        Button btnIniciarSesion = findViewById(R.id.button);
        btnIniciarSesion.setOnClickListener(v -> validarCredenciales());
    }

    private void validarCredenciales() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {DatabaseHelper.COLUMN_EMAIL};
        String selection = DatabaseHelper.COLUMN_EMAIL + " = ? AND " +
                DatabaseHelper.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        try (Cursor cursor = db.query(
                DatabaseHelper.TABLE_USUARIOS,
                projection,
                selection,
                selectionArgs,
                null, null, null)) {

            if (cursor.moveToFirst()) {
                // Guarda el correo en SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("USER_EMAIL", email);  // Guarda el correo
                editor.apply();

                // Redirigir a LobbyActivity
                Intent intent = new Intent(IniciarSActivity.this, LobbyActivity.class);
                intent.putExtra("USER_EMAIL", email);  // Pasar el correo a LobbyActivity
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Email o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}