package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoEntity {

    private String display_name;
    private String typeFromRequest;
    private String lat;
    private String lon;
    private Geojson geojson;

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getTypeFromRequest() {
        return typeFromRequest;
    }

    public void setTypeFromRequest(String typeFromRequest) {
        this.typeFromRequest = typeFromRequest;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Geojson getGeojson() {
        return geojson;
    }

    public void setGeojson(Geojson geojson) {
        this.geojson = geojson;
    }

    public String printAtBrowser() {
        return "name - \"" + getDisplay_name() + "\"<br>" +
                " type - \"" + getTypeFromRequest() + "\"<br>" +
                " Center coordinates: {" +
                " longitude - \"" + getLon() + "\"" +
                " latitude - \"" + getLat() + "\"}<br>" +
                "geojson type - " + geojson.getType() + "<br>" +
                "coordinates - " + coordinatesToString();
    }

    private String coordinatesToString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object[] array : geojson.getCoordinates()) {
            for (Object element : array)
                stringBuilder.append(element.toString()).append(" ");
            stringBuilder.append("<br>");
        }

        return stringBuilder.toString();
    }
}
