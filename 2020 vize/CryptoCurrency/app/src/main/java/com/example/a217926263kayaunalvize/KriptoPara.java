package com.example.a217926263kayaunalvize;

public class KriptoPara {


    private String _symbol;
    private String _name;
    private String _price;
    private Integer _numberOfMarkets;
    private Integer _numberOfExchanges;


    public KriptoPara(String _symbol, String _name, String _price, Integer _numberOfMarkets, Integer _numberOfExchanges) {
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

    public Integer get_numberOfMarkets() {
        return _numberOfMarkets;
    }

    public Integer get_numberOfExchanges() {
        return _numberOfExchanges;
    }


    //------------------------------------------------------------------------



    public void set_symbol(String _symbol) {
        this._symbol = _symbol;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_price(String _price) {
        this._price = _price;
    }

    public void set_numberOfMarkets(Integer _numberOfMarkets) {
        this._numberOfMarkets = _numberOfMarkets;
    }

    public void set_numberOfExchanges(Integer _numberOfExchanges) {
        this._numberOfExchanges = _numberOfExchanges;
    }
}
