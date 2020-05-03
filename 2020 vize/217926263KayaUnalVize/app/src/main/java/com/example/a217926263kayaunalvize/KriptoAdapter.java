package com.example.a217926263kayaunalvize;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class KriptoAdapter extends ArrayAdapter<KriptoPara> {

    public KriptoAdapter(Context context, int resource, int textViewResourceId, List<KriptoPara> objects) {
        super(context, resource, textViewResourceId, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.satirkalibi,parent,false);

        TextView symbolSatirKalibi = convertView.findViewById(R.id.symbolSatirKalibi);
        TextView nameSatirKalibi = convertView.findViewById(R.id.nameSatirKalibi);
        TextView priceSatirKalibi = convertView.findViewById(R.id.priceSatirKalibi);
        TextView numberOfMarketsSatirKalibi = convertView.findViewById(R.id.numberOfMarketsSatirKalibi);
        TextView numberOfExchangesSatirKalibi = convertView.findViewById(R.id._numberOfExchangesSatirKalibi);

        KriptoPara kriptoPara = getItem(position);

        symbolSatirKalibi.setText(kriptoPara.get_symbol());
        nameSatirKalibi.setText(kriptoPara.get_name());
        priceSatirKalibi.setText(kriptoPara.get_price());
        numberOfMarketsSatirKalibi.setText(kriptoPara.get_numberOfMarkets());
        numberOfExchangesSatirKalibi.setText(kriptoPara.get_numberOfExchanges());




        return convertView;




    }
}
