
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guest Main</title>
    </head>
    <body>
        <h1>Guest Account</h1>
        <%
            int guestID = (int) session.getAttribute("guestID");
            guest guestRecord = guest.getGuestRecord(guestID);
            
            out.println("<br>Guest ID: " + guestRecord.guestID);
            out.println("<br>User Name: " + guestRecord.userName);
            out.println("<br>First Name: " + guestRecord.firstName);
            out.println("<br>Last Name: " + guestRecord.lastName);
            out.println("<br>Description: " + guestRecord.description);
            out.println("<br>Join Date: " + guestRecord.joinDate);
            out.println("<br>Email: " + guestRecord.email);
            out.println("<br>Phone Number: " + guestRecord.phoneNumber);
            %><br>
         <br><form action="add_reservation.jsp" method="POST">
            <input type="hidden" name="guestID" value="<%= guestID%>">
            <button type="submit"> Add Reservation</button>
        </form>
         <form action="view_reservations_guest.jsp" method="POST">
            <input type="hidden" name="guestID" value="<%= guestID%>">
            <button type="submit"> View Reservations</button>
        </form>
        </form>
            <br><form action="viewHostRating.jsp" method="POST">
            <input type="hidden" name="guestID" value="<%= guestID%>">
            <button type="submit"> View Host Ratings</button>
        </form>
        <form action="addHostRating.jsp" method="POST">
            <input type="hidden" name="guestID" value="<%= guestID%>">
            <button type="submit"> Add Host Ratings</button>
        </form>
        <br><form action="viewPropertyRating.jsp" method="POST">
            <input type="hidden" name="guestID" value="<%= guestID%>">
            <button type="submit"> View Property Ratings</button>
        </form>
        <form action="addPropertyRating.jsp" method="POST">
            <input type="hidden" name="guestID" value="<%= guestID%>">
            <button type="submit"> Add Property Ratings</button>
        </form>         
        <br><form action="update_guest_landing.jsp" method="POST">
            <input type="hidden" name="guestID" value="<%= guestID%>">
            <button type="submit"> Edit Account</button>
        </form>
         <form action="delete_guest.jsp" method="POST">
            <input type="hidden" name="guestID" value="<%= guestID%>">
            <button type="submit"> Delete Account</button>
        </form>
        <br><button onclick ="window.location.href='index.html'"> Logout</button>
    </body>
</html>
