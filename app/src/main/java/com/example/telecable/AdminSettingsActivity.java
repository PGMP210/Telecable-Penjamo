package com.example.telecable;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class AdminSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_settings);

        Button btnLogout = findViewById(R.id.btn_logout);
        Button btnClients = findViewById(R.id.btn_manage_clients);

        // 1. Botón Cerrar Sesión
        btnLogout.setOnClickListener(v -> {
            // Aquí iría la lógica real para cerrar sesión (Firebase/BD)
            Toast.makeText(this, "Cerrando sesión de administrador...", Toast.LENGTH_LONG).show();
            // Redirigir al Main o Login (asumiendo Main es el punto de inicio)
            Intent intent = new Intent(AdminSettingsActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        // 2. Botón Clientes
        btnClients.setOnClickListener(v -> {
            // Ir a la pantalla de registro unificado
            Intent intent = new Intent(AdminSettingsActivity.this, UserRegistrationActivity.class);
            startActivity(intent);
        });
    }
}