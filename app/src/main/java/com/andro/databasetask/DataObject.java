package com.andro.databasetask;

import java.io.Serializable;

public class DataObject  implements Serializable {
    int id;
    String name;
    String city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DataObject(String name, String city) {
        this.name = name;
        this.city = city;
    }
    public DataObject() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
