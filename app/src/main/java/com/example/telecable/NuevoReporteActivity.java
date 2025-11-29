package com.example.telecable; // Asegúrate de que este sea tu paquete real

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NuevoReporteActivity extends AppCompatActivity {

    private EditText etFecha, etDescripcion;
    private Spinner spinnerFalla;
    private Button btnReportar;
    private final Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_reporte);

        // 1. Referencias de la UI
        etFecha = findViewById(R.id.etFecha);
        etDescripcion = findViewById(R.id.etDescripcion);
        spinnerFalla = findViewById(R.id.spinnerFalla);
        btnReportar = findViewById(R.id.btnReportarFalla);

        // 2. Configurar el Spinner con tipos de fallas
        configureSpinner();

        // 3. Configurar el selector de fecha
        configureDatePicker();

        // 4. Configurar el botón de Reportar
        btnReportar.setOnClickListener(v -> submitReporte());
    }

    private void configureSpinner() {
        String[] fallas = new String[] {
                "Seleccione Tipo de Falla", // Primera opción (placeholder)
                "Fallo de Internet (Velocidad baja/sin conexión)",
                "Fallo de TV (Sin señal/pantalla azul)",
                "Fallo de Cable (Físico o instalación)",
                "No se ven canales específicos"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, fallas);
        spinnerFalla.setAdapter(adapter);
    }

    private void configureDatePicker() {
        // Inicializar con la fecha actual
        updateLabel();

        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, monthOfYear, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        etFecha.setOnClickListener(v -> new DatePickerDialog(
                NuevoReporteActivity.this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show());
    }

    private void updateLabel() {
        String format = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        etFecha.setText(sdf.format(calendar.getTime()));
    }

    private void submitReporte() {
        String tipoFalla = (String) spinnerFalla.getSelectedItem();
        String fecha = etFecha.getText().toString();
        String descripcion = etDescripcion.getText().toString().trim();

        if (tipoFalla.equals("Seleccione Tipo de Falla") || descripcion.isEmpty()) {
            Toast.makeText(this, "Por favor, complete el tipo de falla y la descripción.", Toast.LENGTH_LONG).show();
            return;
        }

        // SIMULACIÓN: Construir el texto del reporte para la lista
        String reporteInfo = tipoFalla.split(" \\(")[0] +
                " - Fecha: " + fecha +
                " (Descr: " + (descripcion.length() > 20 ? descripcion.substring(0, 20) + "..." : descripcion) + ")";

        // Enviar resultado de vuelta a ReporteActivity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("REPORTE_INFO", reporteInfo);
        setResult(RESULT_OK, resultIntent);

        finish(); // Cierra la pantalla de formulario y vuelve a ReporteActivity
    }
}