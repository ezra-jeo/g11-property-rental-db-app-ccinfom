
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Reservations Guest</title>
    </head>
    <body>
        <h1>Reservations Under Guest</h1>
        Reservations:
        <ul>
        <%
            int guestID = Integer.parseInt(request.getParameter("guestID"));
            ArrayList<reservation> reservationList = reservation.getReservationGuest(guestID);
            for (int i = 0; i < reservationList.size(); i++) {
                host hostRecord = host.getHostRecord(reservationList.get(i).hostID);
                Property p = new Property();
                String propertyName = p.getPropertyName(reservationList.get(i).propertyListingID);
        %>
            
        <li>
        <%= reservationList.get(i).reservationID %> : <%= hostRecord.userName %> - <%= propertyName %> (<%= reservationList.get(i).startDate %> - <%= reservationList.get(i).endDate %>)
        </li>
        <%
            }           
        %>
        </ul>
        <br><button onclick ="window.location.href='guest_main.jsp'"> Back </button>
    </body>
</html>
