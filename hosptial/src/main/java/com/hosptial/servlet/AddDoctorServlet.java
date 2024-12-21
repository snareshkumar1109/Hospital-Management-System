package com.hosptial.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.hosptial.dao.*;
import com.hosptial.model.*;

/**
 * Servlet implementation class AddDoctorServlet
 */
@WebServlet("/AddDoctorServlet")
public class AddDoctorServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
	     String email=request.getParameter("email");
	     String password=request.getParameter("password");
	     String specification=request.getParameter("Specification");
	     User user=new User();
	     user.setName(name);
	     user.setEmail(email);
	     user.setPassword(password);
	     user.setSpecification(specification);
	     AdminDao admin=new AdminDao();
	     try {
	    	 if(admin.AddDoctor(name, email, password, specification)) {
	    		 response.sendRedirect("admin.jsp");
	    	 }else {
	    		 response.sendRedirect("AddDoctor.jsp");
	    	 }
	     }catch(Exception e) {
	    	 e.printStackTrace();
	    	 response.sendRedirect("error.html");
	     }
	}

}
