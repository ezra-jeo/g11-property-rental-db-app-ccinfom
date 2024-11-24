

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Filter Property List</title>
    </head>
    <body>
        <jsp:useBean id="property" class="propertyRental.Property" scope="session"/>
        <% 
            String v_columnName = request.getParameter("columnName");
            String v_operator = request.getParameter("operator");
            String v_value = request.getParameter("value");
            int status = property.filterProperties(v_columnName, v_operator, v_value);

            if (status == 1) {
                out.println("<h1>Query Result</h1><br>");
                for (Property prop : property.getPropertyList()) {
                   out.println(" <p> " + prop.getInfo() + "</p><br>"); 
                }
                out.println("<h2>Filter Successful</h2><br>"); 
            }
            else {
            out.println("<h2>Filter Unsuccessful</h2><br>");
            }
         %>
        <br><form action="guest_main.jsp" method="POST">
            <button type="submit"> Back </button>
    </body>
</html>
