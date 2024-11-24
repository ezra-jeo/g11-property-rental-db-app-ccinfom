

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Host Rating</title>
    </head>
    <body>
            <jsp:useBean id="hostRating" class="propertyRental.HostRating" scope="session"/>
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
        <br><form action="guest_main.jsp" method="POST">
            <button type="submit"> Back </button>
    </body>
</html>

