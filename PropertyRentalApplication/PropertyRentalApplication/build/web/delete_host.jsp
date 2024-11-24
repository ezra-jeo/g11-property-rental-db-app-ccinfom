
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
                Property p = new Property();
                p.deleteListingHost(hostID);
        %>
        <h2>Host Account with ID: <%= hostID %> And Listing/s Have Been Deleted Successfully!</h2>
                <br>
                <button onclick="window.location.href='index.html'">Back To Main Menu</button>
                <br>
        <%
            } else {
                session.setAttribute("hostID", hostID);

        %>
                <h2><h2>Host Account with ID: <%= hostID %> Have Existing Reservations!</h2></h2>
                <br>
                <button onclick="window.location.href='host_main.jsp'">Back</button>
        <%
            }
        %>
    </body>
</html>
