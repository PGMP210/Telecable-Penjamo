package com.example.telecable;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Asegúrate de que el nombre del layout coincida con el archivo XML
        setContentView(R.layout.activity_main);

        // Referencias a los botones
        Button btnInicio = findViewById(R.id.btn_inicio);


        // Configurar el click del botón INICIO (va a LoginActivity)
        btnInicio.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

    }
}