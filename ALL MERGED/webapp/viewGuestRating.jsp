<%-- 
    Document   : viewGuestRating
    Created on : Nov 23, 2024, 11:33:39 PM
    Author     : Ezra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, propertyRental.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Get Ratings Given Guest ID</title>
    </head>
    <body>
        <h1>View Guest Ratings</h1>
        <form action="viewGuestRating_process.jsp" method="get">
            Select Guest:
            <select id="guestID" name="guestID" required>
                <%
                    try {
                        // Call the static getGuestRecords method to fetch all guests
                        ArrayList<guest> guestList = guest.getGuestRecords();

                        // Dynamically populate the dropdown
                        for (guest g : guestList) {
                %>
                            <option value="<%= g.guestID %>"><%= g.userName %></option>
                <%
                        }
                    } 
                    catch (Exception e) {
                        out.println("<option disabled>Error fetching guest data</option>");
                        e.printStackTrace();
                    }
                %>
            </select><br><br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
