
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
        Reservations:<select id="reservations" name="reservations">
        <%
            int hostID = Integer.parseInt(request.getParameter("hostID"));
            ArrayList<reservation> reservationList = reservation.getReservationHost(hostID);
            for (int i = 0; i < reservationList.size(); i++) {
        %>
            <option value="<%= reservationList.get(i).reservationID %>">
                <%= reservationList.get(i).reservationID %>
            </option>
        <%
            }           
        %>
        </select><br>
        <br><button onclick ="window.location.href='host_main.jsp'"> Back </button>
    </body>
</html>
