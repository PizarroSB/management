package com.event.management.domain.models;


import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private String id;
    private String name;
    private List<EventModel> events = new ArrayList<>();

    public UserModel(String id, String name, List<EventModel> events) {
        this.id = id;
        this.name = name;
        this.events = events;
    }

    public UserModel() {
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

    public List<EventModel> getEvents() {
        return events;
    }

    public void setEvents(List<EventModel> events) {
        this.events = events;
    }
}
