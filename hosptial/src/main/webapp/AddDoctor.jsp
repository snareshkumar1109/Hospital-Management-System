<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hosptial.dao.*" %>
<%@ page import="com.hosptial.model.*" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.sql.Time" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Doctor</title>
    <link rel="stylesheet" href="css/AddDoctor.css"> <!-- Link to the CSS file -->
</head>
<body>
    <header>
        <nav class="navbar">
             <div class="logo">
                <img src="images.png" alt="Consultation Logo" />
            </div>
     
            <ul class="navbar-links" id="navbar-links">
                <li><a href="admin.jsp">Home</a></li>
                <li><a href="#">Add Doctor</a></li>
                <li><a href="ViewAllAppointment.jsp">View Appointment</a></li>
                <li><a href="logout.html">Logout</a></li>
            </ul>
        </nav>
    </header>

    <section class="form-container">
        <h2>Add Doctor</h2>
        <form action="AddDoctorServlet" method="post">
            <div class="input-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="input-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="input-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="input-group">
                <label for="specification">Specification:</label>
                <input type="text" id="specification" name="Specification" required>
            </div>
            <button type="submit" class="submit-btn">Submit</button>
        </form>
    </section>

    <footer>
        <p>&copy; 2024. All Rights Reserved.</p>
    </footer>
</body>
</html>
