

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Listing</title>
    </head>
    <body>
        <jsp:useBean id="property" class="propertyRental.Property" scope="session"/>
        <%
            String hostID_input = request.getParameter("hostID");
            if (hostID_input != null && !hostID_input.trim().isEmpty()) {
        %>
            <form method="post" action="deleteListing_process.jsp">
                <input type="hidden" name="hostID" value="<%= hostID_input %>">

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
                <input type="submit" value="Delete Listing">
            </form>
        <%
            } 
            else if (hostID_input == null) {
        %>
            <p>Please enter a Host ID to see the listings.</p>
        <%
            }
        %>
        <br>
        <a href="host_main.jsp">
                <button type="button">Back</button>
        </a>
    </body>
</html>
