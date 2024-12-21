<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hosptial.dao.*" %>
<%@ page import="com.hosptial.model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View All Appointment</title>
    <link rel="stylesheet" href="css/vpa.css"> <!-- Link to the CSS file -->
</head>
<body>
    <header>
        <nav class="navbar">
             <div class="logo">
                <img src="images.png" alt="Consultation Logo" />
            </div>
            <div class="menu-icon" id="menu-icon">&#9776;</div>
            <ul class="navbar-links" id="navbar-links">
                <li><a href="Doctor.jsp">Home</a></li>
                <li><a href="#">HISTORY</a></li>
                <li><a href="logout.html">Logout</a></li>
            </ul>
        </nav>
    </header>
    
    <section class="table-container">
        <h2>View All The Appointments</h2>
        <%
        User user = (User) session.getAttribute("user");
        DoctorDao doctorDao = new DoctorDao();
        List<Appointment> appointments = doctorDao.ViewAppointment(user.getName(), user.getId());
        %>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <%
                if (appointments != null && !appointments.isEmpty()) {
                    for (Appointment appointment : appointments) { 
                %>
                <tr>
                    <td><%= appointment.getID() %></td>
                    <td><%= appointment.getName() %></td>
                    <td><%= appointment.getEmail() %></td>
                    <td><%= appointment.getDate() %></td>
                    <td><%= appointment.getTime() %></td>
                    <td><%= appointment.getStatus() %></td>
                </tr>
                <% 
                    }
                } else { 
                %>
                <tr>
                    <td colspan="6">No pending appointments found.</td>
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
