<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hosptial.dao.*" %>
<%@ page import="com.hosptial.model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Appointments</title>
    <link rel="stylesheet" href="css/vaa.css"> <!-- Link to the CSS file -->
</head>
<body>
    <header>
        <nav class="navbar">
            <div class="logo">
                <img src="images.png" alt="Consultation Logo" />
            </div>
           
            <ul class="navbar-links" id="navbar-links">
                <li><a href="admin.jsp">Home</a></li>
                <li><a href="AddDoctor.jsp">Add Doctor</a></li>
                <li><a href="#">View Appointment</a></li>

                <li><a href="logout.html">Logout</a></li>
            </ul>
        </nav>
    </header>

    <section class="table-container">
        <h2>Appointment History</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Patient Name</th>
                    <th>Doctor ID</th>
                    <th>Doctor Name</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <%
                AdminDao admin = new AdminDao();
                List<Appointment> Appointments = admin.ViewAllAppointment();
                if (Appointments != null && !Appointments.isEmpty()) {
                    for (Appointment appointment : Appointments) { 
                %>
                <tr>
                    <td><%= appointment.getID() %></td>
                    <td><%= appointment.getName() %></td>
                    <td><%= appointment.getDId() %></td>
                    <td><%= appointment.getDname() %></td>
                    <td><%= appointment.getDate() %></td>
                    <td><%= appointment.getTime() %></td>
                    <td><%= appointment.getStatus() %></td>
                </tr>
                <% 
                    }
                } else { 
                %>
                <tr>
                    <td colspan="7">No appointments found.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </section>

    <footer>
        <p>&copy; 2024. All Rights Reserved.</p>
    </footer>
</body>
</html>
