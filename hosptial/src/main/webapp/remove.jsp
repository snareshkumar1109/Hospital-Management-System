<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hosptial.dao.PatientDao" %>
<%@ page import="com.hosptial.model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cancel Appointment</title>
    <link rel="stylesheet" href="css/remove.css"> <!-- Link to the CSS file -->
</head>
<body>
    <header>
        <nav class="navbar">
            <div class="logo">
                <img src="images.png" alt="Consultation Logo" />
            </div>
            <div class="menu-icon" id="menu-icon">&#9776;</div>
            <ul class="navbar-links" id="navbar-links">
                <li><a href="ChooseSpecification.jsp">Home</a></li>
                <li><a href="HistoryPatient.jsp">History</a></li>
                <li><a href="#">Cancel Appointment</a></li>
                <li><a href="AboutUs.html">About Us</a></li>
                <li><a href="Contact.html">Contact Us</a></li>
                <li><a href="logout.html">Logout</a></li>
            </ul>
        </nav>
    </header>
    
    <section class="table-container">
        <h2>Appointment History</h2>
        <%
        User user = (User) session.getAttribute("user");
        PatientDao patientDao = new PatientDao();
        List<Appointment> appointments = patientDao.ViewAppointmentPatient(user.getName());
        
        %>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Doctor ID</th>
                    <th>Doctor Name</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Status</th>
                    <th>Cancel</th>
                </tr>
            </thead>
            <tbody>
                <%
                if (appointments != null && !appointments.isEmpty()) {
                    for (Appointment appointment : appointments) { 
                        if (!"reject".equalsIgnoreCase(appointment.getStatus())) {
                %>
                <tr>
                    <td><%= appointment.getID() %></td>
                    <td><%= appointment.getDId() %></td>
                    <td><%= appointment.getDname() %></td>
                    <td><%= appointment.getDate() %></td>
                    <td><%= appointment.getTime() %></td>
                    <td><%= appointment.getStatus() %></td>
                    <td>
                        <form action="UpdateStatusServlet" method="post">
                            <input type="hidden" name="id" value="<%= appointment.getID() %>">
                            <input type="hidden" name="status" value="Cancel by you">
                            <button type="submit" class="cancel-button">Cancel</button>
                        </form>
                    </td>
                </tr>
                <% 
                        }
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
