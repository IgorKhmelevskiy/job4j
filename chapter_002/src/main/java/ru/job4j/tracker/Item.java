package ru.job4j.tracker;

public class Item {
    private String id;
    public String name;
    public String description;

    public Item() {
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void out() {
        System.out.println("Имя: " + this.name + ", Описание: " + this.description + ", ID: " + this.getId());
    }
}
