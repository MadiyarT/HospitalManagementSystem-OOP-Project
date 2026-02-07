package model;

public class Doctor extends Person {

    private String specialization;
    private String department;


    public Doctor(int id, String name, int age, String specialization, String department) {
        super(id, name, age);
        setSpecialization(specialization);
        setDepartment(department);
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.isEmpty())
            throw new IllegalArgumentException("Invalid specialization");
        this.specialization = specialization;
    }

    public void setDepartment(String department) {
        if (department == null)
            throw new IllegalArgumentException("Invalid department");
        this.department = department;
    }

    public String getSpecialization() { return specialization; }
    public String getDepartment() { return department; }


    @Override
    public void work() {
        System.out.println("Doctor is treating patients");
    }

    @Override
    public String toString() {
        return "Doctor -> " + super.toString() + ", Specialization=" + specialization;
    }
}