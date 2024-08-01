package com.hikequote;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/quote")
public class QuoteResource {

    /**
     * Handles POST requests to the /quote/html endpoint.
     * Consumes form data and produces an HTML response.
     * 
     * @param hike      The hike name
     * @param date      The start date of the hike
     * @param duration  The duration of the hike in days
     * @param numHikers The number of hikers
     * @return Response with the quote and any errors formatted in HTML
     */
    @POST
    @Path("/html")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response getQuoteHtml(@FormParam("hike") String hike,
                                 @FormParam("date") String date,
                                 @FormParam("duration") String duration,
                                 @FormParam("numHikers") String numHikers) {
                    
        // Create an instance of BusinessLogic with the provided parameters
        BusinessLogic bu = new BusinessLogic(hike, date, duration, numHikers);

        // Retrieve errors and the calculated quote
        List<String> errors = bu.getErrors();
        double quote = bu.getResult();

        // Build the HTML response
        String result = "<html><body>";
        result += "<p>" + quote + "</p>";

        // Append errors to the HTML response if any
        if (errors.size() > 0) {
            for (String error : errors) {
                result += "<p>" + error + "</p>"; 
            }
        }
        result += "</body></html>";
        
        // Return the response
        return Response.ok(result).build();
    }

    /**
     * Handles POST requests to the /quote/json endpoint.
     * Consumes JSON data and produces a JSON response.
     * 
     * @param request The HikeRequest object containing the request data
     * @return Response with the quote and any errors formatted in JSON
     */
    @POST
    @Path("/json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuoteJson(HikeRequest request) {

        // Extract parameters from the request object
        String hike = request.getHike();
        String date = request.getDate();
        String duration = request.getDuration();
        String numHikers = request.getNumHikers();

        // Create an instance of BusinessLogic with the provided parameters
        BusinessLogic bu = new BusinessLogic(hike, date, duration, numHikers);

        // Retrieve errors and the calculated quote
        List<String> errors = bu.getErrors();
        double quote = bu.getResult();     
        
        // Create a QuoteResponse object
        QuoteResponse response = new QuoteResponse(quote, errors);
        
        // Return the response
        return Response.ok(response).build();
    }

    /**
     * Checks if a given string can be converted to an integer.
     * 
     * @param theStr The string to check
     * @return true if the string can be converted to an integer, false otherwise
     */
    public static boolean isInteger(String theStr) {
        if (theStr == null) {
            return false;
        }
        try {
            Integer.parseInt(theStr);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
