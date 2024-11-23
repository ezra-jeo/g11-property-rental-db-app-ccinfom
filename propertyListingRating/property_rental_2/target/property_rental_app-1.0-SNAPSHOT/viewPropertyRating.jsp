<%-- 
    Document   : viewPropertyRating
    Created on : Nov 23, 2024, 11:34:15 PM
    Author     : Ezra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, property_listing.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Get Ratings Given Property ID</title>
    </head>
    <body>
        <h1>View Property Ratings</h1>
        <form action="viewPropertyRating_process.jsp" method="get">
            <jsp:useBean id="property" class="property_listing.Property" scope="session"/>

            Select Property:
            <select id="propertyID" name="propertyID" required>
    
                <%
                    try {
                        // Call the static getPropertyRecords method to fetch all properties
                        property.getAllProperties();
                        
                        // Dynamically populate the dropdown
                        for (Property p : property.getPropertyList()) {
                %>
                            <option value="<%= p.getID() %>"><%= p.getListingName() %></option>
                <%
                        }
                    } 
                    catch (Exception e) {
                        out.println("<option disabled>Error fetching property data</option>");
                        e.printStackTrace();
                    }
                %>
            </select><br><br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
