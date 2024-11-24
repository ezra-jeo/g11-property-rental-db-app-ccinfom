
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Reservations Host</title>
    </head>
    <body>
        <h1>Reservations Under Host</h1>
        Reservations:
        <ul>
        <%
            int hostID = Integer.parseInt(request.getParameter("hostID"));
            ArrayList<reservation> reservationList = reservation.getReservationHost(hostID);
            for (int i = 0; i < reservationList.size(); i++) {
                guest guestRecord = guest.getGuestRecord(reservationList.get(i).guestID);
                Property p = new Property();
                String propertyName = p.getPropertyName(reservationList.get(i).propertyListingID);
        %>
            
        <li>
        <%= reservationList.get(i).reservationID %> : <%= guestRecord.userName %> - <%= propertyName %> (<%= reservationList.get(i).startDate %> - <%= reservationList.get(i).endDate %>)
        </li>
        <%
            }           
        %>
        </ul>
        <br><button onclick ="window.location.href='host_main.jsp'"> Back </button>
    </body>
</html>