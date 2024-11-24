

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, propertyRental.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Get Ratings Given Host ID</title>
    </head>
    <body>
        <h1>View Host Ratings</h1>
        <form action="viewHostRating_process.jsp" method="get">
            Select Host:
            <select id="hostID" name="hostID" required>
                <%
                    try {
                        // Call the static getHostRecords method directly
                        ArrayList<host> hostList = host.getHostRecords();

                        // Dynamically populate the dropdown
                        for (host h : hostList) {
                %>
                            <option value="<%= h.hostID %>"><%= h.userName %></option>
                <%
                        }
                    } 
                    catch (Exception e) {
                        out.println("<option disabled>Error fetching host data</option>");
                        e.printStackTrace();
                    }
                %>
            </select><br><br>
            <input type="submit" value="Submit">
        </form><br>
        <a href="guest_main.jsp">
                <button type="button">Back</button>
        </a>
    </body>
</html>
