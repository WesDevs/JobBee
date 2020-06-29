package com.wesdev.JobBee.models;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Company {

    private SimpleStringProperty name;
    private SimpleStringProperty note;
    private SimpleBooleanProperty active;

    public Company() {
        this.name = new SimpleStringProperty();
        this.note = new SimpleStringProperty();
        this.active = new SimpleBooleanProperty();
    }

    public Company(String name) {
        this.name.set(name);
    }

    public Company(String name, String note, String url, Boolean active) {
        this.name.set(name);
        this.note.set(note);
        this.active.set(active);
    }

    public SimpleStringProperty nameVal() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getName() {
        return this.name.get();
    }

    public SimpleStringProperty noteVal() {
        return note;
    }

    public void setNote(String note) {
        this.note.set(note);
    }

    public String getNote() {
        return this.note.get();
    }

    public SimpleBooleanProperty activeVal() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active.set(active);
    }

    public Boolean isActive() {
        return this.active.get();
    }
}
