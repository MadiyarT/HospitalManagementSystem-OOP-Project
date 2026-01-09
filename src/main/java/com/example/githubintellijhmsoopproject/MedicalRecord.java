package com.example.githubintellijhmsoopproject;

import java.util.ArrayList;
import java.util.List;

class MedicalRecord {
    private Patient patient;
    private List<String> diagnoses = new ArrayList<>();
    private List<Medicine> medicines = new ArrayList<>();

    public MedicalRecord(Patient patient) {
        this.patient = patient;
    }

    public void addDiagnosis(String diagnosis) {
        diagnoses.add(diagnosis);
    }

    public void prescribeMedicine(Medicine medicine) {
        medicines.add(medicine);
    }

    public void showRecord() {
        System.out.println("Medical Record for " + patient.getName());
        System.out.println("Diagnoses: " + diagnoses);
        System.out.println("Medicines: " + medicines);
    }
}