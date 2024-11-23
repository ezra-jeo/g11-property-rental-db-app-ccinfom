<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, propertyRental.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Guest Sign-In</title>
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
            
            if (guest.checkGuestUserName(userName) == 0) {
                guest guestRecord = new guest(userName, password, firstName, lastName, description, email, phoneNumber);
                guestRecord.createGuest();
                session.setAttribute("guestID", guestRecord.guestID);
                response.sendRedirect("guest_main.jsp");
            }
            else {
                out.println("<h2>Sign-In Failed!</h2>");
                out.println("<h2>Existing username.</h2>");
        %>
                <br><button onclick ="window.location.href='guest_sign-in_landing.html'"> Try Again</button>
        <%
            }
        %>
    </body>
</html>