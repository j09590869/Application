package com.example.application;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_resultado extends AppCompatActivity {

    TextView tvDatos;
    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        tvDatos = findViewById(R.id.tvDatos);
        btnRegresar = findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Vuelve a la actividad anterior
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nombre = extras.getString("nombre", "");
            String apellido = extras.getString("apellido", "");
            String tipoDocumento = extras.getString("tipoDocumento", "");
            String numero = extras.getString("numero", "");
            String fechaNacimiento = extras.getString("fechaNacimiento", "");

            String texto = "<b>Nombre:</b> " + nombre + "<br>" +
                    "<b>Apellido:</b> " + apellido + "<br>" +
                    "<b>Fecha de Nacimiento:</b> " + fechaNacimiento + "<br>" +
                    "<b>Tipo de Documento:</b> " + tipoDocumento + "<br>" +
                    "<b>Número:</b> " + numero + "<br>" ;


            tvDatos.setText(Html.fromHtml(texto, Html.FROM_HTML_MODE_LEGACY));
        }
    }
}
