
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat, java.util.*, propertyRental.*, java.sql.Date"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm Reservation</title>
    </head>
    <body>
        <%
            int propertyListingID = Integer.parseInt(request.getParameter("propertyListingID"));
            int guestID = Integer.parseInt(request.getParameter("guestID"));
            int hostID = Property.getHostIDProperty(propertyListingID);
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            format.setTimeZone(TimeZone.getTimeZone("UTC"));
            String startDateStr = request.getParameter("startDate").trim();
            java.util.Date startDateUtil = format.parse(startDateStr);
            Date startDate = new Date(startDateUtil.getTime());
            String endDateStr = request.getParameter("endDate").trim();
            java.util.Date endDateUtil = format.parse(endDateStr);
            Date endDate = new Date(endDateUtil.getTime());
            
            if (startDate.before(endDate)) {
                if (reservation.checkAvailableForReservation(propertyListingID, startDate, endDate) == 1) {
                    reservation R = new reservation(guestID, hostID, propertyListingID, startDate, endDate);
                    R.createReservation();
                    session.setAttribute("reservationID", R.reservationID);
                    response.sendRedirect("payment.jsp");
                }
                else {
                   out.println("<h2>Property Unavailable On Specified Dates!</h2>");
            }
                
            }
            else {
                out.println("<h2>Invalid Date/s!</h2>");
            }            
        %>
        <form action="add_reservation.jsp">        
        <input type="hidden" name="guestID" value="<%= guestID%>">
        <button type="submit">Back</button>
    </form>
    </body>
</html>