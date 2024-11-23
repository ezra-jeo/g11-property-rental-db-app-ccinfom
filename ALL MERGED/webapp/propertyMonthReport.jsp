<%-- 
    Document   : propertyMonthReport
    Created on : Nov 24, 2024, 4:14:54 AM
    Author     : Elisha
--%>

<%@ page import="property_listing.PropertyReports" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
<head>
    <title>Property Report</title>
</head>
<body>

<h2>Property Report For the Month</h2>

<%
    PropertyReports propertyReport = new PropertyReports();
    String propertyMonthReport = propertyReport.getPropertyMonth();

    if (propertyMonthReport != null && !propertyMonthReport.isEmpty()) {
        String[] lines = propertyMonthReport.split("\n");
        
        for (String line : lines) {
            out.println("<p>" + line + "</p>");
        }
    } else {
        out.println("<p>No data found for the report.</p>");
    }
%>
<form action="adminView.html">
        <input type="submit" value="Back to Admin Page">
        </form>
</body>
</html>