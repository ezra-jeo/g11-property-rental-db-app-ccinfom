
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
            int guestID = Integer.parseInt(request.getParameter("guestID"));
            reservation.deleteReservationRecord(reservationID);
            session.setAttribute("guestID", guestID);
            response.sendRedirect("add_reservation.jsp");
        %>
    </body>
</html>
