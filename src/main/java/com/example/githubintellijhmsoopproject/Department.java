package com.example.githubintellijhmsoopproject;

public class Department {

    private int id;
    private String name;

    public Department(int id, String name) {
        if (id <= 0) throw new IllegalArgumentException("Invalid department ID");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Invalid name");
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Department{id=" + id + ", name='" + name + "'}";
    }
}
