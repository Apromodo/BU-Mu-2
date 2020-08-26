package com.example.bilp211_21796263;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Throwable e;
    private ArrayList<String> uzakliklar;
    private ListView listView;
    private ArrayAdapter<String> adaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner1 = findViewById(R.id.spiiner1);
        spinner2 = findViewById(R.id.spiiner2);
        textView = findViewById(R.id.textView1);

        String jsonCevabi = dosyadanOku("uzakliklar.json");
        uzakliklar = jsonAyika(jsonCevabi);


        adaptor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, uzakliklar);
        spinner1.setAdapter(adaptor);
        spinner2.setAdapter(adaptor);

    }

    private ArrayList<String> jsonAyika(String jsonCevabi) {
        ArrayList<String> liste = new ArrayList<String>();
        JSONArray kokDizi = null;
        try {
            kokDizi = new JSONArray(jsonCevabi);

            for (int i = 0; i < kokDizi.length(); i++) {
                JSONObject sehirJO = kokDizi.getJSONObject(i);
                String ilAdi = sehirJO.getString("diğer il");


            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }


        return liste;

    }

    private String dosyadanOku(String dosyaAdi) {

        StringBuilder sb=new StringBuilder();
        try (InputStream is = getAssets().open(dosyaAdi);
             InputStreamReader isr=new InputStreamReader(is);
             BufferedReader br=new BufferedReader(isr);
        ) {
            String satir=br.readLine();
            while(satir!=null) {
                sb.append(satir);
                satir = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
