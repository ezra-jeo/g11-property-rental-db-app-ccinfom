<%-- 
    Document   : generate_property_process
    Created on : Nov 22, 2024, 7:45:27 PM
    Author     : Ezra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, property_listing.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Generate Property Report</title>
    </head>
    <body>
            <jsp:useBean id="property" class="property_listing.Property" scope="request"/>
            <% 
                String v_hostName = request.getParameter("host_name");
                int status = property.getProperties(v_hostName);
                out.println("<p>" + v_hostName + "</p>");
                if (status == 1) {
                    for (String info : property.getInfoList()) {
                       out.println(" <p> " + info + "</p><br>"); 
                    }
                    out.println("<h1>Report Generated Successfully</h1><br>"); 
                }
                else {
                out.println("<h1>Report Generated Unsuccessfully</h1><br>");
                }
             %>

    </body>
</html>
