package database;

import model.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public boolean insertDoctor(Doctor doctor) {
        String sql = "INSERT INTO doctor (name, age, specialization, department) + VALUES (?, ?, ?, ?)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, doctor.getName());
            statement.setInt(2, doctor.getAge());
            statement.setString(3, doctor.getSpecialization());
            statement.setString(4, doctor.getDepartment());

            int rowsInserted = statement.executeUpdate();
            statement.close();

            if (rowsInserted > 0) {
                System.out.println("Doctor inserted: " + doctor.getName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Insert failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public List<Doctor> getAllDoctors() {
        List<Doctor> doctorlist = new ArrayList<>();
        String sql = "SELECT * FROM doctor ORDER BY id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return doctorlist;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Doctor doctor = extractDoctorFromResultSet(resultSet);
                if (doctor != null) {
                    doctorlist.add(doctor);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("Retrieved " + doctorlist.size() + " doctors from database");

        } catch (SQLException e) {
            System.out.println("Select all doctors failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return doctorlist;
    }

    public Doctor getDoctorById(int doctorid) {
        String sql = "SELECT * FROM doctor WHERE id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, doctorid);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Doctor doctor = extractDoctorFromResultSet(resultSet);

                resultSet.close();
                statement.close();

                if (doctor != null) {
                    System.out.println("Found doctor with ID: " + doctorid);
                }

                return doctor;
            }

            System.out.println("No doctor found with ID: " + doctorid);

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("Select by ID failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return null;
    }

    public boolean updateDoctor(Doctor doctor) {
        String sql = "UPDATE doctor SET name = ?, specialization = ?, department = ? " + "WHERE id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getSpecialization());
            statement.setInt(3, doctor.getId());
            statement.setString(4, doctor.getDepartment());

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            if (rowsUpdated > 0) {
                System.out.println("Doctor updated: " + doctor.getName());
                return true;
            } else {
                System.out.println("No doctor found with ID: " + doctor.getId());
            }

        } catch (SQLException e) {
            System.out.println("Update Doctor failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public boolean deleteDoctor(int doctorid) {
        String sql = "DELETE FROM doctor WHERE id = ?";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, doctorid);

            int rowsDeleted = statement.executeUpdate();
            statement.close();

            if (rowsDeleted > 0) {
                System.out.println("Doctor deleted (ID: " + doctorid + ")");
                return true;
            } else {
                System.out.println("No doctor found with ID: " + doctorid);
            }

        } catch (SQLException e) {
            System.out.println("Delete failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public List<Doctor> searchByName(String name) {
        List<Doctor> doctorList = new ArrayList<>();
        String sql = "SELECT * FROM doctor WHERE name ILIKE ? ORDER BY name";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return doctorList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Doctor doctor = extractDoctorFromResultSet(resultSet);
                if (doctor != null) {
                    doctorList.add(doctor);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("Found " + doctorList.size() + " doctor matching '" + name + "'");

        } catch (SQLException e) {
            System.out.println("Search by name failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return doctorList;
    }

    private Doctor extractDoctorFromResultSet(ResultSet resultSet) throws SQLException {
        int doctorid = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int age = resultSet.getInt("age");
        String department = resultSet.getString("department");

        Doctor doctor = null;

        String specialization = resultSet.getString("specialization");
        doctor = new Doctor(doctorid, name, age, specialization, department);


        return doctor;
    }

    public void displayAllDoctors() {
        List<Doctor> doctorList = getAllDoctors();

        System.out.println("ALL STAFF FROM DATABASE");

        if (doctorList.isEmpty()) {
            System.out.println("No staff members in database.");
        } else {
            for (int i = 0; i < doctorList.size(); i++) {
                Doctor d = doctorList.get(i);
                System.out.print((i + 1) + ". ");
                System.out.println(d.toString());
            }
        }
    }

    public void makeEveryoneWork() {
        List<Doctor> doctorList = getAllDoctors();

        System.out.println("  POLYMORPHISM: Doctor from Database");

        if (doctorList.isEmpty()) {
            System.out.println("No doctor to demonstrate.");
        } else {
            for (Doctor d : doctorList) {
                d.work();
            }
        }
    }

}
