

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*, propertyRental.*"%>
<!-- add import to reservation -->
<!DOCTYPE html>
<html>
    <head>
        <title>Submit Guest Rating</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Submit Guest Rating</h1>
        <form method="post" action="addGuestRating_process.jsp">
            <!-- Dropdown for Reservation ID -->
            reservation ID:
            <select id="reservationID" name="reservationID" required>
                <%
                    if (reservation.getReservationHost(Integer.parseInt(request.getParameter("hostID"))) != null) {
                        for (reservation reservation : reservation.getReservationHost(Integer.parseInt(request.getParameter("hostID")))) {
                %>
                            <option value="<%= reservation.reservationID %>">
                                <%= String.format("Guest: %s - Reservation: %s", guest.getGuestRecord(reservation.guestID).userName, reservation.reservationID) %>
                            </option>
                <%
                        }
                    } 
                    else {
                %>
                        <option value="" disabled>No Reservations Available</option>
                <%
                    }
                %>
            </select><br><br>
            
            Rating: <input type="number" id="rating" name="rating" step="0.1" min="0" max="5" required><br><br>
            Review: <textarea id="review" name="review" rows="4" cols="50" placeholder="Write your review here..." required></textarea><br><br>
            
            <input type="submit" value="Submit Rating">
        </form><br>
        <a href="host_main.jsp">
                <button type="button">Back</button>
        </a>
    </body>
</html>

