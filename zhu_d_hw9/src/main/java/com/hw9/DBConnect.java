package com.hw9;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBConnect {

    // Database connection details
    int port = 3306;
    String username = "johncolter";
    String password = "LetMeIn";
    String dbname = "class";
    String host = "web6.jhuep.com";
    String url = "jdbc:mysql://" + host + ":" + port + "/" + dbname;

    // Default constructor
    public DBConnect() {}

    // Parameterized constructor to set custom database connection details
    public DBConnect(String username, String password, String dbname, String host, int port) {
        this.username = username;
        this.password = password;
        this.dbname = dbname;
        this.host = host;
        this.port = port;
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + dbname;
    }

    /**
     * Retrieves a list of reservations starting from a specific date.
     * 
     * @param startDate The start date in yyyy-MM-dd format.
     * @return A list of reservations starting from the specified date.
     * @throws SQLException If a database access error occurs.
     */
    public List<Reservation> getReservationsByStartDate(String startDate) throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            // Establish the database connection
            conn = DriverManager.getConnection(this.url, this.username, this.password);

            // SQL query to retrieve reservations from a specific start date onwards
            String qry = "SELECT reservation.StartDay, reservation.NumberOfDays, locations.location, guides.First, guides.Last, reservation.First, reservation.Last " +
                         "FROM reservation, guides, locations " +
                         "WHERE reservation.guide = guides.idguides AND reservation.location = locations.idlocations " +
                         "AND reservation.StartDay >= ? " +
                         "ORDER BY reservation.StartDay";
            
            // Prepare the SQL statement with the provided start date
            preparedStatement = conn.prepareStatement(qry);
            preparedStatement.setDate(1, Date.valueOf(startDate));

            // Execute the query and obtain the result set
            rs = preparedStatement.executeQuery();

            // Process the result set and populate the list of reservations
            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setStartDay(rs.getDate("StartDay"));
                reservation.setNumberOfDays(rs.getInt("NumberOfDays"));
                reservation.setLocation(rs.getString("location"));
                reservation.setGuideFirstName(rs.getString("First"));
                reservation.setGuideLastName(rs.getString("Last"));
                reservation.setReservationFirstName(rs.getString("reservation.First"));
                reservation.setReservationLastName(rs.getString("reservation.Last"));
                reservations.add(reservation);
            }
        } catch (SQLException ex) {
            // Print stack trace and rethrow the exception
            ex.printStackTrace();
            throw ex;
        } finally {
            // Close the resources in the finally block to ensure they are closed even if an exception occurs
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
        return reservations;
    }
}
