<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hosptial.dao.*" %>
<%@ page import="com.hosptial.model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pending Appointments</title>
    <link rel="stylesheet" href="css/Doctor.css"> <!-- Link to the CSS file -->
</head>
<body>
    <header>
        <nav class="navbar">
             <div class="logo">
                <img src="images.png" alt="Consultation Logo" />
            </div>
         
            <ul class="navbar-links" id="navbar-links">
                <li><a href="#">Home</a></li>
                <li><a href="HistoryDoctor.jsp">HISTORY</a></li>
                <li><a href="logout.html">Logout</a></li>
            </ul>
        </nav>
    </header>
    
    <section class="table-container">
        <h2>Pending Appointments</h2>
        <%
        User user = (User) session.getAttribute("user");
        DoctorDao doctorDao = new DoctorDao();
        List<Appointment> appointments = doctorDao.ViewPendingAppointment(user.getName(), user.getId());
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
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                if (appointments != null && !appointments.isEmpty()) {
                    for (Appointment appointment : appointments) { 
                        if (!("reject".equalsIgnoreCase(appointment.getStatus()))) {
                %>
                <tr>
                    <td><%= appointment.getID() %></td>
                    <td><%= appointment.getDId() %></td>
                    <td><%= appointment.getDname() %></td>
                    <td><%= appointment.getDate() %></td>
                    <td><%= appointment.getTime() %></td>
                    <td><%= appointment.getStatus() %></td>
                    <td>
                        <form action="UpdateStatusServlet" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%= appointment.getID() %>">
                            <input type="hidden" name="status" value="Booked">
                            <button type="submit" class="btn-ok">OK</button>
                        </form>
                        <form action="UpdateStatusServlet" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%= appointment.getID() %>">
                            <input type="hidden" name="status" value="reject">
                            <button type="submit" class="btn-cancel">Cancel</button>
                        </form>
                    </td>
                </tr>
                <% 
                        }
                    }
                } else { 
                %>
                <tr>
                    <td colspan="7">No pending appointments found.</td>
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
