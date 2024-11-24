<%-- 
    Document   : guestMonthReport
    Created on : Nov 24, 2024, 4:12:27 AM
    Author     : Elisha
--%>

<%@ page import="property_listing.PeopleReports" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<html>
<head>
    <title>Guest Report</title>
</head>
<body>

<h2>Guest Report For the Year</h2>

<%
    PeopleReports peopleReport = new PeopleReports();
    String guestYearReport = peopleReport.getGuestYear();

    if (guestYearReport != null && !guestYearReport.isEmpty()) {
        String[] lines = guestYearReport.split("\n");
        
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