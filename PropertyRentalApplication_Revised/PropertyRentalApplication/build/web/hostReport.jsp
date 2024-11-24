<%-- 
    Document   : hostReport
    Created on : Nov 24, 2024, 4:10:07 AM
    Author     : Elisha
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="propertyRental.PeopleReports" %>

<html>
<head>
    <title>Host Report</title>
</head>
<body>

<h2>Host Report</h2>

<jsp:useBean id="peopleReport" class="propertyRental.PeopleReports" scope="page" />
<%
    String peopleReports = peopleReport.getHostReport();
%>

<%
    if (peopleReports != null && !peopleReports.isEmpty()) {
        String[] lines = peopleReports.split("\n");
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