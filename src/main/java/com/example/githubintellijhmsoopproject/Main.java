package com.example.githubintellijhmsoopproject;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    static ArrayList<Person> people = new ArrayList<>();
    static ArrayList<Appointment> appointments = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Department cardiology = new Department(1, "Cardiology");

        while (true) {
            System.out.println("\n1. Add Patient");
            System.out.println("2. Add Doctor");
            System.out.println("3. Create Appointment");
            System.out.println("4. View Medical Record");
            System.out.println("5. View All People");
            System.out.println("6. Make Everyone Work");
            System.out.println("0. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addPatient();
                case 2 -> addDoctor(cardiology);
                case 3 -> createAppointment();
                case 4 -> viewMedicalRecord();
                case 5 -> viewAll();
                case 6 -> makeEveryoneWork();
                case 0 -> System.exit(0);
            }
        }
    }

    static void addPatient() {
        System.out.print("ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Age: ");
        int age = sc.nextInt(); sc.nextLine();
        System.out.print("Illness: ");
        String illness = sc.nextLine();

        people.add(new Patient(id, name, age, illness));
        System.out.println("Patient added");
    }

    static void addDoctor(Department dept) {
        System.out.print("ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Age: ");
        int age = sc.nextInt(); sc.nextLine();
        System.out.print("Specialization: ");
        String spec = sc.nextLine();

        people.add(new Doctor(id, name, age, spec, dept));
        System.out.println("Doctor added");
    }

    static void createAppointment() {
        Patient p = null;
        Doctor d = null;

        for (Person person : people) {
            if (person instanceof Patient && p == null) p = (Patient) person;
            if (person instanceof Doctor && d == null) d = (Doctor) person;
        }

        if (p != null && d != null) {
            appointments.add(new Appointment(p, d, new Date()));
            p.getRecord().addDiagnosis("Scheduled visit");
            System.out.println("Appointment created");
        } else {
            System.out.println("Need at least one patient and one doctor");
        }
    }

    static void viewMedicalRecord() {
        for (Person p : people) {
            if (p instanceof Patient pt) {
                System.out.println(pt.getRecord());
            }
        }
    }

    static void viewAll() {
        for (Person p : people) {
            System.out.println(p);
        }
    }

    static void makeEveryoneWork() {
        for (Person p : people) {
            p.work();
        }
    }
}
