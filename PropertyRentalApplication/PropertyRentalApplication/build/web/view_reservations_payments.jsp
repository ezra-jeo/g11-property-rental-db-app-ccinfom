
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Reservations and Payments</title>
    </head>
    <body>
        <h1>Guest Reservations and Payments</h1>
        <%
            int guestID = Integer.parseInt(request.getParameter("guestID"));
            ArrayList<reservation> reservationList = reservation.getReservationGuest(guestID);
            Payments p = new Payments();
            Payments pay = null;
        %>
        <br>Reservations:
        <ul>
        <%
            for (int i = 0; i < reservationList.size(); i++) {
                host hostRecord = host.getHostRecord(reservationList.get(i).hostID);
                Property pr = new Property();
                String propertyName = pr.getPropertyName(reservationList.get(i).propertyListingID);
                pay = p.getPaymentRecord(reservationList.get(i).reservationID);
                float amount = pay.amount;
                String mode = pay.mode;
        %>
        <li>
            Reservation ID: <%= reservationList.get(i).reservationID %>
            <ul>
                <li>Host: <%= hostRecord.userName %></li>
                <li>Property: <%= propertyName %></li>
                <li>Date: <%= reservationList.get(i).startDate %> - <%= reservationList.get(i).endDate %></li>
                <li>Total Price: <%= reservationList.get(i).totalPrice %></li>
               <li>Payment Amount & Mode: <%= amount %> (<%= mode %>)</li>
            </ul>
        </li>
        <%
            }           
        %>
        </ul>
        <br><button onclick ="window.location.href='guest_main.jsp'"> Back </button>
    </body>
    
</html>
