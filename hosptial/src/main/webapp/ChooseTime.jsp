<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hosptial.dao.PatientDao" %>
<%@ page import="com.hosptial.model.User" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.sql.Time" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Doctor Appointment Selection</title>
<link rel="stylesheet" href="css/booking.css"> 
</head>
<body>
  <header>
    <nav class="navbar">
       <div class="logo">
                <img src="images.png" alt="Consultation Logo" />
            </div>
      <div class="menu-icon" id="menu-icon">&#9776;</div>
      <ul class="navbar-links" id="navbar-links">
        <li><a href="#">Home</a></li>
        <li><a href="HistoryPatient.jsp">History</a></li>
        <li><a href="remove.jsp">Cancel Appointment</a></li>
        <li><a href="AboutUs.html">About Us</a></li>
        <li><a href="Contact.html">Contact Us</a></li>
        <li><a href="logout.html">Logout</a></li>
      </ul>
    </nav>
  </header>

  <section>
    <%
      PatientDao patientDao = new PatientDao();
      User doctor = (User) session.getAttribute("Doctor");
      Date date = (Date) session.getAttribute("Date");
      System.out.println(date);
      System.out.println(doctor);
      if (doctor != null && date != null) {
          List<Time> availableTimes = patientDao.getAllAvailableTimes(doctor.getId(), doctor.getName(), date);
    %>
          <form action="BookingAppointmentServlet" method="post">
            <p>Select an available time:</p>
            <%
              for (int i = 0; i < availableTimes.size(); i++) {
                  if (i % 3 == 0 && i > 0) { 
            %><br>
                  <%
                  } 
            %>
                  <input type="radio" name="time" value="<%= availableTimes.get(i) %>">
                  <%= availableTimes.get(i) %>
            <%
              }
            %>
            <br><br>
            <button type="submit">Book Appointment</button>
          </form>
    <%
      } else {
    %>
          <p>Error: Doctor or Date information is missing in the session.</p>
    <%
      }
    %>
  </section>

  <footer>
    <p>&copy; 2024. All Rights Reserved.</p>
  </footer>

</body>
</html>
