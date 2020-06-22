package com.example.odev3kayaunal;

public class Kullanici {

    private String kullaniciAdi;
    private double enlem;
    private double boylam;
    private long tarih;

    public Kullanici() {
    }

    public Kullanici(String kullaniciAdi, double enlem, double boylam, long tarih) {
        this.kullaniciAdi = kullaniciAdi;
        this.enlem = enlem;
        this.boylam = boylam;
        this.tarih = tarih;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public double getEnlem() {
        return enlem;
    }

    public void setEnlem(double enlem) {
        this.enlem = enlem;
    }

    public double getBoylam() {
        return boylam;
    }

    public void setBoylam(double boylam) {
        this.boylam = boylam;
    }

    public long getTarih() {
        return tarih;
    }

    public void setTarih(long tarih) {
        this.tarih = tarih;
    }
}