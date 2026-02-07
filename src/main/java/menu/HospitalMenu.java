package menu;

import model.*;
import database.*;

import exception.InvalidInputException;

import java.util.Scanner;
import java.util.List;

public class HospitalMenu implements Menu {

    private Scanner scanner;
    private DoctorDAO doctorDAO;
    private PatientDAO patientDAO;

    public HospitalMenu() {
        this.scanner = new Scanner(System.in);
        this.doctorDAO = new DoctorDAO();

        System.out.println("\nHi");
    }

    @Override
    public void displayMenu() {
        System.out.println("\nHere should be menu display");
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("\nEnter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addPatient();
                        break;
                    case 2:
                        addDoctor();
                        break;
                    case 3:
                        viewAllDoctors();
                        break;
                    case 4:
                        updateDoctor();
                        break;
                    case 5:
                        deleteDoctor();
                        break;
                    case 6:
                        makeEveryoneWork();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Thank you for using our system!");
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice! Please select 0-6.");
                }

                if (choice != 0) {
                    pressEnterToContinue();
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Please enter a valid number!");
                scanner.nextLine();
                pressEnterToContinue();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
                pressEnterToContinue();
            }
        }

        scanner.close();
    }



    private void addPatient() {
        System.out.print("ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt(); scanner.nextLine();
        System.out.print("Illness: ");
        String illness = scanner.nextLine();

        Patient patient = new Patient(id, name, age, illness);
        patientDAO.insertPatient(patient);
    }

    private void addDoctor() {
        System.out.print("ID: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt(); scanner.nextLine();
        System.out.print("Specialization: ");
        String specialization = scanner.nextLine();
        System.out.println("Department: ");
        String department = scanner.nextLine();

        Doctor doctor = new Doctor(id, name, age, specialization, department);
        doctorDAO.insertDoctor(doctor);
    }

    private void updateDoctor() {
        System.out.println("\nUPDATE Doctor");
        System.out.print("Enter Doctor ID to update: ");

        try {
            int doctorid = scanner.nextInt();
            scanner.nextLine();

            Doctor existingDoctor = doctorDAO.getDoctorById(doctorid);

            if (existingDoctor == null) {
                System.out.println("No doctor found with ID: " + doctorid);
                return;
            }

            System.out.println("Current Info:");
            System.out.println(existingDoctor.toString());

            System.out.println("\nENTER NEW VALUES");
            System.out.println("(Press Enter to keep current value)");

            System.out.print("New Name [" + existingDoctor.getName() + "]: ");
            String newName = scanner.nextLine();
            if (newName.trim().isEmpty()) {
                newName = existingDoctor.getName();
            }

            System.out.print("New Age [" + existingDoctor.getAge() + "]: ");
            String input = scanner.nextLine();

            int newAge;
            if (input.trim().isEmpty()) {
                newAge = existingDoctor.getAge();
            } else {
                newAge = Integer.parseInt(input);
            }

            if (existingDoctor instanceof Doctor) {
                Doctor doctor = (Doctor) existingDoctor;
                System.out.print("New Specialization [" + doctor.getSpecialization() + "]: ");
                String newSpec = scanner.nextLine();
                if (newSpec.trim().isEmpty()) {
                    newSpec = doctor.getSpecialization();
                }

                System.out.print("New Department [" + doctor.getDepartment() + "]: ");
                String newDept = scanner.nextLine();
                if (newDept.trim().isEmpty()) {
                    newDept = doctor.getSpecialization();
                }


                Doctor updatedDoctor = new Doctor(doctorid, newName, newAge, newSpec, newDept);
                doctorDAO.updateDoctor(updatedDoctor);
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format!");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        }
    }

    private void deleteDoctor() {
        System.out.println("\nDELETE Doctor");
        System.out.print("Enter Doctor ID to delete: ");

        try {
            int doctorid = scanner.nextInt();
            scanner.nextLine();

            Doctor doctor = doctorDAO.getDoctorById(doctorid);

            if (doctor == null) {
                System.out.println("No doctor found with ID: " + doctorid);
                return;
            }

            System.out.println("Doctor to delete:");
            System.out.println(doctor.toString());
            System.out.print("Are you sure? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                doctorDAO.deleteDoctor(doctorid);
            } else {
                System.out.println("Deletion cancelled.");
            }

        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: Invalid input!");
            scanner.nextLine();
        }

    }

    private void searchByName() {
        System.out.println("\nSEARCH BY NAME");
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();

        List<Doctor> results = doctorDAO.searchByName(name);

        displaySearchResults(results, "Search: '" + name + "'");
    }

    private void displaySearchResults(List<Doctor> results, String criteria) {
        System.out.println("SEARCH RESULTS");
        System.out.println("Criteria: " + criteria);

        if (results.isEmpty()) {
            System.out.println("No doctor found matching criteria.");
        } else {
            for (int i = 0; i < results.size(); i++) {
                Doctor d = results.get(i);
                System.out.print((i + 1) + ". ");
                System.out.println(d.toString());
            }
            System.out.println("Total Results: " + results.size());
        }
    }


    private void viewAllDoctors() {
        doctorDAO.displayAllDoctors();
    }

    private void makeEveryoneWork() {}

    private void pressEnterToContinue() {
        System.out.println("\n[Press Enter to continue...]");
        scanner.nextLine();
    }
}