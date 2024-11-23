
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
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String description = request.getParameter("description");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            
            guest guestRecord = guest.getGuestRecord(guestID);

            if ((guest.checkGuestUserName(userName) == 1 && userName.equalsIgnoreCase(guestRecord.userName)) || 
                guest.checkGuestUserName(userName) == 0) {
                guest.updateGuestRecord(guestID, userName, password, firstName, lastName, description, email, phoneNumber);
                session.setAttribute("hostID", guestID);
                response.sendRedirect("guest_main.jsp");
            }            
            else {
                out.println(host.checkHostUserName(userName) == 1);
                out.println("<h2>Edit Failed!</h2>");
                out.println("<h2>Existing username already.</h2>"); 
            }
        %>
        <br><form action="update_guest_landing.jsp" method="POST">
            <input type="hidden" name="guestID" value="<%= guestID%>">
            <button type="submit"> Try Again</button>
        </form>    
    </body>
</html>
