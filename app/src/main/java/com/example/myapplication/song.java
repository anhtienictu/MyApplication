package com.example.myapplication;

public class song {
    private String tbh;
    private int file;

    public song(String tbh, int file) {
        this.tbh = tbh;
        this.file = file;
    }

    public String getTbh() {
        return tbh;
    }

    public void setTbh(String tbh) {
        this.tbh = tbh;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }
}
