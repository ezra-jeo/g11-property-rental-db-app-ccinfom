<%-- 
    Document   : viewPropertyRating_process
    Created on : Nov 23, 2024, 11:34:42 PM
    Author     : Ezra
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, property_listing.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Property Rating</title>
    </head>
    <body>
        <jsp:useBean id="propertyRating" class="property_listing.PropertyRating" scope="session"/>
        <% 
            Integer v_propertyID = Integer.parseInt(request.getParameter("propertyID"));
            int status = propertyRating.getRatingList(v_propertyID);
            
            if (status == 1) {
                out.println("<h1>View Ratings for Property</h1><br>");
                for (PropertyRating rating : propertyRating.getPropertyRatingList()) {
                    out.println("<p>" + rating.viewRating() + "</p><br>");
                }
                out.println("<h2>Information Generated Successfully</h2><br>");
            } else {
                out.println("<h2>Information Generated Unsuccessfully</h2><br>");
            }
        %>
    </body>
</html>
