// package com.hw8;

// import java.io.IOException;
// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.net.URLDecoder;
// import java.nio.charset.StandardCharsets;
// import java.util.ArrayList;

// import edu.jhu.en605681.BookingDay;
// import edu.jhu.en605681.HikeType;
// import edu.jhu.en605681.Rates;

// // Uncomment the following line to enable servlet mapping using annotations
// //@WebServlet("/MyServlet")
// public class MyServlet extends HttpServlet {
//     private static final long serialVersionUID = 1L;

//     // Default constructor
//     public MyServlet() {
//         super();
//     }

//     // Handles POST requests
//     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         // Process input parameters, then route requests to the other MVC components (business logic and view)
//         processRequest(request, response, true); 
//     }

//     // Handles GET requests
//     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
//         // Process input parameters, then route requests to the other MVC components (business logic and view)
//         processRequest(request, response, false);
//     } 

//     // Method to process the request and route to appropriate components
//     protected void processRequest(HttpServletRequest request, HttpServletResponse response, Boolean isPost) throws ServletException, IOException {
        
//         // Process input parameters from the request
//         String hikeName = request.getParameter("hikeName") != null ? URLDecoder.decode(request.getParameter("hikeName"), StandardCharsets.UTF_8.name()) : null;
//         String year = request.getParameter("year") != null ? URLDecoder.decode(request.getParameter("year"), StandardCharsets.UTF_8.name()) : null;
//         String month = request.getParameter("month") != null ? URLDecoder.decode(request.getParameter("month"), StandardCharsets.UTF_8.name()) : null;
//         String day = request.getParameter("day") != null ? URLDecoder.decode(request.getParameter("day"), StandardCharsets.UTF_8.name()) : null;
//         String duration = request.getParameter("duration") != null ? URLDecoder.decode(request.getParameter("duration"), StandardCharsets.UTF_8.name()) : null;
//         String hikers = request.getParameter("hikers") != null ? URLDecoder.decode(request.getParameter("hikers"), StandardCharsets.UTF_8.name()) : null;

//         // Route requests to the business logic component
//         BusinessLogic businessLogic = new BusinessLogic(hikeName, year, month, day, duration, hikers, isPost);

//         // Route requests to the view (JSP)
//         request.setAttribute("formData", businessLogic.getFormData());
        
//         if (isPost) 
//         {
//             request.setAttribute("result", businessLogic.getResult()); 
//         }
//         request.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(request, response);
//     }   
// }






package com.hw9;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        String startDate = request.getParameter("startDate") != null ? URLDecoder.decode(request.getParameter("startDate"), StandardCharsets.UTF_8.name()) : null;
    
        String records = "";
        List<Reservation> reservations = new ArrayList<>();
        
        // If the request method is POST, query the database
        if (isPost) {
            try {
                DBConnect dbc = new DBConnect();
                // Get reservations starting from the specified date
                reservations = dbc.getReservationsByStartDate(startDate);
                // Set the number of records found
                Integer num_records = reservations.size();
                records = "Total " + num_records + " records found.";  
            } catch (Exception ex) {
                // Handle any exceptions by setting an error message
                records = ex.getMessage() + "\n, Please try again!";
            }
        }else if (startDate == null)
        {
            startDate = Util.getCurrentDateFormatted();
        }
        
        // Set attributes to pass data to the JSP view
        request.setAttribute("startDate", startDate);
        request.setAttribute("records", records); 
        request.setAttribute("reservations", reservations); 
        
        // Forward the request to the JSP view
        request.getRequestDispatcher("/WEB-INF/views/hw9.jsp").forward(request, response);
    }   
}


