
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservation Delete</title>
    </head>
    <body>
        <%
            int reservationID = Integer.parseInt(request.getParameter("reservationID"));
            int propertyID = Integer.parseInt(request.getParameter("propertyListingID"));
            reservation.deleteReservationRecord(reservationID);
            session.setAttribute("propertyListingID", propertyID);
            response.sendRedirect("add_reservation.jsp");
        %>
    </body>
</html>
