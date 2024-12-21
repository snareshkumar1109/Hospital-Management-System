package com.hosptial.dao;

import com.hosptial.model.*;
import com.hosptial.connection.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDao {

    public List<Appointment> ViewAppointment(String name, int id) {
        List<Appointment> appointments = new ArrayList<>();
        String updateStatusQuery = "UPDATE appointment SET status = 'reject' WHERE Date < CURRENT_DATE()";
        String selectQuery = "SELECT * FROM appointment WHERE Dname = ? AND DId = ? AND status = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement updateStmt = con.prepareStatement(updateStatusQuery)) {

            // Update outdated appointments
            updateStmt.executeUpdate();

            // Fetch appointments for each status
            String[] statuses = {"Pending", "booked", "reject"};
            for (String status : statuses) {
                try (PreparedStatement selectStmt = con.prepareStatement(selectQuery)) {
                    selectStmt.setString(1, name);
                    selectStmt.setInt(2, id);
                    selectStmt.setString(3, status);

                    ResultSet rs = selectStmt.executeQuery();
                    while (rs.next()) {
                        Appointment appointment = new Appointment();
                        appointment.setDId(id);
                        appointment.setDname(name);
                        appointment.setEmail(rs.getString("email"));
                        appointment.setId(rs.getInt("Id"));
                        appointment.setName(rs.getString("name"));
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

    public boolean updateStatus(String status, int id) {
        String query = "UPDATE appointment SET status = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, status);
            ps.setInt(2, id);

            return ps.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Appointment> ViewPendingAppointment(String name, int id) {
        List<Appointment> appointments = new ArrayList<>();
        String updateStatusQuery = "UPDATE appointment SET status = 'reject' WHERE Date < CURRENT_DATE()";
        String selectPendingQuery = "SELECT * FROM appointment WHERE Dname = ? AND DId = ? AND status = 'Pending'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement updateStmt = con.prepareStatement(updateStatusQuery);
             PreparedStatement selectStmt = con.prepareStatement(selectPendingQuery)) {

            // Update outdated appointments
            updateStmt.executeUpdate();

            // Fetch pending appointments
            selectStmt.setString(1, name);
            selectStmt.setInt(2, id);

            ResultSet rs = selectStmt.executeQuery();
            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setDId(id);
                appointment.setDname(name);
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
