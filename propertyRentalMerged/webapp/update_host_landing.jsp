
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Host Edit</title>
    </head>
    <body>
        <%
            int hostID = Integer.parseInt(request.getParameter("hostID"));
            host hostRecord = host.getHostRecord(hostID);
        %>
        <form action="update_host.jsp">
            Host User Name: <input type="text" id="userName" name="userName" value="<%= hostRecord.userName%>" required><br>
            Password: <input type="password" id="password" name="password" value="<%= hostRecord.password%>" required><br><br>
            First Name: <input type="text" id="firstName" name="firstName" value="<%= hostRecord.firstName%>" required><br>
            Last Name: <input type="text" id="lastName" name="lastName" value="<%= hostRecord.lastName%>" required><br>
            Description: <textarea id="longAnswer" name="description" rows="5" cols="50"> <%= hostRecord.description %> </textarea><br>
            Description: <textarea id="longAnswer" name="description" rows="5" cols="50" value="<%= hostRecord.description%>"></textarea><br>
            Email: <input type="text" id="email" name="email" value="<%= hostRecord.email%>"><br>
            Phone Number: <input type="text" id="phoneNumber" name="phoneNumber" value="<%= hostRecord.phoneNumber%>"><br>
            <input type="hidden" name="hostID" value="<%= hostID%>">
            <input type="submit" value="Save"><br>     
        </form>
        <br><form action="host_main.jsp" method="POST">
            <input type="hidden" name="hostID" value="<%= hostID%>">
            <button type="submit"> Back </button>
        </form>          
    </body>
</html>
