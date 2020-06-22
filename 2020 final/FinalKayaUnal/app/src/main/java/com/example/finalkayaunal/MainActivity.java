package com.example.finalkayaunal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    //private String yer ="";
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference konumlar;
    private ChildEventListener konumlarDinleyici;
    private SupportMapFragment haritaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        haritaFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.haritaFragment);
        konumSicak();




        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
            izinleriKontrolEtYoksaIste();


        if(izinVarMi()) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
            fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location!=null) {



                        double enlem=location.getLatitude();
                        double boylam=location.getLongitude();
                        long sicak= (long) 0.0;
                        String yer= "";

                        Konum konum =new Konum(yer,enlem,boylam,sicak);
                        konumlar.push().setValue(konum);

                    }

                }
            });

        }

        firebaseDatabase=firebaseDatabase.getInstance();
        konumlar=firebaseDatabase.getReference("konumlar");
        konumlarDinleyici= new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Konum konum =dataSnapshot.getValue(Konum.class);
                haritadaGoster(konum);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        };
        konumlar.addChildEventListener(konumlarDinleyici);
    }



    public void konumSicak(){

        String url = "http://api.openweathermap.org/data/2.5/find?lat=39.5&lon=32.5&units=metric&appid=f24e77f45826cf7aae0d8a25f51d425f";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {

                JSONObject root = null;
                try {
                    root = new JSONObject(response);
                    JSONArray test1 = root.getJSONArray("list");

                    for(int i=0; i<test1.length();i++) {
                        JSONObject iter = test1.getJSONObject(i);
                        String name = iter.getString("name");

                        Log.e("Name", name);
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
    }

    private void haritadaGoster(final Konum konum) {

        haritaFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                LatLng enlemBoylam=new LatLng(konum.getEnlem(), konum.getBoylam());
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(enlemBoylam,10));

                MarkerOptions isaretleyiciSecenekleri=new MarkerOptions();
                isaretleyiciSecenekleri.title("Yer: "+ konum.getYer());
                isaretleyiciSecenekleri.position(enlemBoylam);

                googleMap.addMarker(isaretleyiciSecenekleri).setSnippet("Sıcaklık: "+konum.getSicak());


            }
        });

    }

    public void izinleriKontrolEtYoksaIste(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},123);
        }
    }

    public boolean izinVarMi(){
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
}