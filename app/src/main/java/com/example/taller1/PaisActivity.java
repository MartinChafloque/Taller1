package com.example.taller1;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PaisActivity extends AppCompatActivity  {

        private ListView mListView;
        private List<String> mLista = new ArrayList<>();
        private List<Pais> mPais = new ArrayList<>();
        private ArrayAdapter<String> mAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_pais);


            // leer
            String jsonFileContent = null;
            try {

                jsonFileContent = leerJson(this,"paises.json");
                JSONArray jsonArray = new JSONArray(jsonFileContent);

                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String nombre = jsonObject.getString("Name");

                    Pais paisInfo = new Pais();
                    paisInfo.setName(jsonObject.getString("Name"));
                    paisInfo.setAlpha2code(jsonObject.getString("Alpha2Code"));
                    paisInfo.setAlpah3code(jsonObject.getString("Alpha3Code"));
                    paisInfo.setNativename(jsonObject.getString("NativeName"));
                    paisInfo.setRegion(jsonObject.getString("Region"));
                    paisInfo.setSubregion(jsonObject.getString("SubRegion"));
                    paisInfo.setLatitude(jsonObject.getString("Latitude"));
                    paisInfo.setLongitude(jsonObject.getString("Longitude"));
                    paisInfo.setArea(jsonObject.getInt("Area"));
                    paisInfo.setNumericCode(jsonObject.getInt("NumericCode"));
                    paisInfo.setNativeLanguage(jsonObject.getString("NativeLanguage"));
                    paisInfo.setCurrencyCode(jsonObject.getString("CurrencyCode"));
                    paisInfo.setCurrencyName(jsonObject.getString("CurrencyName"));
                    paisInfo.setCurrencySymbol(jsonObject.getString("CurrencySymbol"));
                    paisInfo.setFlag(jsonObject.getString("Flag"));
                    paisInfo.setFlagPng(jsonObject.getString("FlagPng"));


                    mLista.add(nombre);
                    System.out.println(paisInfo.toString());
                    mPais.add(paisInfo);
                }

            }

            catch(IOException | JSONException e){
                e.printStackTrace();
            }


            mListView = findViewById(R.id.listaP);
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,mLista);
            mListView.setAdapter(mAdapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent,View view,int position,long id)
                {
                        Intent intent = new Intent(PaisActivity.this,PaisinfoActivity.class);
                        Pais pasar = mPais.get(position);
                        intent.putExtra("Pais",pasar);
                        Log.d("Nombre",pasar.getName());
                        startActivity(intent);


                }
            });

        }




        public static String leerJson(Context context, String fileName) throws IOException
        {
           BufferedReader reader = null;
            reader = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName),"UTF-8"));

            String content ="";
            String line;
            while((line=reader.readLine())!=null)
            {
                content = content + line;
            }

            return content;
        }


}
