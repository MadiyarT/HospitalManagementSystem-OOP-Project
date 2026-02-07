package model;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {

    private Patient patient;
    private List<String> diagnoses = new ArrayList<>();

    public MedicalRecord(Patient patient) {
        this.patient = patient;
    }

    public void addDiagnosis(String diagnosis) {
        diagnoses.add(diagnosis);
    }

    public List<String> getDiagnoses() {
        return diagnoses;
    }

    @Override
    public String toString() {
        return "MedicalRecord{patient=" + patient.getName() +
                ", diagnoses=" + diagnoses + "}";
    }
}
