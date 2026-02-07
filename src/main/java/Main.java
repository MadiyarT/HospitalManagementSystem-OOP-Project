import database.DoctorDAO;
import model.Doctor;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        DoctorDAO doctorDAO = new DoctorDAO();
        List<Doctor> doctorList = doctorDAO.getAllDoctors();
        for(Doctor doctor : doctorList){
            System.out.println(doctor);
        }
    }

}
