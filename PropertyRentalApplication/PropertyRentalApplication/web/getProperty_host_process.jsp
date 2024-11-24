

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Generate Property Information</title>
    </head>
    <body>
            <jsp:useBean id="property" class="propertyRental.Property" scope="session"/>
            <h1>View Listing</h1>
            <% 
                String v_hostID = request.getParameter("hostID");
                int status = property.getProperties(v_hostID);
               
                if (status == 1) {
                    out.println("<h1>View Listings</h1><br>");
                    for (Property prop : property.getPropertyList()) {
                       out.println(" <p> " + prop.getInfo() + "</p><br>"); 
                    }
                    out.println("<h2>Information Generated Successfully</h2><br>"); 
                }
                else {
                out.println("<h2>Information Generated Unsuccessfully</h2><br>");
                }
             %>
        <br>
        <a href="host_main.jsp">
            <button type="button">Back</button>
        </a>
    </body>
</html>
