

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Listing</title>
    </head>
    <body>
        <jsp:useBean id="property" class="propertyRental.Property" scope="session"/>
        <h1>Update Listing</h1>
        
        <%
            String hostID_input = request.getParameter("hostID");
            if (hostID_input != null && !hostID_input.trim().isEmpty()) {
        %>

            <!-- Step 2: Display listings for the selected Host -->
            <form method="post" action="updateListing.jsp">
                <input type="hidden" name="hostID" value="<%= hostID_input %>">

                <select id="propertyListingID" name="propertyListingID" required>
                    <%
                        property.filterProperties("hostID", "=", hostID_input);
                        for (Property prop : property.getPropertyList()) {
                    %>
                        <option value="<%= prop.getID() %>" <%= (request.getParameter("propertyListingID") != null && request.getParameter("propertyListingID").equals(String.valueOf(prop.getID()))) ? "selected" : "" %>>
                            <%= prop.getID() %> : <%= prop.getListingName() %>
                        </option>
                    <%
                        }
                    %>
                </select><br><br>

                <button type="submit">Confirm Listing</button>
            </form>

            <%
                String selectedPropertyID = request.getParameter("propertyListingID");
                if (selectedPropertyID != null) {
                    // Fetch the property details based on the selected propertyID
                    property.adaptListing(Integer.parseInt(selectedPropertyID));
            %>

            <!-- Step 3: Display pre-filled form for editing -->
            <form method="post" action="updateListing_process.jsp">
                <input type="hidden" name="hostID" value="<%= hostID_input %>">
                <input type="hidden" name="propertyListingID" value="<%= property.getID() %>">

                Listing Name: <input type="text" id="listingName" name="listingName" value="<%= property.getListingName() %>" required><br><br>
                Description: <textarea id="description" name="description" rows="4" cols="50" required><%= property.getDescription() %></textarea><br><br>
                Price Per Night: <input type="number" id="pricePerNight" name="pricePerNight" step="0.01" value="<%= property.getPricePerNight() %>" required><br><br>
                Street: <input type="text" id="street" name="street" value="<%= property.getStreet() %>" required><br><br>
                City: <input type="text" id="city" name="city" value="<%= property.getCity() %>" required><br><br>
                Province: <input type="text" id="province" name="province" value="<%= property.getProvince() %>" required><br><br>
                Country: <input type="text" id="country" name="country" value="<%= property.getCountry() %>" required><br><br>

                Status: 
                <select id="status" name="status" required>
                    <option value="Available" <%= property.getStatus().equals("Available") ? "selected" : "" %>>Available</option>
                    <option value="Unavailable" <%= property.getStatus().equals("Unavailable") ? "selected" : "" %>>Unavailable</option>
                </select><br><br>

                <input type="submit" value="Update Listing">
            <%
                }
            }
        %>
            </form><br>
            <a href="host_main.jsp">
                    <button type="button">Back</button>
            </a>
    </body>
</html>
