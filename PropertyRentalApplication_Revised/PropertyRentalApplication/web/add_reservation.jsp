
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="propertyRental.*, java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Reservation</title>
    </head>
<!DOCTYPE html>
<html>
<head>
    <style>
        h2 {
            margin-bottom: 20px;
        }

        form {
            margin-bottom: 20px;
        }

        select, input[type="date"], button {
            display: block;
            margin: 10px 0;
            padding: 10px;
            font-size: 16px;
        }
    </style>
</head>
<body>
    <h2>Add Reservation</h2>

    <form method="POST" action="confirm_reservation.jsp">
        <label for="propertyListingID">Choose a Property:</label>
        <select id="propertyListingID" name="propertyListingID" required>
            <%
                int guestID = (int) session.getAttribute("guestID");
                //int guestID = Integer.parseInt(request.getParameter("guestID"));
                Property property = new Property();
                property.getAllProperties(); 
                for (Property prop : property.getPropertyList()) {
            %>
                <option value="<%= prop.getID() %>"><%=prop.getID()%> : <%= prop.getListingName() %> - Price per Night: <%= prop.getPricePerNight() %></option>
            <%
                }
            %>
        </select>
        <label for="startDate">Start Date:</label>
        <input type="date" id="startDate" name="startDate" required>
        
        <label for="endDate">End Date:</label>
        <input type="date" id="endDate" name="endDate" required>
        <input type="hidden" name="guestID" value="<%= guestID%>">
        <button type="submit">Reserve</button>
    </form>
    <div class="button-container">
        <button onclick="window.location.href='guest_main.jsp'">Back</button>
    </div>
</body>
</html>
