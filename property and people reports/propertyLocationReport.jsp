<%-- 
    Document   : propertyLocationReport
    Created on : Nov 24, 2024, 4:17:56 AM
    Author     : Elisha
--%>

<%@ page import="property_listing.PropertyReports" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
<head>
    <title>Property Report</title>
</head>
<body>

<h2>Property Location Report</h2>

<%
    PropertyReports propertyReport = new PropertyReports();
    String propertyLocationReport = propertyReport.getLocationReport();

    if (propertyLocationReport != null && !propertyLocationReport.isEmpty()) {
        String[] lines = propertyLocationReport.split("\n");
        
        for (String line : lines) {
            out.println("<p>" + line + "</p>");
        }
    } else {
        out.println("<p>No data found for the report.</p>");
    }
%>
</body>
</html>