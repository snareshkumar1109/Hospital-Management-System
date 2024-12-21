package com.hosptial.servlet;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.hosptial.connection.DBConnection;
import com.hosptial.dao.*;
import com.hosptial.model.*;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		try {
			User user=Login.getUserByEmailAndPassword(email, password);
			Connection con=DBConnection.getConnection();
			
			if(user!=null) {
				HttpSession session=request.getSession();
				session.setAttribute("user", user);
				if ("Admin".equalsIgnoreCase(user.getRole())) {
					response.sendRedirect("admin.jsp");
				}else if("Doctor".equalsIgnoreCase(user.getRole())){
				response.sendRedirect("Doctor.jsp");
				}else{
					response.sendRedirect("ChooseSpecification.jsp");
				  	
				}}else {		
				response.sendRedirect("login.html?error=Invalid%20credentials");
			    }
		}catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("error.html");
		}

	}

}
