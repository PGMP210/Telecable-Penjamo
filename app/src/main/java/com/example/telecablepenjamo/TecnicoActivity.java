package com.example.telecablepenjamo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class TecnicoActivity extends AppCompatActivity {

    private TextInputEditText etCodigoTecnico;
    private Button btnVerificarCodigo;
    private String usuario;

    // Código de técnico de ejemplo
    private static final String CODIGO_CORRECTO = "1234";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnico);

        // Obtener datos del Intent
        usuario = getIntent().getStringExtra("usuario");

        // Inicializar componentes
        etCodigoTecnico = findViewById(R.id.etCodigoTecnico);
        btnVerificarCodigo = findViewById(R.id.btnVerificarCodigo);

        // Configurar evento de click
        btnVerificarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarCodigo();
            }
        });

        // Manejar el botón back usando OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        });
    }

    private void verificarCodigo() {
        String codigo = etCodigoTecnico.getText().toString().trim();

        if (codigo.isEmpty()) {
            etCodigoTecnico.setError("Ingrese el código de técnico");
            etCodigoTecnico.requestFocus();
            return;
        }

        // Verificar el código
        if (codigo.equals(CODIGO_CORRECTO)) {
            Toast.makeText(this, "Código correcto. Acceso de técnico autorizado",
                    Toast.LENGTH_SHORT).show();

            // Ir a la pantalla de vivienda con permisos de técnico
            Intent intent = new Intent(TecnicoActivity.this, VienvenidaActivity.class);
            intent.putExtra("usuario", usuario);
            intent.putExtra("esTecnico", true);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Código incorrecto. Intente nuevamente",
                    Toast.LENGTH_SHORT).show();
            etCodigoTecnico.setText("");
            etCodigoTecnico.requestFocus();
        }
    }
}