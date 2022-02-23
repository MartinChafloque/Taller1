package com.example.taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class PaisinfoActivity extends AppCompatActivity {

    TextView nombrePais;
    TextView alpha2code;
    TextView alpha3code;
    TextView nativename;
    TextView region;
    TextView subregion;
    TextView latitude;
    TextView longitude;
    TextView area;
    TextView numericcode;
    TextView nativelanguague;
    TextView currencycode;
    TextView currencyname;
    TextView currencysymbol;
    ImageView imageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paisinfo);

        Pais info = (Pais) getIntent().getSerializableExtra("Pais");

        nombrePais = findViewById(R.id.nombrepais);
        alpha2code = findViewById(R.id.alpha2code);
        alpha3code = findViewById(R.id.alpha3code);
        nativename = findViewById(R.id.nativename);
        region = findViewById(R.id.region);
        subregion = findViewById(R.id.subregion);
        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longitude);
        area = findViewById(R.id.area);
        numericcode = findViewById(R.id.numericcode);
        nativelanguague = findViewById(R.id.nativelanguague);
        currencycode= findViewById(R.id.currencycode);
        currencyname = findViewById(R.id.currencyname);
        currencysymbol=findViewById(R.id.currencysymbol);
        imageView2 = findViewById(R.id.imageView2);

        nombrePais.setText(info.getName());
        alpha2code.setText(info.getAlpha2code());
        alpha3code.setText(info.getAlpah3code());
        nativename.setText(info.getNativename());
        region.setText(info.getRegion());
        subregion.setText(info.getSubregion());
        latitude.setText(info.getLatitude());
        longitude.setText(info.getLongitude());
        area.setText(String.valueOf(info.getArea()));
        numericcode.setText(String.valueOf(info.getNumericCode()));
        nativelanguague.setText(info.getNativeLanguage());
        currencycode.setText(info.getCurrencyCode());
        currencyname.setText(info.getCurrencyName());
        currencysymbol.setText(info.getCurrencySymbol());

        String url = info.getFlagPng();
        new DownloadImageTask((ImageView) findViewById(R.id.imageView2))
                .execute(url);


    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}