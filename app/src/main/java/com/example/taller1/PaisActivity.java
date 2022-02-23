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
import java.util.ArrayList;
import java.util.List;

public class PaisActivity extends AppCompatActivity  {

        private ListView mListView;
        private List<String> mLista = new ArrayList<>();
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

                    Log.d("Nombre: ",nombre);
                    mLista.add(nombre);
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

                        startActivity(new Intent(PaisActivity.this, PaisinfoActivity.class));


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
