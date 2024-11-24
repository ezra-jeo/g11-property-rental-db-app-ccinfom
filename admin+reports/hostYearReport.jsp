<%-- 
    Document   : hostYearReport
    Created on : Nov 24, 2024, 4:10:07 AM
    Author     : Elisha
--%>

<%@ page import="property_listing.PeopleReports" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
<head>
    <title>Host Report</title>
</head>
<body>

<h2>Host Report For the Year</h2>

<%
    PeopleReports peopleReport = new PeopleReports();
    String hostYearReport = peopleReport.getHostYear();

    if (hostYearReport != null && !hostYearReport.isEmpty()) {
        String[] lines = hostYearReport.split("\n");
        
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