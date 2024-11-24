<%@ page import="java.util.*, java.sql.*, property_listing.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Host Statistics</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        table, th, td { border: 1px solid black; }
        th, td { padding: 8px; text-align: left; }
    </style>
</head>
<body>
    <h1>Host Statistics</h1>

    <%
        List<String[]> results = Reports.getDataFromDatabase();
    %>

    <table>
        <thead>
            <tr>
                <th>Host ID</th>
                <th>User Name</th>
                <th>Average Rating</th>
                <th>Total Revenue</th>
                <th>Month</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (String[] row : results) {
            %>
                    <tr>
                        <td><%= row[0] %></td>
                        <td><%= row[1] %></td>
                        <td><%= row[2] %></td>
                        <td><%= row[3] %></td>
                        <td><%= row[4] %></td>
                    </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>