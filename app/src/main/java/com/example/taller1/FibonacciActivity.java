package com.example.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FibonacciActivity extends AppCompatActivity {

    private ImageButton imgFib;
    private LinearLayout linFib;
    private WebView linkWiki;
    private ArrayList<Integer> secuencia = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fibonacci);

        imgFib = findViewById(R.id.imgFib);
        linFib = findViewById(R.id.layoutFib);
        linkWiki = (WebView) findViewById(R.id.webFib);

        imgFib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linkWiki.setWebViewClient(new WebViewClient());
                linkWiki.loadUrl("https://es.wikipedia.org/wiki/Sucesi%C3%B3n_de_Fibonacci");
            }
        });
        algorithmFib();
    }

    public void algorithmFib(){
        int pos = getIntent().getIntExtra("Pos", 0);

        secuencia.add(0);
        secuencia.add(1);

        for(int i = 1; i < pos; i++){
            int a = secuencia.get(i - 1);
            int b = secuencia.get(i);
            secuencia.add(a + b);
        }

        for(Integer i: secuencia){
            TextView t = new TextView(this);
            t.setText("" + i);
            t.setId(i);
            t.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ((LinearLayout) linFib).addView(t);
        }
    }
}