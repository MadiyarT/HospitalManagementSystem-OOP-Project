package model;

import java.util.Date;

public class Appointment {
    private Patient patient;
    private Doctor doctor;
    private Date date;

    public Appointment(Patient patient, Doctor doctor, Date date) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Appointment{patient=" + patient.getName() +
                ", doctor=" + doctor.getName() + ", date=" + date + "}";
    }
}