package com.hw8;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import edu.jhu.en605681.BookingDay;
import edu.jhu.en605681.HikeType;
import edu.jhu.en605681.Rates;

// Uncomment the following line to enable servlet mapping using annotations
//@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Default constructor
    public MyServlet() {
        super();
    }

    // Handles POST requests
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Process input parameters, then route requests to the other MVC components (business logic and view)
        processRequest(request, response, true); 
    }

    // Handles GET requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
        // Process input parameters, then route requests to the other MVC components (business logic and view)
        processRequest(request, response, false);
    } 

    // Method to process the request and route to appropriate components
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Boolean isPost) throws ServletException, IOException {
        
        // Process input parameters from the request
        String hikeName = request.getParameter("hikeName") != null ? URLDecoder.decode(request.getParameter("hikeName"), StandardCharsets.UTF_8.name()) : null;
        String year = request.getParameter("year") != null ? URLDecoder.decode(request.getParameter("year"), StandardCharsets.UTF_8.name()) : null;
        String month = request.getParameter("month") != null ? URLDecoder.decode(request.getParameter("month"), StandardCharsets.UTF_8.name()) : null;
        String day = request.getParameter("day") != null ? URLDecoder.decode(request.getParameter("day"), StandardCharsets.UTF_8.name()) : null;
        String duration = request.getParameter("duration") != null ? URLDecoder.decode(request.getParameter("duration"), StandardCharsets.UTF_8.name()) : null;
        String hikers = request.getParameter("hikers") != null ? URLDecoder.decode(request.getParameter("hikers"), StandardCharsets.UTF_8.name()) : null;

        // Route requests to the business logic component
        BusinessLogic businessLogic = new BusinessLogic(hikeName, year, month, day, duration, hikers, isPost);

        // Route requests to the view (JSP)
        request.setAttribute("formData", businessLogic.getFormData());
        
        //if (isPost) 
        {
            request.setAttribute("result", businessLogic.getResult()); 
        }
        request.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(request, response);
    }   
}
