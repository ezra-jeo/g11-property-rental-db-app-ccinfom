
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guest Delete</title>
    </head>
    <body>
        <%
            int guestID = Integer.parseInt(request.getParameter("guestID"));
            ArrayList<reservation> reservationList = reservation.getReservationGuest(guestID);
            
            if (reservationList.size() == 0) {
                guest.deleteGuestRecord(guestID);
                out.println("<h2>Guest Account with ID: " + guestID + " Deleted Successfuly!</h2>");
            }
            else {
                out.println("<h2>Guest Account with ID: " + guestID + " Have Existing Reservations!</h2>");
            }
            
        %>
            <br><button onclick ="window.location.href='index.html'"> Back To Main Menu</button>
    </body>
</html>