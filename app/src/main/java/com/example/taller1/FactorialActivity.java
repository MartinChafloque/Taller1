package com.example.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FactorialActivity extends AppCompatActivity {

    private LinearLayout linFact;
    private TextView operacion;
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factorial);

        linFact = findViewById(R.id.layoutFact);
        operacion = findViewById(R.id.operacion);
        resultado = findViewById(R.id.resultado);

        factorial();
    }

    public void factorial(){
        int fact = Integer.parseInt(getIntent().getStringExtra("Numero"));
        String titulo = "Operación: ";
        int result = 1;
        for(int i = 2; i <= fact; i++){
            result = result * i;
        }

        for(int i = 1; i < fact; i++){
            titulo = titulo + (i + "*");
        }
        titulo += ("" + fact);
        operacion.setText(titulo);
        resultado.setText("Resultado: " + result);
    }
}