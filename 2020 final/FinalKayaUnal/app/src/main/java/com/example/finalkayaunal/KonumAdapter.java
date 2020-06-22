package com.example.finalkayaunal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.BreakIterator;
import java.util.List;

public class KonumAdapter extends ArrayAdapter<Konum> {


    public KonumAdapter(Context context, int resource, List<Konum> objects) {
        super(context, resource, objects);

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_main, parent, false);

        TextView yer = convertView.findViewById(R.id.haritaFragment);
        TextView sicaklik = convertView.findViewById(R.id.haritaFragment);

        Konum konum = getItem(position);

        BreakIterator enlemBoylam = null;
        enlemBoylam.setText(String.valueOf(konum.getEnlem() + konum.getBoylam()));
        sicaklik.setText((int) konum.getSicak());


        return convertView;

    }
}