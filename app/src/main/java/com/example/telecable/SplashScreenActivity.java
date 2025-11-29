package com.example.telecable; // ¡Asegúrate de que este sea tu paquete!

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SplashScreenActivity extends AppCompatActivity {

    private TextView tvSplashMessage;
    private TextView tvSplashRoleHint;
    private Button btnContinueToMenu;
    private boolean isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // 1. Obtener la referencia de los elementos de la UI
        tvSplashMessage = findViewById(R.id.tv_splash_message);
        tvSplashRoleHint = findViewById(R.id.tv_splash_role_hint);
        btnContinueToMenu = findViewById(R.id.btn_continue_to_menu);

        // 2. Obtener el rol (isAdmin) desde LoginActivity
        isAdmin = getIntent().getBooleanExtra("IS_ADMIN", false);
        String username = "Pablo Minguela"; // Mantener el nombre usado en MenuActivity

        // 3. Personalizar el mensaje de bienvenida
        configureWelcomeMessage(username);

        // 4. Configurar el botón para continuar
        btnContinueToMenu.setOnClickListener(v -> continueToMenu());
    }

    /**
     * Configura el texto de bienvenida basado en el rol del usuario.
     * @param username El nombre del usuario logeado.
     */
    private void configureWelcomeMessage(String username) {
        if (isAdmin) {
            tvSplashMessage.setText("¡Hola, " + username + "!");
            tvSplashRoleHint.setText("Tu acceso ha sido confirmado como Técnico/Administrativo. Tienes acceso a funciones avanzadas.");
            Toast.makeText(this, "Bienvenido al modo avanzado.", Toast.LENGTH_LONG).show();
        } else {
            tvSplashMessage.setText("¡Bienvenido/a, " + username + "!");
            tvSplashRoleHint.setText("Tu acceso ha sido confirmado como Cliente Estándar.");
        }
    }

    /**
     * Navega a MenuActivity, pasando el rol (isAdmin) nuevamente.
     */
    private void continueToMenu() {
        Intent intent = new Intent(SplashScreenActivity.this, MenuActivity.class);
        // Es CRÍTICO pasar el rol (isAdmin) al MenuActivity para que se configure correctamente el header
        intent.putExtra("IS_ADMIN", isAdmin);
        startActivity(intent);
        finish(); // Opcional: Cierra esta actividad para que el usuario no pueda volver con el botón de atrás
    }
}