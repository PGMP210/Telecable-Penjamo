package com.example.telecablepenjamo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public class VienvenidaActivity extends AppCompatActivity {

    private TextView tvInfoVivienda;
    private Button btnContinuarVivienda;
    private String usuario;
    private String contrato;
    private boolean esTecnico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vienvenida);

        // Obtener datos del Intent
        usuario = getIntent().getStringExtra("usuario");
        contrato = getIntent().getStringExtra("contrato");
        esTecnico = getIntent().getBooleanExtra("esTecnico", false);

        // Inicializar componentes
        tvInfoVivienda = findViewById(R.id.tvInfoVivienda);
        btnContinuarVivienda = findViewById(R.id.btnContinuarVivienda);

        // Mostrar información personalizada
        if (esTecnico) {
            tvInfoVivienda.setText("Bienvenido Técnico: " + usuario +
                    "\n\nTienes acceso completo al sistema de gestión de viviendas.");
        } else {
            tvInfoVivienda.setText("Bienvenido: " + usuario +
                    "\nContrato: " + contrato +
                    "\n\nAquí podrás gestionar toda la información relacionada con tu vivienda.");
        }

        // Configurar botón continuar
        btnContinuarVivienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir al menú principal
                Intent intent = new Intent(VienvenidaActivity.this, MenuActivity.class);
                intent.putExtra("usuario", usuario);
                intent.putExtra("esTecnico", esTecnico);
                startActivity(intent);
            }
        });

        // Manejar el botón back usando OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Evitar que el usuario regrese al login
                moveTaskToBack(true);
            }
        });
    }
}