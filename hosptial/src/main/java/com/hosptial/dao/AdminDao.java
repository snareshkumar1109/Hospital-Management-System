package com.hosptial.dao;

import com.hosptial.connection.DBConnection;
import com.hosptial.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    public List<Count> ViewAllDoctor() {
        String query = "SELECT name, id FROM User WHERE role = 'Doctor'";
        String updateStatusQuery = "UPDATE appointment SET status = 'reject' WHERE Date < CURRENT_DATE()";
        
        List<Count> doctors = new ArrayList<>();
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement updateStmt = con.prepareStatement(updateStatusQuery);
             PreparedStatement statement = con.prepareStatement(query)) {

            // Update outdated appointments
            updateStmt.executeUpdate();

            // Fetch doctor list
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Count doctor = new Count();
                doctor.setId(rs.getInt("id"));
                doctor.setDname(rs.getString("name"));

                // Count different appointment statuses
                doctor.setBookCount(getStatusCount(con, doctor.getDname(), "booked"));
                doctor.setPendingCount(getStatusCount(con, doctor.getDname(), "pending"));
                doctor.setRejectCount(getStatusCount(con, doctor.getDname(), "reject"));

                doctors.add(doctor);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    private int getStatusCount(Connection con, String doctorName, String status) throws SQLException {
        String countQuery = "SELECT count(*) FROM appointment WHERE Dname = ? AND status = ?";
        try (PreparedStatement ps = con.prepareStatement(countQuery)) {
            ps.setString(1, doctorName);
            ps.setString(2, status);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    public boolean AddDoctor(String name, String email, String password, String specification) {
        String insertQuery = "INSERT INTO User (name, email, password, role, specification) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(insertQuery)) {

            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, "Doctor");
            statement.setString(5, specification);

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean DeleteDoctor(int id, String name) {
        String deleteQuery = "DELETE FROM User WHERE id = ? AND name = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement statement = con.prepareStatement(deleteQuery)) {

            statement.setInt(1, id);
            statement.setString(2, name);

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Appointment> ViewAllAppointment() {
        List<Appointment> appointments = new ArrayList<>();
        String updateStatusQuery = "UPDATE appointment SET status = 'reject' WHERE Date < CURRENT_DATE()";
        String selectQuery = "SELECT * FROM appointment";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement updateStmt = con.prepareStatement(updateStatusQuery);
             PreparedStatement ps = con.prepareStatement(selectQuery)) {

            // Update outdated appointments
            updateStmt.executeUpdate();

            // Fetch all appointments
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setDId(rs.getInt("DId"));
                appointment.setDname(rs.getString("Dname"));
                appointment.setEmail(rs.getString("email"));
                appointment.setId(rs.getInt("Id"));
                appointment.setName(rs.getString("name"));
                appointment.setDate(rs.getDate("Date"));
                appointment.setStatus(rs.getString("status"));
                appointment.setTime(rs.getTime("Time"));

                appointments.add(appointment);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }
}
