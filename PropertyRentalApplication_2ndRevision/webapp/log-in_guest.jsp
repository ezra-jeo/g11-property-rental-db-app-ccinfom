
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guest Log-in</title>
    </head>
    <body>
        <%
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            int accountID = guest.checkGuestAccount(userName, password);
            if (accountID != -1) {
                session.setAttribute("guestID", accountID);
                response.sendRedirect("guest_main.jsp");
            } 
            else {
                out.println("<h2>Log-In Failed!</h2>");
                out.println("<h2>Invalid username or password.</h2>");
        %>
                <br><button onclick ="window.location.href='guest_log-in_landing.html'"> Try Again</button>
        <%
            }
        %>
    </body>
</html>

