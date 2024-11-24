
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guest Edit</title>
    </head>
    <body>
        <%
            int guestID = Integer.parseInt(request.getParameter("guestID"));
            guest guestRecord = guest.getGuestRecord(guestID);
        %>
        <form action="update_guest.jsp">
            Guest User Name: <input type="text" id="userName" name="userName" value="<%= guestRecord.userName%>" required><br>
            Password: <input type="password" id="password" name="password" value="<%= guestRecord.password%>" required><br><br>
            First Name: <input type="text" id="firstName" name="firstName" value="<%= guestRecord.firstName%>" required><br>
            Last Name: <input type="text" id="lastName" name="lastName" value="<%= guestRecord.lastName%>" required><br>
            Description: <textarea id="longAnswer" name="description" rows="5" cols="50"> <%= guestRecord.description %> </textarea><br>
            Email: <input type="text" id="email" name="email" value="<%= guestRecord.email%>"><br>
            Phone Number: <input type="text" id="phoneNumber" name="phoneNumber" value="<%= guestRecord.phoneNumber%>"><br>
            <input type="hidden" name="guestID" value="<%= guestID%>">
            <input type="submit" value="Save"><br>     
        </form>
        <br><form action="guest_main.jsp" method="POST">
            <input type="hidden" name="guestID" value="<%= guestID%>">
            <button type="submit"> Back </button>
        </form>          
    </body>
</html>
