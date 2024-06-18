

import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import edu.jhu.en605681.BookingDay;
import edu.jhu.en605681.HikeType;
import edu.jhu.en605681.Rates;
import java.time.*; 
import java.time.temporal.*;
import java.text.SimpleDateFormat;
import java.util.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


// ****** Note ***** 
// zip file name: lastname_firstinit_hw#.zip
// how to compile in linux terminal: javac -cp ".:./BhcUtils.jar" zhu_d_hw3.java zhu_d_hw4.java helper.java main.java
// how to run in linux terminal: java -cp ".:./BhcUtils.jar" main

public class zhu_d_hw4 extends zhu_d_hw3{
    
    public zhu_d_hw4()
    {

    }
    
    @Override
    protected double getCost()
    {
        return rateObj.getCost();
    }

    // protected double updateCost( HikeType hikeType,
    //     Integer duration,
    //     Integer startYear, 
    //     Integer startMonth,
    //     Integer startDay,
    //     Integer numHikers
    // ){
    //     //return 100;

    //     if(hikeType==null || duration==null || 
    //     startYear==null || startMonth==null || startDay==null || 
    //     numHikers==null)
    //     {
    //         System.out.println("something wrong here!!!");
    //         return 0.02;
    //     }

    //     String serverAddress = "web6.jhuep.com";
    //     int port = 20025;
        
    //     // Data to be sent to the server
    //     String hikeId = "0";
    //     // String beginMonth = "8";
    //     // String beginDay = "7";
    //     // String beginYear = "2024";
    //     // String duration = "3";
    //     // String numberHikers = "2";
    //     String dataToSend = hikeId + "&" + startMonth.toString() + "&" + startDay.toString() + "&" + startYear.toString() + "&" + duration.toString() + "&" + numHikers.toString();

    //     try (
    //         Socket socket = new Socket(serverAddress, port);
    //         PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    //         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
    //         ) 
    //         {

    //             // Send data to the server
    //             out.println(dataToSend);
    
    //             // Read response from the server
    //             String response = in.readLine();
    
    //             System.out.println("Response from server: " + response);
    
    //         } 
    //         catch (Exception e) 
    //         {
    //             System.out.println("*****************************************************: ");
    //             e.printStackTrace();
    //         }


    //     return super.updateCost( hikeType,
    //      duration,
    //      startYear, 
    //      startMonth,
    //      startDay,
    //      numHikers);
    // }
}

