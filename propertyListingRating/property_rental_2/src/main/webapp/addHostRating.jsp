<%-- 
    Document   : addHostRating
    Created on : Nov 23, 2024, 7:15:33?PM
    Author     : Ezra
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*, property_listing.*"%>
<!-- add import to reservation -->
<!DOCTYPE html>
<html>
    <head>
        <title>Submit Host Rating</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Submit Host Rating</h1>
        <form method="post" action="addHostRating_process.jsp">
            <!-- Dropdown for Reservation ID -->
            Reservation ID:
            <select id="reservationID" name="reservationID" required>
                <%
                    if (Reservation.getReservationGuestHost() != null) {
                        for (Reservation reservation : Reservation.getReservationGuestHost()) {
                %>
                            <option value="<%= reservation.ID %>">
                                <%= reservation.ID %>
                            </option>
                <%
                        }
                    } else {
                %>
                        <option value="" disabled>No Reservations Available</option>
                <%
                    }
                %>
            </select><br><br>
            
            
            Rating: <input type="number" id="rating" name="rating" step="0.1" min="0" max="5" required><br><br>
            Review: <textarea id="review" name="review" rows="4" cols="50" placeholder="Write your review here..." required></textarea><br><br>
            
            <input type="submit" value="Submit Rating">
        </form>
    </body>
</html>
