<%-- 
    Document   : addHostRating_process
    Created on : Nov 23, 2024, 7:07:24 PM
    Author     : Ezra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, property_listing.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Add Host Rating</title>
    </head>
    <body>
        <jsp:useBean id="hostRating" class="property_listing.HostRating" scope="session"/>
        <% 
            int v_reservationID = Integer.parseInt(request.getParameter("reservationID"));
            Double v_rating = Double.parseDouble(request.getParameter("rating"));
            String v_review = request.getParameter("review");
            int status = hostRating.addRating(v_reservationID, v_review, v_rating);

            if (status == 1) {
                out.println("<h2>Rating Successful</h2><br>"); 
            }
            else {
            out.println("<h2>Rating Unsuccessful</h2><br>");
            }
         %>

    </body>
</html>
