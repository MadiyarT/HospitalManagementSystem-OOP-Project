package com.example.githubintellijhmsoopproject;

public class Doctor extends Person {

    private String specialization;
    private Department department;


    public Doctor(int id, String name, int age, String specialization, Department department) {
        super(id, name, age);
        setSpecialization(specialization);
        setDepartment(department);
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.isEmpty())
            throw new IllegalArgumentException("Invalid specialization");
        this.specialization = specialization;
    }

    public void setDepartment(Department department) {
        if (department == null)
            throw new IllegalArgumentException("Invalid department");
        this.department = department;
    }

    public String getSpecialization() { return specialization; }
    public Department getDepartment() { return department; }


    @Override
    public void work() {
        super.work();
        System.out.println("Doctor is treating patients");
    }

    @Override
    public String toString() {
        return "Doctor -> " + super.toString() + ", Specialization=" + specialization;
    }
}