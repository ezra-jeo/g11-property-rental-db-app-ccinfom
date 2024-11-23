<%-- 
    Document   : viewHostRating
    Created on : Nov 23, 2024, 8:03:01 PM
    Author     : Ezra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, property_listing.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Host Rating</title>
    </head>
    <body>
            <jsp:useBean id="hostRating" class="property_listing.HostRating" scope="session"/>
            <% 
                Integer v_hostID = Integer.parseInt(request.getParameter("hostID"));
                int status = hostRating.getRatingList(v_hostID);
               
                if (status == 1) {
                    out.println("<h1>View Ratings for Host </h1><br>");
                    for (HostRating rating : hostRating.getHostRatingList()) {
                       out.println(" <p> " + rating.viewRating() + "</p><br>"); 
                    }
                    out.println("<h2>Information Generated Successfully</h2><br>"); 
                }
                else {
                out.println("<h2>Information Generated Unsuccessfully</h2><br>");
                }
             %>

    </body>
</html>

