<%-- 
    Document   : updateListing
    Created on : Nov 23, 2024, 2:57:27 PM
    Author     : Ezra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, property_listing.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Listing</title>
    </head>
    <body>
        <jsp:useBean id="property" class="property_listing.Property" scope="session"/>
        <form method="get" action="updateListing.jsp">
            <h1>Update Listing</h1>
            Enter Host ID: <input type="text" id="host_ID" name="host_ID" value="<%= request.getParameter("host_ID") != null ? request.getParameter("host_ID") : "" %>" required>
            <button type="submit">Get Listings</button>
        </form>

        <%
            String hostID_input = request.getParameter("host_ID");
            if (hostID_input != null && !hostID_input.trim().isEmpty()) {
        %>
            <form method="post" action="updateListing_process.jsp">
                <input type="hidden" name="host_ID" value="<%= hostID_input %>">

                <select id="propertyListingID" name="propertyListingID" required>
                    <%
                        property.filterProperties("hostID", "=", hostID_input); 
                        for (Property prop : property.getPropertyList()) {
                    %>
                        <option value="<%= prop.getID() %>"><%=prop.getID()%> : <%= prop.getListingName() %></option>
                    <%
                        }
                    %>
                </select><br><br>
                
                Enter Host ID: <input type="text" id="host_ID" name="host_ID" value="<%= property.getHostID() %>" required><br><br>
                Enter Listing Name: <input type="text" id="listingName" name="listingName" value="<%= property.getListingName() %>" required><br><br>
                Enter Description: <textarea id="description" name="description" rows="4" cols="50" required><%= property.getDescription() %></textarea><br><br>
                Enter Price Per Night: <input type="number" id="pricePerNight" name="pricePerNight" step="0.01" value="<%= property.getPricePerNight() %>" required><br><br>
                Enter Street: <input type="text" id="street" name="street" value="<%= property.getStreet() %>" required><br><br>
                Enter City: <input type="text" id="city" name="city" value="<%= property.getCity() %>" required><br><br>
                Enter Province: <input type="text" id="province" name="province" value="<%= property.getProvince() %>" required><br><br>
                Enter Country: <input type="text" id="country" name="country" value="<%= property.getCountry() %>" required><br><br>

                Enter Status: 
                <select id="status" name="status" required>
                    <option value="Available" <%= property.getStatus().equals("Available") ? "selected" : "" %>>Available</option>
                    <option value="Unavailable" <%= property.getStatus().equals("Unavailable") ? "selected" : "" %>>Unavailable</option>
                </select><br><br>

                <input type="submit" value="Update Listing">
            </form>
        <%
            } 
            else if (hostID_input == null) {
        %>
            <p>Please enter a Host ID to see the listings.</p>
        <%
            }
        %>
    </body>
</html>
