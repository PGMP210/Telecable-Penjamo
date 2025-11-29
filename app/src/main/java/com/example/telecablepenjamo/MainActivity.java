package com.example.telecablepenjamo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnIniciar;
    private Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar componentes
        btnIniciar = findViewById(R.id.btnIniciar);
        btnRegistro = findViewById(R.id.btnRegistro);

        // Verificar que los botones no sean null
        if (btnIniciar == null) {
            Toast.makeText(this, "Error: No se encontró el botón Iniciar", Toast.LENGTH_SHORT).show();
            return;
        }

        if (btnRegistro == null) {
            Toast.makeText(this, "Error: No se encontró el botón Registro", Toast.LENGTH_SHORT).show();
            return;
        }

        // Configurar eventos de click
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar mensaje de prueba
                Toast.makeText(MainActivity.this, "Navegando a Login...", Toast.LENGTH_SHORT).show();

                // Navegar a la pantalla de Login
                try {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error al abrir Login: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes crear una nueva Activity para Registro
                // Por ahora mostraremos un mensaje
                Toast.makeText(MainActivity.this,
                        "Pantalla de Registro - Próximamente",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}