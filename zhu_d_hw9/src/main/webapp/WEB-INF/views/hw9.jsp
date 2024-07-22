<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.hw9.Reservation, java.util.List, java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reservation List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>
    <h1>Hike Registration Search</h1>
    <h2>Assignment: Homework for JDBC</h2>
    <h3>Student Name: Derek Zhu</h3>

    <!-- Form to accept date input -->
    <form method="post" action="${pageContext.request.contextPath}/MyServlet">
        <label for="startDate">Enter Start Date:</label>
        <input type="date" id="startDate" name="startDate" required value="<%= request.getAttribute("startDate") %>">
        <button type="submit">Search</button>
    </form>

    <%
        String startDate = request.getParameter("startDate");
        List<Reservation> reservations = (List<Reservation> ) request.getAttribute("reservations");
        String records = (String)request.getAttribute("records");
        records = (records !=null ? records: "");
    %>
    
    <table>
        <thead>
            <tr>
                <th>Start Day</th>
                <th>Number of Days</th>
                <th>Location</th>
                <th>Guide First Name</th>
                <th>Guide Last Name</th>
                <th>Reservation First Name</th>
                <th>Reservation Last Name</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (reservations.isEmpty()) {
            %>
                <tr>
                    <td colspan="7">No reservations found for the entered date.</td>
                </tr>
            <%
                } else {
                    for (Reservation reservation : reservations) {
            %>
                        <tr>
                            <td><%= reservation.getStartDay() %></td>
                            <td><%= reservation.getNumberOfDays() %></td>
                            <td><%= reservation.getLocation() %></td>
                            <td><%= reservation.getGuideFirstName() %></td>
                            <td><%= reservation.getGuideLastName() %></td>
                            <td><%= reservation.getReservationFirstName() %></td>
                            <td><%= reservation.getReservationLastName() %></td>
                        </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
    
    <%
    if (records != "") {
    %>
    <h2><%= records %></h2>
    <%
    }
    %>

</body>
</html>
