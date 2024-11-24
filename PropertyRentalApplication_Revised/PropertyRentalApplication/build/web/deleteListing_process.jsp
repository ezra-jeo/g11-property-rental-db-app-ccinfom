

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Delete Listing</title>
    </head>
    <body>
        <jsp:useBean id="property" class="propertyRental.Property" scope="session"/>
        <% 
                int v_hostID = Integer.parseInt(request.getParameter("host_ID"));
                int v_propertyID = Integer.parseInt(request.getParameter("propertyListingID")); 
               
                int status = property.deleteListing(v_hostID, v_propertyID);

                if (status == 1) {
                    out.println("<h2>Delete Successful</h2><br>"); 
                }
                else {
                    out.println("<h2>Delete Unsuccessful</h2><br>");
                }
        %>
        <br><form action="host_main.jsp" method="POST">
            <button type="submit"> Back </button>
    </body>
</html>
