package com.example.telecable;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class ReporteActivity extends AppCompatActivity {

    private ListView listViewReportes;
    private Button btnNuevoReporte;
    private ArrayList<String> reportesList; // Lista para simular los reportes

    // Clave para identificar el request code al volver de la pantalla de nuevo reporte
    private static final int NUEVO_REPORTE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);

        listViewReportes = findViewById(R.id.listViewReportes);
        btnNuevoReporte = findViewById(R.id.btnNuevoReporte);

        // 1. Inicializar y cargar la lista simulada
        reportesList = new ArrayList<>();
        reportesList.add("Falla #001: Internet Lento (2024-10-25)");
        reportesList.add("Falla #002: TV sin señal (2024-10-26)");

        loadReportes();

        // 2. Configurar botón para ir a la pantalla de Nuevo Reporte
        btnNuevoReporte.setOnClickListener(v -> {
            Intent intent = new Intent(ReporteActivity.this, NuevoReporteActivity.class);
            startActivityForResult(intent, NUEVO_REPORTE_REQUEST); // Usamos forResult para capturar el resultado
        });
    }

    private void loadReportes() {
        // Adaptador simple para mostrar la lista
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1, // Layout predeterminado para items de lista
                reportesList
        );
        listViewReportes.setAdapter(adapter);

        // Listener para ver detalles del reporte (opcional)
        listViewReportes.setOnItemClickListener((parent, view, position, id) -> {
            String reporteSeleccionado = reportesList.get(position);
            Toast.makeText(ReporteActivity.this, "Detalles: " + reporteSeleccionado, Toast.LENGTH_LONG).show();
        });
    }

    // 3. Método para manejar la respuesta al volver de NuevoReporteActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NUEVO_REPORTE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Se ha reportado una nueva falla y se ha enviado la información
            String nuevoReporteInfo = data.getStringExtra("REPORTE_INFO");
            if (nuevoReporteInfo != null) {
                // Añadir el nuevo reporte a la lista y recargarla
                reportesList.add(0, nuevoReporteInfo); // Añadir al inicio para que se vea primero
                loadReportes();
                Toast.makeText(this, "Falla reportada con éxito.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}