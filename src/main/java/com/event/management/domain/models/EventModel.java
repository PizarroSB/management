package com.event.management.domain.models;

public class EventModel {
    private String id;
    private String name;
    private String date;
    private String location;

    public EventModel() {
    }

    public EventModel(String id, String name, String date, String location) {

        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
