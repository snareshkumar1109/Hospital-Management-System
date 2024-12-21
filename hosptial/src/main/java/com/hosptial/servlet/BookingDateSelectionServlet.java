package com.hosptial.servlet;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;


@WebServlet("/BookingDateSelectionServlet")
public class BookingDateSelectionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the selected date from the form
        String selectedDateStr = request.getParameter("date");

        if (selectedDateStr != null && !selectedDateStr.isEmpty()) {
            // Convert string to SQL Date
            Date selectedDate = Date.valueOf(selectedDateStr);

            // Store selected date in session
            HttpSession session = request.getSession();
            session.setAttribute("Date", selectedDate);

            // Redirect to ChooseTime.jsp
            response.sendRedirect("ChooseTime.jsp");
        } else {
            // If date is not provided, redirect back to the form with an error
            response.sendRedirect("BookingDateSelection.html?error=Please select a date.");
        }
    }
}
