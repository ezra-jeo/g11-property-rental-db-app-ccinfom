<%-- 
    Document   : viewGuestRating_process
    Created on : Nov 23, 2024, 11:33:54 PM
    Author     : Ezra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Guest Rating</title>
    </head>
    <body>
        <jsp:useBean id="guestRating" class="propertyRental.GuestRating" scope="session"/>
        <% 
            Integer v_guestID = Integer.parseInt(request.getParameter("guestID"));
            int status = guestRating.getRatingList(v_guestID);
            
            if (status == 1) {
                out.println("<h1>View Ratings for Guest</h1><br>");
                for (GuestRating rating : guestRating.getGuestRatingList()) {
                    out.println("<p>" + rating.viewRating() + "</p><br>");
                }
                out.println("<h2>Information Generated Successfully</h2><br>");
            } else {
                out.println("<h2>Information Generated Unsuccessfully</h2><br>");
            }
        %>
        <br><form action="host_main.jsp" method="POST">
            <button type="submit"> Back </button>
    </body>
</html>

