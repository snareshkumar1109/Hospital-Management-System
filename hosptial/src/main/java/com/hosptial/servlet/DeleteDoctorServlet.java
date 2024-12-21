package com.hosptial.servlet;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.hosptial.dao.AdminDao;

/**
 * Servlet implementation class DeleteDoctorServlet
 */
@WebServlet("/DeleteDoctorServlet")
public class DeleteDoctorServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		AdminDao admin=new AdminDao();
		try {
	    	 if(admin.DeleteDoctor(id, name)) {
	    		 response.sendRedirect("admin.jsp");
	    	 }else {
	    		 response.sendRedirect("error.jsp");
	    	 }
	     }catch(Exception e) {
	    	 e.printStackTrace();
	    	 response.sendRedirect("error.html");
	     }
		
	}

}
