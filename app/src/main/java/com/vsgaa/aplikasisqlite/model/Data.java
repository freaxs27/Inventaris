package com.vsgaa.aplikasisqlite.model;

public class Data {
    private String id, name, harga, jumlah;

    public Data(String id, String name, String harga, String jumlah) {
        this.id = id;
        this.name = name;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHarga() {
        return harga;
    }

    public String getJumlah() {
        return jumlah;
    }
}
