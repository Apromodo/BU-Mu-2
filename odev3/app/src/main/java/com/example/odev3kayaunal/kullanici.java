package com.example.odev3kayaunal;

public class kullanici {

    private String kullaniciAdi;
    private double enlem;
    private double boylam;
    private long terih;

    public kullanici() {
    }

    public kullanici(String kullaniciAdi, double enlem, double boylam, long terih) {
        this.kullaniciAdi = kullaniciAdi;
        this.enlem = enlem;
        this.boylam = boylam;
        this.terih = terih;
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

    public long getTerih() {
        return terih;
    }

    public void setTerih(long terih) {
        this.terih = terih;
    }
}