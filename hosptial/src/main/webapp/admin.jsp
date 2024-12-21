<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hosptial.dao.*" %>
<%@ page import="com.hosptial.model.*" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Doctor History</title>
     <link rel="stylesheet" href="css/admin.css">
</head>
<body>
    <header>
        <nav class="navbar">
             <div class="logo">
                <img src="images.png" alt="Consultation Logo" />
            </div>
            <ul class="navbar-links" id="navbar-links">
                <li><a href="#">Home</a></li>
                <li><a href="AddDoctor.jsp">Add Doctor</a></li>
                <li><a href="ViewAllAppointment.jsp">View Appointment</a></li>
                <li><a href="logout.html">Logout</a></li>
            </ul>
        </nav>
    </header>

    <section>
        <%
            User user = (User) session.getAttribute("user");
            AdminDao admin = new AdminDao();
            List<Count> doctorsList = admin.ViewAllDoctor();
        %>
        <h2>Doctor Appointment History</h2>
        <table>
            <thead>
                <tr>
                    <th>Doctor Name</th>
                    <th>Pending Appointments</th>
                    <th>Booked Appointments</th>
                    <th>Rejected Appointments</th>
                    <th>Delete Doctor</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if (doctorsList != null && !doctorsList.isEmpty()) {
                        for (Count doctor : doctorsList) { 
                %>
                <tr>
                    <td><%= doctor.getDname() %></td>
                    <td><%= doctor.getPendingCount() %></td>
                    <td><%= doctor.getBookCount() %></td>
                    <td><%= doctor.getRejectCount() %></td>
                    <td>
                        <form action="DeleteDoctorServlet" method="post">
                            <input type="hidden" name="id" value="<%= doctor.getId() %>">
                            <input type="hidden" name="name" value="<%= doctor.getDname() %>">
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
                <% 
                        } 
                    } else { 
                %>
                <tr>
                    <td colspan="5">No doctor records found.</td>
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
