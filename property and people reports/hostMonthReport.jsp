<%-- 
    Document   : hostMonthReport
    Created on : Nov 24, 2024, 4:05:34 AM
    Author     : Elisha
--%>

<%@ page import="property_listing.PeopleReports" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
<head>
    <title>Host Report</title>
</head>
<body>

<h2>Host Report For the Month</h2>

<%
    PeopleReports peopleReport = new PeopleReports();
    String hostMonthReport = peopleReport.getHostMonth();

    if (hostMonthReport != null && !hostMonthReport.isEmpty()) {
        String[] lines = hostMonthReport.split("\n");
        
        for (String line : lines) {
            out.println("<p>" + line + "</p>");
        }
    } else {
        out.println("<p>No data found for the report.</p>");
    }
%>
</body>
</html>