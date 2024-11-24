
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="propertyRental.PeopleReports" %>

<html>
<head>
    <title>Guest Report</title>
</head>
<body>

<h2>Guest Report</h2>

<jsp:useBean id="peopleReport" class="propertyRental.PeopleReports" scope="page" />
<%
    String peopleReports = peopleReport.getGuestReport();
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