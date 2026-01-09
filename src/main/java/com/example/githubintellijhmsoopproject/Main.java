package com.example.githubintellijhmsoopproject;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Department cardiology = new Department("Cardiology");
        Department neurology = new Department("Neurology");

        Doctor d1 = new Doctor(1, "Dr. Smith", "Cardiologist", cardiology);
        Doctor d2 = new Doctor(2, "Dr. Brown", "Neurologist", neurology);

        Patient p1 = new Patient(101, "John Doe", 30, "Male");
        Patient p2 = new Patient(102, "Alice Green", 25, "Female");

        Appointment a1 = new Appointment(1, p1, d1, new Date());
        Appointment a2 = new Appointment(2, p2, d2, new Date());

        p1.getMedicalRecord().addDiagnosis("Hypertension");
        p1.getMedicalRecord().prescribeMedicine(new Medicine("Amlodipine", "5mg daily"));

        System.out.println(a1);
        System.out.println(a2);

        p1.getMedicalRecord().showRecord();
    }
}

