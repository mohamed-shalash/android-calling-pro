package com.example.caller3;

public class you_colled {
    int id;
    String phone;
    String when;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public you_colled(String phone, String when, String name) {
        this.phone = phone;
        this.when = when;
        this.name = name;
    }

    public you_colled(int id, String phone, String when, String name) {
        this.id = id;
        this.phone = phone;
        this.when = when;
        this.name = name;
    }
}
