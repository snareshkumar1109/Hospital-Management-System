package com.hosptial.dao;

import com.hosptial.model.*;
import com.hosptial.connection.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDao {

    public List<User> AllDoctor(String specification) {
        String query = "SELECT * FROM User WHERE specification = ? AND Role = 'Doctor'";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, specification);
            ResultSet rs = statement.executeQuery();

            List<User> doctors = new ArrayList<>();
            while (rs.next()) {
                User doctor = new User();
                doctor.setId(rs.getInt("Id"));
                doctor.setName(rs.getString("name"));
                doctor.setEmail(rs.getString("email"));
                doctor.setPassword(rs.getString("Password"));
                doctor.setRole(rs.getString("Role"));
                doctor.setSpecification(rs.getString("specification"));
                doctors.add(doctor);
            }
            return doctors;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getDoctorById(int id) {
        String query = "SELECT * FROM User WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                User doctor = new User();
                doctor.setId(id);
                doctor.setName(rs.getString("name"));
                doctor.setEmail(rs.getString("email"));
                doctor.setPassword(rs.getString("password"));
                doctor.setRole(rs.getString("role"));
                doctor.setSpecification(rs.getString("specification"));
                return doctor;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> AllSpecification() {
        String query = "SELECT DISTINCT specification FROM User WHERE role = 'Doctor'";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {

            List<String> specifications = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                specifications.add(rs.getString("specification"));
            }
            return specifications;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Time> getAllAvailableTimes(int doctorId, String doctorName, Date appointmentDate) {
        List<Time> availableTimes = new ArrayList<>(List.of(
                Time.valueOf("09:00:00"), Time.valueOf("10:00:00"), Time.valueOf("11:00:00"),
                Time.valueOf("12:00:00"), Time.valueOf("13:00:00"), Time.valueOf("14:00:00"),
                Time.valueOf("15:00:00"), Time.valueOf("16:00:00"), Time.valueOf("17:00:00")
        ));

        String updateStatusQuery = "UPDATE Appointment SET status = 'reject' WHERE Date < CURRENT_DATE()";
        String selectTimeQuery = "SELECT Time FROM Appointment WHERE DId = ? AND Dname = ? AND Date = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement updateStatusStmt = con.prepareStatement(updateStatusQuery);
             PreparedStatement selectTimeStmt = con.prepareStatement(selectTimeQuery)) {

            updateStatusStmt.executeUpdate();  // Update outdated appointments

            selectTimeStmt.setInt(1, doctorId);
            selectTimeStmt.setString(2, doctorName);
            selectTimeStmt.setDate(3, appointmentDate);

            ResultSet rs = selectTimeStmt.executeQuery();
            while (rs.next()) {
                availableTimes.remove(rs.getTime("Time"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return availableTimes;
    }

    public boolean BookingAppointment(String name, String email, String Dname, int DId, Date Date, Time Time, String status) {
        String query = "INSERT INTO Appointment(name, email, Dname, DId, Date, Time, status) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {

            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, Dname);
            statement.setInt(4, DId);
            statement.setDate(5, Date);
            statement.setTime(6, Time);
            statement.setString(7, status);

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Appointment> ViewAppointmentPatient(String name) {
        List<Appointment> appointments = new ArrayList<>();
        String updateStatusQuery = "UPDATE Appointment SET status = 'reject' WHERE Date < CURRENT_DATE()";
        String selectQuery = "SELECT * FROM Appointment WHERE name = ? AND status = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement updateStmt = con.prepareStatement(updateStatusQuery)) {
            
            updateStmt.executeUpdate();  // Update outdated appointments

            // Queries for each status
            String[] statuses = {"Pending", "booked", "reject"};
            for (String status : statuses) {
                try (PreparedStatement selectStmt = con.prepareStatement(selectQuery)) {
                    selectStmt.setString(1, name);
                    selectStmt.setString(2, status);

                    ResultSet rs = selectStmt.executeQuery();
                    while (rs.next()) {
                        Appointment appointment = new Appointment();
                        appointment.setDId(rs.getInt("DId"));
                        appointment.setName(name);
                        appointment.setEmail(rs.getString("email"));
                        appointment.setId(rs.getInt("Id"));
                        appointment.setDname(rs.getString("Dname"));
                        appointment.setDate(rs.getDate("Date"));
                        appointment.setStatus(rs.getString("status"));
                        appointment.setTime(rs.getTime("Time"));
                        appointments.add(appointment);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }
}
