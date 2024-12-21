package com.hosptial.servlet;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.hosptial.dao.*;
@WebServlet("/UpdateStatusServlet")
public class UpdateStatusServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int AppointmentId=Integer.parseInt(request.getParameter("id"));
		String status=request.getParameter("status");
		DoctorDao a=new DoctorDao();
		if(a.updateStatus(status,AppointmentId)) {
			response.sendRedirect("Doctor.jsp");
		}else {
			response.sendRedirect("error.html");
		}
	}

}
