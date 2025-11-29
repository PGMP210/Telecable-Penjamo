package com.example.telecable;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {

    private TextView tvUserType;
    private TextView tvUsername;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // 1. Referencias de la UI
        tvUserType = findViewById(R.id.tv_user_type);
        tvUsername = findViewById(R.id.tv_username);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        // 2. Configuración del Header/Rol
        configureHeader();

        // 3. Configuración del Bottom Navigation
        configureBottomNavigation();
    }

    private void configureHeader() {
        // Recuperar si es admin/técnico de la actividad anterior (LoginActivity)
        boolean isAdmin = getIntent().getBooleanExtra("IS_ADMIN", false);
        // NOTA: El nombre de usuario debería obtenerse aquí de la base de datos o el intento de login.
        String username = "Pablo Minguela";

        tvUsername.setText(username);

        if (isAdmin) {
            tvUserType.setText("Técnico/Administrativo");
        } else {
            tvUserType.setText("Cliente Estándar");
        }
    }

    private void configureBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                // Ya estamos en Inicio
                return true;
            } else if (itemId == R.id.nav_report) {
                // *** ACCIÓN: Iniciar ReporteActivity ***
                Intent intent = new Intent(MenuActivity.this, ReporteActivity.class);
                startActivity(intent);

                // IMPORTANTE: Retornar true indica que el evento fue consumido y la vista se selecciona
                return true;
            } else if (itemId == R.id.nav_settings) {
                Intent intent = new Intent(MenuActivity.this, AdminSettingsActivity.class);
                startActivity(intent);
                return true;
            }
            return false;
        });

        // Asegurarse de que "Inicio" esté seleccionado al cargar la pantalla
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
    }
}