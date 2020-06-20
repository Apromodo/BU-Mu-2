package com.example.odev2kayaunal21796263;

import androidx.annotation.NonNull;

public class Mesaj {

    private String kullaniciAdi;
    private String metin;

    public Mesaj() {    }

    public Mesaj(String kullaniciAdi, String metin) {
        this.kullaniciAdi = kullaniciAdi;
        this.metin = metin;
    }

    @NonNull
    @Override
    public String toString() {
        return kullaniciAdi+"\n"+metin;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public String getMetin() {
        return metin;
    }

}