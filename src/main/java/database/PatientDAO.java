package database;

import model.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientDAO {

    public void insertPatient(Patient patient) {
        String sql =
                "INSERT INTO patient (name, age, illness) VALUES (?, ?, ?)";

        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement =
                    connection.prepareStatement(sql);

            statement.setString(1, patient.getName());
            statement.setInt(2, patient.getAge());
            statement.setString(3, patient.getIllness());

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Patient inserted successfully!");
            }

            statement.close();
        } catch (SQLException e) {
            System.out.println("Insert failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }
}
