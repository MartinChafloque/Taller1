package com.example.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnFib;
    EditText txtFib;
    Button btnFact;
    Spinner spinFact;
    TextView txtFibo, txtFac, txtFibDate, txtFacDate;
    String fechaFib = "", fechaFact = "";
    int visitFib = 0, visitFact = 0;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("fibCount", visitFib);
        outState.putInt("facCount", visitFact);
        outState.putString("fibDate", fechaFib);
        outState.putString("facDate", fechaFact);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        visitFib = savedInstanceState.getInt("fibCount");
        visitFact = savedInstanceState.getInt("facCount");
        fechaFib = savedInstanceState.getString("fibDate");
        fechaFact = savedInstanceState.getString("facDate");
        txtFac.setText("Visitas a Fact: " + visitFact);
        txtFibo.setText("Visitas a Fib: " + visitFib);
        txtFibDate.setText("Fecha Fib: " + fechaFib);
        txtFacDate.setText("Fecha Fact: " + fechaFact);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnFib = findViewById(R.id.btnFib);
        this.txtFib = findViewById(R.id.txtPos);
        this.btnFact = findViewById(R.id.btnFact);
        this.spinFact = findViewById(R.id.spinnerFact);
        txtFac = findViewById(R.id.facCount);
        txtFibo = findViewById(R.id.fibCount);
        txtFacDate = findViewById(R.id.facDate);
        txtFibDate = findViewById(R.id.fibDate);
        txtFac.setText("Visitas a Fact: " + visitFact);
        txtFibo.setText("Visitas a Fib: " + visitFib);
        txtFibDate.setText("Fecha Fib: " + fechaFib);
        txtFacDate.setText("Fecha Fact: " + fechaFact);


        this.btnFib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), FibonacciActivity.class);
                if(!txtFib.getText().toString().equals("")){
                    visitFib++;
                    fechaFib = getTime();
                    txtFibo.setText("Visitas a Fib: " + visitFib);
                    txtFibDate.setText("Fecha Fib: " + fechaFib);
                    intent.putExtra("Pos", Integer.parseInt(txtFib.getText().toString()));
                    startActivity(intent);
                }
                else{
                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "Digite un numero", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        this.btnFact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visitFact++;
                txtFac.setText("Visitas a Fact: " + visitFact);
                fechaFact = getTime();
                txtFacDate.setText("Fecha Fact: " + fechaFact);
                Intent intent = new Intent(getBaseContext(), FactorialActivity.class);
                intent.putExtra("Numero", spinFact.getSelectedItem().toString());
                startActivity(intent);
            }
        });

    }

    public String getTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -5);
        return df.format(cal.getTime());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}