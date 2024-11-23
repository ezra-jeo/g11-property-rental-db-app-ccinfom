<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Host Sign-In</title>
    </head>
    <body>
        <%
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String description = request.getParameter("description");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            
            if (host.checkHostUserName(userName) != 0) {
                host hostRecord = new host(userName, password, firstName, lastName, description, email, phoneNumber);
                hostRecord.createHost();
                session.setAttribute("hostID", hostRecord.hostID);
                response.sendRedirect("host_main.jsp");
            }            
            else {
                out.println("<h2>Sign-In Failed!</h2>");
                out.println("<h2>Existing username already.</h2>");
        %>
                <br><button onclick ="window.location.href='host_sign-in_landing.html'"> Try Again</button>
        <%
            }
        %>
    </body>
</html>
