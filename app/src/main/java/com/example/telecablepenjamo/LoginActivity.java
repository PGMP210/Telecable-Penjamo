package com.example.telecablepenjamo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText etUsuario;
    private TextInputEditText etContrasena;
    private TextInputEditText etContrato;
    private CheckBox cbSoloTecnico;
    private Button btnIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar componentes
        etUsuario = findViewById(R.id.etUsuario);
        etContrasena = findViewById(R.id.etContrasena);
        etContrato = findViewById(R.id.etContrato);
        cbSoloTecnico = findViewById(R.id.cbSoloTecnico);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);

        // Configurar evento de click del botón
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarLogin();
            }
        });
    }

    private void validarLogin() {
        String usuario = etUsuario.getText().toString().trim();
        String contrasena = etContrasena.getText().toString().trim();
        String contrato = etContrato.getText().toString().trim();

        // Validar que los campos no estén vacíos
        if (usuario.isEmpty()) {
            etUsuario.setError("Ingrese usuario");
            etUsuario.requestFocus();
            return;
        }

        if (contrasena.isEmpty()) {
            etContrasena.setError("Ingrese contraseña");
            etContrasena.requestFocus();
            return;
        }

        if (contrato.isEmpty()) {
            etContrato.setError("Ingrese número de contrato");
            etContrato.requestFocus();
            return;
        }

        // Verificar si es técnico
        if (cbSoloTecnico.isChecked()) {
            // Ir a pantalla de código de técnico
            Intent intent = new Intent(LoginActivity.this, TecnicoActivity.class);
            intent.putExtra("usuario", usuario);
            startActivity(intent);
        } else {
            // Ir directamente a pantalla de vivienda
            Intent intent = new Intent(LoginActivity.this, VienvenidaActivity.class);
            intent.putExtra("usuario", usuario);
            intent.putExtra("contrato", contrato);
            startActivity(intent);

            Toast.makeText(this, "Bienvenido " + usuario, Toast.LENGTH_SHORT).show();
        }
    }
}