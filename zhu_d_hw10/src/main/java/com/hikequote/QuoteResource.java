// QuoteResource.java
package com.hikequote;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Path("/quote")
public class QuoteResource {

    @POST
    @Path("/html")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public Response getQuoteHtml(@FormParam("hike") String hike,
                                 @FormParam("date") String date,
                                 @FormParam("duration") String duration,
                                 @FormParam("numHikers") String numHikers) {
                    
        BusinessLogic bu = new BusinessLogic(hike, date, duration, numHikers);

        List<String> errors =  bu.getErrors();
        double quote = bu.getResult();

        String result ="<html><body>";
        result = "<p>" + quote + "</p>";

        if(errors.size() > 0 ){

            for (String error : errors) {
                result += "<p>" + error + "</p>"; 
            }
        }
        result += "</body></html>";
        
        return Response.ok(result).build();
    }

    @POST
    @Path("/json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuoteJson(HikeRequest request) {

        String hike=request.getHike();
        String date = request.getDate();
        String duration = request.getDuration();
        String numHikers = request.getNumHikers();

        BusinessLogic bu = new BusinessLogic(hike, date, duration, numHikers);
        List<String> errors =  bu.getErrors();
        double quote = bu.getResult();     
        QuoteResponse response = new QuoteResponse(quote, errors);
        return Response.ok(response).build();

    }

    // private String calculateQuote(String hike, String date, String duration, String numHikers, List<String> errors) {

    // 	//date = "07/55/2024";
    //     //date = "2024-12-22";
    //     //date = date.replace('/', '-');
    // 	//String quote = "hello:" + "hike=" + hike + ", date=" + date;

    //     BusinessLogic bu = new BusinessLogic(hike, date, duration, numHikers);

    // 	return bu.getResult();
    	
    //     //return 120.00; // Example value
    // }


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
