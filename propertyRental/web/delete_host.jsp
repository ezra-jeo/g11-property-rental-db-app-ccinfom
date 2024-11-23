
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Host Delete</title>
    </head>
    <body>
        <%
            int hostID = Integer.parseInt(request.getParameter("hostID"));
            ArrayList<reservation> reservationList = reservation.getReservationHost(hostID);
            
            if (reservationList.size() == 0) {
                host.deleteHostRecord(hostID);
                out.println("<h2>Host Account with ID: " + hostID + " Deleted Successfuly!</h2>");
            }
            else {
                out.println("<h2>Host Account with ID: " + hostID + " Have Existing Reservations!</h2>");
            }
        %>
            <br><button onclick ="window.location.href='index.html'"> Back </button>
    </body>
</html>
