package com.example.githubintellijhmsoopproject;

class Doctor {
    private int id;
    private String name;
    private String specialization;
    private Department department;

    public Doctor(int id, String name, String specialization, Department department) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.department = department;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Doctor{id=" + id + ", name='" + name + "', specialization='" + specialization + "', department=" + department.getName() + "}";
    }
}