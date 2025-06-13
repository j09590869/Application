package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerDia, spinnerMes, spinnerAnio;
    AutoCompleteTextView autoCompleteTextViewDocumentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinnerDia = findViewById(R.id.spinnerDia);
        spinnerMes = findViewById(R.id.spinnerMes);
        spinnerAnio = findViewById(R.id.spinnerAnio);

        List<String> dias = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            dias.add(String.valueOf(i));
        }
        ArrayAdapter<String> diaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dias);
        diaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDia.setAdapter(diaAdapter);

        String[] meses = {
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        ArrayAdapter<String> mesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, meses);
        mesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMes.setAdapter(mesAdapter);

        List<String> anios = new ArrayList<>();
        for (int i = 2025; i >= 1950; i--) {
            anios.add(String.valueOf(i));
        }
        ArrayAdapter<String> anioAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, anios);
        anioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnio.setAdapter(anioAdapter);

        autoCompleteTextViewDocumentType = findViewById(R.id.autoCompleteTextViewDocumentType);
        String[] documentTypes = new String[]{"Pasaporte", "Cédula", "Licencia de Conducir"};
        ArrayAdapter<String> documentTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, documentTypes);
        autoCompleteTextViewDocumentType.setAdapter(documentTypeAdapter);
        autoCompleteTextViewDocumentType.setOnClickListener(v -> autoCompleteTextViewDocumentType.showDropDown());

        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextApellido = findViewById(R.id.editTextApellido);
        EditText editTextNumber = findViewById(R.id.editTextNumber);
        Button buttonClear = findViewById(R.id.buttonClear);
        Button buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonClear.setOnClickListener(v -> {
            editTextName.setText("");
            editTextApellido.setText("");
            editTextNumber.setText("");
            autoCompleteTextViewDocumentType.setText("");
            spinnerDia.setSelection(0);
            spinnerMes.setSelection(0);
            spinnerAnio.setSelection(0);
        });

        buttonSubmit.setOnClickListener(v -> {
            String nombre = editTextName.getText().toString().trim();
            String apellido = editTextApellido.getText().toString().trim();
            String tipoDocumento = autoCompleteTextViewDocumentType.getText().toString();
            String numero = editTextNumber.getText().toString();
            String dia = spinnerDia.getSelectedItem().toString();
            String mes = spinnerMes.getSelectedItem().toString();
            String anio = spinnerAnio.getSelectedItem().toString();

            if (nombre.isEmpty() || apellido.isEmpty() || tipoDocumento.isEmpty() || numero.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, activity_resultado.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("apellido", apellido);
                intent.putExtra("fechaNacimiento", dia + " de " + mes + " de " + anio);
                intent.putExtra("tipoDocumento", tipoDocumento);
                intent.putExtra("numero", numero);

                startActivity(intent);
            }
        });
    }
}
