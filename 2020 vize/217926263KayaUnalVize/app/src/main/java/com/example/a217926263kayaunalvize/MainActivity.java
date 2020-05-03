package com.example.a217926263kayaunalvize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    private Context context;
    private ListView listView;
    private ArrayList<KriptoPara> cyrptoList ;
    private KriptoAdapter adaptorum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        cyrptoList = new ArrayList<KriptoPara>();
        kriptoPara();
    }


    public void kriptoPara(){

        String url = "https://api.coinranking.com/v1/public/coins";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {

                JSONObject root = null;
                try {
                    root = new JSONObject(response);
                    JSONObject val1 = root.getJSONObject("data");

                    JSONArray data = val1.getJSONArray("coins");

                    for(int i=0; i<data.length();i++){
                        JSONObject iter = data.getJSONObject(i);

                        String symbol = iter.getString("symbol");
                        String name = iter.getString("name");
                        String price = iter.getString("price");
                        Integer numberOfMarkets = iter.getInt("numberOfMarkets");
                        Integer numberOfExchanges = iter.getInt("numberOfExchanges");

                        Log.e("Name", name + symbol);
                        Log.e("Price", name + price);
                        Log.e("NumberOfMarkets", String.valueOf(numberOfMarkets));
                        Log.e("NumberOfExchanges", String.valueOf(numberOfExchanges));

                        cyrptoList .add(new KriptoPara(symbol, name, price, numberOfMarkets, numberOfExchanges));



                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(request);
        adaptorum= new KriptoAdapter(this, R.layout.satirkalibi,cyrptoList);
        listView.setAdapter(adaptorum);
    }
}
