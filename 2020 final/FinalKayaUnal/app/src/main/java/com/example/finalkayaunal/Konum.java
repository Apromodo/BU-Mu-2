package com.example.finalkayaunal;

public class Konum {

    private String yer;
    private double enlem;
    private double boylam;
    private long sicak;

    public Konum() {
    }

    public Konum(String yer, double enlem, double boylam,  long sicak) {
        this.yer = yer;
        this.enlem = enlem;
        this.boylam = boylam;
        this.sicak = sicak;
    }

    public String getYer() {
        return yer;
    }

    public void setYer(String yer) {
        this.yer = yer;
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

    public long getSicak() { return sicak; }

    public void setSicak(long sicak) { this.sicak = sicak; }
}
