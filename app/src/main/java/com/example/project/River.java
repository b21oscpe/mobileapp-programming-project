package com.example.project;

public class River {

    String ID;
    String name;
    String location;
    Integer size;
    String auxdata;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getAuxdata() {
        return auxdata;
    }

    public void setAuxdata(String auxdata) {
        this.auxdata = auxdata;
    }

    public River(String ID, String name, String location, Integer size, String auxdata){
        this.ID = ID;
        this.name = name;
        this.location = location;
        this.size = size;
        this.auxdata = auxdata;
    }
}
