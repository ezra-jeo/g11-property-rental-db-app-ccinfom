
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Reservations and Earnings</title>
    </head>
    <body>
        <h1>Host Reservations and Earnings</h1>
        Total Earnings: 
        <%
            float total = 0;
            int hostID = Integer.parseInt(request.getParameter("hostID"));
            ArrayList<reservation> reservationList = reservation.getReservationHost(hostID);
            Payments p = new Payments();
            Payments pay = null;
            for (int i = 0; i < reservationList.size(); i++) {
                pay = p.getPaymentRecord(reservationList.get(i).reservationID);
                total += pay.amount;
            }
            
            out.println(total);
        %>
        <br>Reservations:
        <ul>
        <%
            for (int i = 0; i < reservationList.size(); i++) {
                guest guestRecord = guest.getGuestRecord(reservationList.get(i).guestID);
                Property pr = new Property();
                String propertyName = pr.getPropertyName(reservationList.get(i).propertyListingID);
                pay = p.getPaymentRecord(reservationList.get(i).reservationID);
                float amount = pay.amount;
                String mode = pay.mode;

        %>
        <li>
            Reservation ID: <%= reservationList.get(i).reservationID %>
            <ul>
                <li>Guest: <%= guestRecord.userName %></li>
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
        <br><button onclick ="window.location.href='host_main.jsp'"> Back </button>
    </body>
    
</html>
