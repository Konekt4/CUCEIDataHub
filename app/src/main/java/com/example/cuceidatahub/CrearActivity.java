package com.example.cuceidatahub;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CrearActivity extends AppCompatActivity {
    private EditText editTextNombre, editTextApellidoP, editTextApellidoM, editTextMatricula, editTextPassword;
    private String email;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear);

        email = getIntent().getStringExtra("EMAIL");

        editTextNombre = findViewById(R.id.editTextText4);
        editTextApellidoP = findViewById(R.id.editTextText3);
        editTextApellidoM = findViewById(R.id.editTextText);
        editTextMatricula = findViewById(R.id.editTextText2);
        editTextPassword = findViewById(R.id.editTextTextPassword);

        dbHelper = new DatabaseHelper(this);

        Button btnRegistrar = findViewById(R.id.button);
        btnRegistrar.setOnClickListener(v -> registrarUsuario());

        Button btnRegresar = findViewById(R.id.button2);
        btnRegresar.setOnClickListener(v -> finish());
    }

    private void registrarUsuario() {
        String nombre = editTextNombre.getText().toString().trim();
        String apellidoP = editTextApellidoP.getText().toString().trim();
        String apellidoM = editTextApellidoM.getText().toString().trim();
        String matricula = editTextMatricula.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (nombre.isEmpty() || apellidoP.isEmpty() || matricula.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        long resultado = dbHelper.insertarUsuario(
                email,
                nombre,
                apellidoP,
                apellidoM,
                matricula,
                password
        );

        if (resultado != -1) {
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}