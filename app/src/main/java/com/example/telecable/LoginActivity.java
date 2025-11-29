package com.example.telecable;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    // Variables para los elementos de la interfaz
    private EditText etUsuario, etPassword, etContrato, etCodigoEspecial;
    private CheckBox cbAdminTecnico;
    private Button btnIngresar;
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar la base de datos
        dbHelper = new DataBaseHelper(this);

        // Enlazar componentes con el XML
        etUsuario = findViewById(R.id.et_usuario);
        etPassword = findViewById(R.id.et_password);
        etContrato = findViewById(R.id.et_contrato);
        etCodigoEspecial = findViewById(R.id.et_codigo_especial);
        cbAdminTecnico = findViewById(R.id.cb_admin_tecnico);
        btnIngresar = findViewById(R.id.btn_ingresar);

        // Lógica del Checkbox: Ocultar Contrato / Mostrar Código Especial
        cbAdminTecnico.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // MODO TÉCNICO: Ocultar contrato, mostrar código especial
                    etContrato.setVisibility(View.GONE);
                    etCodigoEspecial.setVisibility(View.VISIBLE);
                } else {
                    // MODO CLIENTE: Mostrar contrato, ocultar código especial
                    etContrato.setVisibility(View.VISIBLE);
                    etCodigoEspecial.setVisibility(View.GONE);
                }
            }
        });

        // Lógica del Botón Ingresar
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etUsuario.getText().toString().trim();
                String pass = etPassword.getText().toString().trim();

                if (usuario.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Por favor llene usuario y contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean loginExitoso = false;

                if (cbAdminTecnico.isChecked()) {
                    // Validación TÉCNICO
                    String codigo = etCodigoEspecial.getText().toString().trim();
                    if (codigo.isEmpty()) {
                        etCodigoEspecial.setError("Código requerido");
                        return;
                    }
                    loginExitoso = dbHelper.validarTecnico(usuario, pass, codigo);
                } else {
                    // Validación CLIENTE
                    String contrato = etContrato.getText().toString().trim();
                    if (contrato.isEmpty()) {
                        etContrato.setError("Contrato requerido");
                        return;
                    }
                    loginExitoso = dbHelper.validarCliente(usuario, pass, contrato);
                }

                if (loginExitoso) {
                    Toast.makeText(LoginActivity.this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();

                    // Ir a la siguiente pantalla (MainActivity)
                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}