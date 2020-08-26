package com.example.odev3kayaunal;

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

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    private String kullaniciAdi="kaya";
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference konumlar;
    private ChildEventListener konumlarDinleyici;
    private SupportMapFragment haritaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        haritaFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.haritaFragment);





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
                         long tarih=System.currentTimeMillis();

                        Kullanici kullanici=new Kullanici(kullaniciAdi,enlem,boylam,tarih);
                        konumlar.push().setValue(kullanici);

                    }

                }
            });

        }

        firebaseDatabase=firebaseDatabase.getInstance();
        konumlar=firebaseDatabase.getReference("konumlar");
        konumlarDinleyici= new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Kullanici kullanici=dataSnapshot.getValue(Kullanici.class);
                haritadaGoster(kullanici);

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

    private void haritadaGoster(final Kullanici kullanici) {

        haritaFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                LatLng enlemBoylam=new LatLng(kullanici.getEnlem(),kullanici.getBoylam());
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(enlemBoylam,12));

                MarkerOptions isaretleyiciSecenekleri=new MarkerOptions();
                SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.YYYY");
                String tarih=sdf.format(kullanici.getTarih());
                isaretleyiciSecenekleri.title("Kullanıcı: "+kullanici.getKullaniciAdi());
                isaretleyiciSecenekleri.position(enlemBoylam);

                googleMap.addMarker(isaretleyiciSecenekleri).setSnippet("Tarih:"+tarih);


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