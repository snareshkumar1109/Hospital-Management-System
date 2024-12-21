<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hosptial.dao.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home | Online Hospital Booking System</title>
    <link rel="stylesheet" href="css/booking.css"> <!-- Link to the CSS file -->
</head>
<body>
    <!-- Header with Navigation -->
    <header>
        <nav class="navbar">
            <div class="logo">
                <img src="images.png" alt="Consultation Logo" />
            </div>
            <div class="menu-icon" id="menu-icon">&#9776;</div>
            <ul class="navbar-links" id="navbar-links">
                <li><a href="#">Home</a></li>
                <li><a href="HistoryPatient.jsp">HISTORY</a></li>
                <li><a href="remove.jsp">Canceling Appointment</a></li>
                <li><a href="AboutUs.html">About Us</a></li>
                <li><a href="Contact.html">Contact Us</a></li>
                <li><a href="logout.html">Logout</a></li>
            </ul>
        </nav>
    </header>

    <!-- Doctor Selection Section -->
    <section>
        <%
        // Fetch doctor specifications
        PatientDao patientDao = new PatientDao();
        List<String> specifications = patientDao.AllSpecification();
        %>
        <form method="post" action="">
            <label>Choose a field of Doctor you want:</label><br>
            <%
            for (int i = 0; i < specifications.size(); i++) {
                if (i % 3 == 0) { %><br><% }
            %>
            <input type="radio" name="Specification" value="<%= specifications.get(i) %>"> <%= specifications.get(i) %>
            <% } %>
            <br><br>
            <button type="submit">Next</button>
        </form>

        <%
        // Retrieve and store selected specification in session, then forward
        String selectedSpecification = request.getParameter("Specification");
        if (selectedSpecification != null && !selectedSpecification.isEmpty()) {
            session.setAttribute("Specification", selectedSpecification);
            response.sendRedirect("ChooseDoctor.jsp"); // Use redirect instead of forward after form submission
        }
        %>
    </section>

    <footer>
        <p>&copy; 2024. All Rights Reserved.</p>
    </footer>
</body>
</html>
