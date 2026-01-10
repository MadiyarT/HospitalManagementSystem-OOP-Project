package com.example.githubintellijhmsoopproject;

public class Patient extends Person {

    private String illness;
    private MedicalRecord record;


    public Patient(int id, String name, int age, String illness) {
        super(id, name, age);
        setIllness(illness);
        this.record = new MedicalRecord(this);
    }

    public void setIllness(String illness) {
        if (illness == null || illness.isEmpty())
            throw new IllegalArgumentException("Invalid illness");
        this.illness = illness;
    }

    public String getIllness() { return illness; }
    public MedicalRecord getRecord() { return record; }


    @Override
    public void work() {
        System.out.println("Patient is receiving treatment");
    }

    @Override
    public String toString() {
        return "Patient -> " + super.toString() + ", Illness=" + illness;
    }
}
