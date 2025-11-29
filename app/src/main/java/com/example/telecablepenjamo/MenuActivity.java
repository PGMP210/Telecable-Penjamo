package com.example.telecablepenjamo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.telecablepenjamo.MainActivity;

public class MenuActivity extends AppCompatActivity {

    private TextView tvNombreUsuario;
    private TextView tvTipoUsuario;
    private Button btnPlan80;
    private Button btnPlan150;
    private Button btnPlan200;
    private LinearLayout btnNavInicio;
    private LinearLayout btnNavReporte;
    private LinearLayout btnNavAjuste;

    private String usuario;
    private boolean esTecnico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Obtener datos del Intent
        usuario = getIntent().getStringExtra("usuario");
        esTecnico = getIntent().getBooleanExtra("esTecnico", false);

        // Inicializar componentes
        tvNombreUsuario = findViewById(R.id.tvNombreUsuario);
        tvTipoUsuario = findViewById(R.id.tvTipoUsuario);
        btnPlan80 = findViewById(R.id.btnPlan80);
        btnPlan150 = findViewById(R.id.btnPlan150);
        btnPlan200 = findViewById(R.id.btnPlan200);
        btnNavInicio = findViewById(R.id.btnNavInicio);
        btnNavReporte = findViewById(R.id.btnNavReporte);
        btnNavAjuste = findViewById(R.id.btnNavAjuste);

        // Configurar datos del usuario
        if (usuario != null && !usuario.isEmpty()) {
            tvNombreUsuario.setText(usuario);
        }

        if (esTecnico) {
            tvTipoUsuario.setText("Técnico");
        } else {
            tvTipoUsuario.setText("Usuario");
        }

        // Mostrar mensaje de bienvenida
        Toast.makeText(this, "Bienvenido al menú principal, " + usuario,
                Toast.LENGTH_SHORT).show();

        // Configurar botones de planes
        btnPlan80.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoPlan("80 Mbps", "$120");
            }
        });

        btnPlan150.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoPlan("150 Mbps", "$350");
            }
        });

        btnPlan200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoPlan("200 Mbps", "$449");
            }
        });

        // Configurar navegación inferior
        btnNavInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuActivity.this, "Ya estás en Inicio",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnNavReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MenuActivity.this, "Abriendo Reportes...",
                        Toast.LENGTH_SHORT).show();
                // Aquí puedes agregar la navegación a la pantalla de reportes
            }
        });

        btnNavAjuste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoAjustes();
            }
        });

        // Manejar el botón back usando OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Mostrar diálogo en lugar de regresar
                mostrarDialogoCerrarSesion();
            }
        });
    }

    private void mostrarDialogoPlan(String plan, String precio) {
        new AlertDialog.Builder(this)
                .setTitle("Contratar Plan")
                .setMessage("¿Desea contratar el plan de " + plan + " por " + precio + " al mes?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    Toast.makeText(MenuActivity.this,
                            "Plan de " + plan + " contratado exitosamente",
                            Toast.LENGTH_LONG).show();
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void mostrarDialogoAjustes() {
        CharSequence[] opciones = {"Mi Perfil", "Notificaciones", "Cerrar Sesión"};

        new AlertDialog.Builder(this)
                .setTitle("Ajustes")
                .setItems(opciones, (dialog, which) -> {
                    switch (which) {
                        case 0: // Mi Perfil
                            Toast.makeText(MenuActivity.this, "Abriendo perfil...",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case 1: // Notificaciones
                            Toast.makeText(MenuActivity.this, "Configuración de notificaciones",
                                    Toast.LENGTH_SHORT).show();
                            break;
                        case 2: // Cerrar Sesión
                            mostrarDialogoCerrarSesion();
                            break;
                    }
                })
                .show();
    }

    private void mostrarDialogoCerrarSesion() {
        new AlertDialog.Builder(this)
                .setTitle("Cerrar Sesión")
                .setMessage("¿Está seguro que desea cerrar sesión?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    // Cerrar todas las actividades y volver a MainActivity
                    Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

                    Toast.makeText(MenuActivity.this, "Sesión cerrada",
                            Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .show();
    }
}