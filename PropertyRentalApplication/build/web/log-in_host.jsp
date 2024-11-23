<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Host Log-in</title>
    </head>
    <body>
        <%
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            int accountID = host.checkHostAccount(userName, password);
            if (accountID != -1) {
                session.setAttribute("hostID", accountID);
                response.sendRedirect("host_main.jsp");
            } 
            else {
                out.println("<h2>Log-In Failed!</h2>");
                out.println("<h2>Invalid username or password.</h2>");
        %>
                <br><button onclick ="window.location.href='host_log-in_landing.html'"> Try Again</button>
        <%
            }
        %>
    </body>
</html>
