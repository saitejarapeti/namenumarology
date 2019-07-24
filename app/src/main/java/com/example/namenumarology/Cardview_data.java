package com.example.namenumarology;

public class Cardview_data {
    private String num;
    private  String name;
    private String numroname;
    private String text_message;

    public Cardview_data(String num, String name, String numroname, String text_message) {
        this.num = num;
        this.name = name;
        this.numroname = numroname;
        this.text_message = text_message;
    }

    public String getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public String getNumroname() {
        return numroname;
    }

    public String getText_message() {
        return text_message;
    }
}
