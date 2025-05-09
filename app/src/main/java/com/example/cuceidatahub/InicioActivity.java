package com.example.cuceidatahub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class InicioActivity extends AppCompatActivity {

    private EditText emailEditText;
    private Button continuarBtn;
    private Button iniciarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);

        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        continuarBtn = findViewById(R.id.button);
        iniciarBtn = findViewById(R.id.button2);

        continuarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarEmail()) {
                    String email = emailEditText.getText().toString().trim();
                    Intent intent = new Intent(InicioActivity.this, CrearActivity.class);
                    intent.putExtra("EMAIL", email); // Envía el email como extra
                    startActivity(intent);
                }
            }
        });

        iniciarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir a IniciarSActivity
                Intent intent = new Intent(InicioActivity.this, IniciarSActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validarEmail() {
        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa tu correo electrónico", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Por favor ingresa un correo electrónico válido", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}