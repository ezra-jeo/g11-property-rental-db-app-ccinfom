
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Reservation</title>
    </head>
    <body>
        <h2> Add Reservation </h2>
        <%
            int propertyListingID = (int) session.getAttribute("propertyListingID");
            out.println("Property ID: " + propertyListingID);
        %>  
        
        <form action="check_reservation.jsp" method="POST">
            Start Date: <input type="date" id="startDate" name="startDate" required>
            End Date:  <input type="date" id="endDate" name="endDate" required>
            <input type="hidden" name="propertyListingID" value="<%= propertyListingID%>">
            <button type="submit">Reserve<br> 
        </form>
        <br><button onclick ="window.location.href='guest_main.jsp'"> Back</button>
    </body>
</html>