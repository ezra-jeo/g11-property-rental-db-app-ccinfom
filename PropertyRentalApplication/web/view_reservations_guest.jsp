
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
        Reservations:<select id="reservations" name="reservations">
        <%
            int guestID = Integer.parseInt(request.getParameter("guestID"));
            ArrayList<reservation> reservationList = reservation.getReservationGuest(guestID);
            for (int i = 0; i < reservationList.size(); i++) {
        %>
            <option value="<%= reservationList.get(i).reservationID %>">
                <%= reservationList.get(i).reservationID %>
            </option>
        <%
            }           
        %>
        </select><br>
        <br><button onclick ="window.location.href='guest_main.jsp'"> Back </button>
    </body>
</html>
