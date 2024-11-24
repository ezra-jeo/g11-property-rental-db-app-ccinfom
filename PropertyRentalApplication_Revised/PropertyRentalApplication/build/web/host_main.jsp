
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Host Main</title>
    </head>
    <body>
        <h1>Host Account</h1>
        <%
            int hostID = (int) session.getAttribute("hostID");
            host hostRecord = host.getHostRecord(hostID);
            
            out.println("<br>Host ID: " + hostRecord.hostID);
            out.println("<br>User Name: " + hostRecord.userName);
            out.println("<br>First Name: " + hostRecord.firstName);
            out.println("<br>Last Name: " + hostRecord.lastName);
            out.println("<br>Description: " + hostRecord.description);
            out.println("<br>Join Date: " + hostRecord.joinDate);
            out.println("<br>Email: " + hostRecord.email);
            out.println("<br>Phone Number: " + hostRecord.phoneNumber);
            %><br>
        <br><form action="registerListing.html" method="POST">
            <input type="hidden" name="hostID" value="<%= hostID%>">
            <button type="submit"> Add Listing</button>
        </form>
        <form action="deleteListing.jsp" method="POST">
            <input type="hidden" name="hostID" value="<%= hostID%>">
            <button type="submit"> Remove Listing</button>
        </form>
        <form action="getProperty_host.jsp" method="POST">
            <input type="hidden" name="hostID" value="<%= hostID%>">
            <button type="submit"> View Listing</button>
        </form>
        <form action="updateListing.jsp" method="POST">
            <input type="hidden" name="hostID" value="<%= hostID%>">
            <button type="submit"> Update Listing</button>
        </form>
        <br><form action="view_earnings_reservations.jsp" method="POST">
            <input type="hidden" name="hostID" value="<%= hostID%>">
            <button type="submit"> View Reservations & Earnings</button>
        </form>
            <br><form action="viewGuestRating.jsp" method="POST">
            <input type="hidden" name="hostID" value="<%= hostID%>">
            <button type="submit"> View Guest Ratings</button>
        </form>
        <form action="addGuestRating.jsp" method="POST">
            <input type="hidden" name="hostID" value="<%= hostID%>">
            <button type="submit"> Add Guest Ratings</button>
        </form>
        <br><form action="update_host_landing.jsp" method="POST">
            <input type="hidden" name="hostID" value="<%= hostID%>">
            <button type="submit"> Edit Account</button>
        </form>
        <form action="delete_host.jsp" method="POST">
            <input type="hidden" name="hostID" value="<%= hostID%>">
            <button type="submit"> Delete Account</button>
        </form>
        <br><button onclick ="window.location.href='index.html'"> Logout</button>    
    </body>
</html>
