package com.example.githubintellijhmsoopproject;

import java.util.Date;

class Appointment {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private Date date;

    public Appointment(int id, Patient patient, Doctor doctor, Date date) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Appointment{id=" + id + ", patient=" + patient.getName() +
                ", doctor=" + doctor.getName() + ", date=" + date + "}";
    }
}