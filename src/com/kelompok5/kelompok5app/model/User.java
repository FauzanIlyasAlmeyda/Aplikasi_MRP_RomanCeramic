package com.kelompok5.kelompok5app.model;

public class User {
    private String username;
    private String divisi;

    public User(String username, String divisi) {
        this.username = username;
        this.divisi = divisi;
    }

    public String getUsername() {
        return username;
    }

    public String getDivisi() {
        return divisi;
    }
}
