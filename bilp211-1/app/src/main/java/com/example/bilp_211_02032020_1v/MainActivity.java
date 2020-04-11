package com.example.bilp_211_02032020_1v;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    private ArrayList<String> ilListesi;
    private ListView listView;
    private ArrayAdapter<String> adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

        String jsonCevabi = dosyadanOku("Turkiye_Il_Ilce.json");
        ilListesi = jsonAyika(jsonCevabi);


        adaptor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ilListesi);
        listView.setAdapter(adaptor);

    }

    private ArrayList<String> jsonAyika(String jsonCevabi) {
        ArrayList<String> liste = new ArrayList<String>();
        JSONArray kokDizi = null;
        try {
            kokDizi = new JSONArray(jsonCevabi);

            for (int i = 0; i < kokDizi.length(); i++) {
                JSONObject sehirJO = kokDizi.getJSONObject(i);
                String ilAdi = sehirJO.getString("il");
                ilListesi.add(ilAdi);

            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }


        return liste;

    }

    private String dosyadanOku(String dosyaAdi) {
        //try'dan sonra normal parantez() açıp kapatıp, satırı bu parantezler arasına taşıdık
        StringBuilder sb = new StringBuilder();
        try (InputStream is = getAssets().open(dosyaAdi);
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr);
        ) {
            String satir = br.readLine();//İLK SATIRI OKU
            while (satir != null) {//SON OKUNAN SATIR NULL OLMADIĞI SÜRECE;sb'ye ekle ve bir daha oku
                sb.append(satir);
                satir = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();//okuduğumuz satırları içinde biriktirdiğimiz sb'yi döndür
    }

}