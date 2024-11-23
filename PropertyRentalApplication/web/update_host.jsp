
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
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String description = request.getParameter("description");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            
            host hostRecord = host.getHostRecord(hostID);

            if ((host.checkHostUserName(userName) == 1 && userName.equalsIgnoreCase(hostRecord.userName)) || 
                host.checkHostUserName(userName) == 0) {
                host.updateHostRecord(hostID, userName, password, firstName, lastName, description, email, phoneNumber);
                session.setAttribute("hostID", hostID);
                response.sendRedirect("host_main.jsp");
            }            
            else {
                out.println(host.checkHostUserName(userName) == 1);
                out.println("<h2>Edit Failed!</h2>");
                out.println("<h2>Existing username already.</h2>"); 
            }
        %>
        <br><form action="update_host_landing.jsp" method="POST">
            <input type="hidden" name="hostID" value="<%= hostID%>">
            <button type="submit"> Try Again</button>
        </form>    
    </body>
</html>
