<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="java.sql.*, java.util.*, propertyRental.*"%>

<html>
    <title>Payment Transaction</title>
    <body>
        <%
            int reservationID = (int) session.getAttribute("reservationID");
            reservation reservationRecord = reservation.getReservationRecord(reservationID);
            out.println("Total Price: " + reservationRecord.totalPrice);
        %>
        <form action = "receipt.jsp">
            Select Mode of Payment: <select id ="method_id" name="method_id"><br>
                                            <option value ="1">Bank Transfer</option>
                                            <option value ="2">Debit Card</option>
                                            <option value ="3">PayPal</option>
                                            <option value ="4">Credit Card  </option>
                                    </select><br>
            Input Amount: <input type="text" id ="amount_id" name="amount_id"><br>
            <input type="hidden" name="reservationID" value="<%= reservationRecord.reservationID%>">
            <input type="submit" value="Confirm Payment">
        </form>
            <br> <form action="delete_reservation.jsp" method="POST">
            <input type="hidden" name="propertytID" value="<%= reservationRecord.propertyListingID%>">;
            <input type="hidden" name="reservationID" value="<%= reservationRecord.reservationID%>">;
            <button type="submit"> Back </button>
        </form>    </body>
</html>


