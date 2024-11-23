<%-- 
    Document   : addGuestRating_process
    Created on : Nov 24, 2024, 12:16:38 AM
    Author     : Ezra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Add Guest Rating</title>
    </head>
    <body>
        <jsp:useBean id="guestRating" class="propertyRental.GuestRating" scope="session"/>
        <% 
            int v_reservationID = Integer.parseInt(request.getParameter("reservationID"));
            Double v_rating = Double.parseDouble(request.getParameter("rating"));
            String v_review = request.getParameter("review");
            int status = guestRating.addRating(v_reservationID, v_review, v_rating);

            if (status == 1) {
                out.println("<h2>Rating Successful</h2><br>"); 
            }
            else {
                out.println("<h2>Rating Unsuccessful</h2><br>");
            }
         %>
        <br><form action="host_main.jsp" method="POST">
            <button type="submit"> Back </button>
        </form> 
    </body>
</html>

