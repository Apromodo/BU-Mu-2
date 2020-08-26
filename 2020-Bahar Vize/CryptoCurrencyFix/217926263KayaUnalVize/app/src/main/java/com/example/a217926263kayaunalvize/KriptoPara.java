package com.example.a217926263kayaunalvize;

public class KriptoPara {


    private String _symbol;
    private String _name;
    private String _price;
    private String _numberOfMarkets;
    private String _numberOfExchanges;


    public KriptoPara(String _symbol, String _name, String _price,  String _numberOfMarkets, String _numberOfExchanges) {
        this._symbol = _symbol;
        this._name = _name;
        this._price = _price;
        this._numberOfMarkets = _numberOfMarkets;
        this._numberOfExchanges = _numberOfExchanges;
    }

    public String get_symbol() {
        return _symbol;
    }

    public String get_name() {
        return _name;
    }

    public String get_price() {
        return _price;
    }

    public String get_numberOfMarkets() {
        return _numberOfMarkets;
    }

    public String get_numberOfExchanges() {
        return _numberOfExchanges;
    }


    //------------------------------------------------------------------------




}
