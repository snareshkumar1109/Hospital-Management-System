package com.hosptial.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import com.hosptial.dao.PatientDao;
import com.hosptial.model.User;

@WebServlet("/BookingAppointmentServlet")
public class BookingAppointmentServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve session and user information
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		User doctor = (User) session.getAttribute("Doctor");
		Date date = (Date) session.getAttribute("Date"); 

		String timeStr = request.getParameter("time");
		Time time = (timeStr != null && !timeStr.isEmpty()) ? Time.valueOf(timeStr) : null;
		
		if (user != null && doctor != null && date != null && time != null) {
			
			PatientDao patientDao = new PatientDao();

			boolean isBooked = patientDao.BookingAppointment(user.getName(),user.getEmail(),doctor.getName(),doctor.getId(),date,time,"Pending");

			// Redirect based on booking result
			if (isBooked) {
				response.sendRedirect("HistoryPatient.jsp");
			} else {
				response.sendRedirect("error.html");
			}
		} else {
			// Redirect to error page if any required data is missing
			response.sendRedirect("error.html");
		}
	}
}

