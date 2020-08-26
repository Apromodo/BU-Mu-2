package com.example.odev2kayaunal21796263;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.odev2kayaunal21796263.Mesaj;
import com.example.odev2kayaunal21796263.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String kullaniciAdi="Mace Windu";

    private EditText metinET;
    private Button metinGonderButon;
    private ListView listView;

    private ArrayAdapter<Mesaj> mesajAdaptor;
    private ArrayList<Mesaj> mesajList;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mesajlar;
    private ChildEventListener mesajlarDinleyici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        metinET=findViewById(R.id.metinET);
        metinGonderButon=findViewById(R.id.metinGonderButon);
        listView=findViewById(R.id.listView);

        mesajList=new ArrayList<>();

        mesajAdaptor=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,mesajList);
        listView.setAdapter(mesajAdaptor);

        metinGonderButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String metin=metinET.getText().toString();
                Mesaj mesaj = new Mesaj(kullaniciAdi, metin);
                metinET.setText("");
                mesajlar.push().setValue(mesaj);
            }
        });
        metinET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()<1){
                    metinGonderButon.setEnabled(false);
                }
                else {
                    metinGonderButon.setEnabled(true);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        firebaseDatabase=FirebaseDatabase.getInstance();
        mesajlar=firebaseDatabase.getReference("mesajlar");
        mesajlarDinleyici=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //Veritabanına yeni bir bilgi düştüğü zaman ne yapması gerektiğini söyleyeceğiz.
                Mesaj mesaj=dataSnapshot.getValue(Mesaj.class);
                mesajAdaptor.add(mesaj);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mesajlar.addChildEventListener(mesajlarDinleyici);

    }


}