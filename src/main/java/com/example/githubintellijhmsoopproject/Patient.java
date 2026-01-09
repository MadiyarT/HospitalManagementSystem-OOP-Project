package com.example.githubintellijhmsoopproject;

class Patient {
    private int id;
    private String name;
    private int age;
    private String gender;
    private MedicalRecord medicalRecord;

    public Patient(int id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.medicalRecord = new MedicalRecord(this);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public MedicalRecord getMedicalRecord() { return medicalRecord; }

    @Override
    public String toString() {
        return "Patient{id=" + id + ", name='" + name + "', age=" + age + ", gender=" + gender + "}";
    }
}