package com.example.demo.entity;

public class Geojson {

    private String type;
    private Object[][] coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Object[][] coordinates) {
        this.coordinates = coordinates;
    }
}
