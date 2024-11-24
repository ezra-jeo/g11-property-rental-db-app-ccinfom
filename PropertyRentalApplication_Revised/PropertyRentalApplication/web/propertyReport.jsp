<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="propertyRental.PropertyReports" %>

<html>
<head>
    <title>Property Report</title>
</head>
<body>

<h2>Property Report</h2>

<jsp:useBean id="propertyReport" class="propertyRental.PropertyReports" scope="page" />
<%
    String propertyLocationReport = propertyReport.getPropertyReport();
%>


<%
    if (propertyLocationReport != null && !propertyLocationReport.isEmpty()) {
        String[] lines = propertyLocationReport.split("\n");
        for (String line : lines) {
            out.println("<p>" + line + "</p>");
        }
    } else {
        out.println("<p>No data found for the report.</p>");
    }
%>

<form action="admin_landing.html">
    <input type="submit" value="Back to Admin Page">
</form>

</body>
</html>