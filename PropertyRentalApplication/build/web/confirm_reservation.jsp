
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat, java.util.*, propertyRental.*, import java.sql.Date;"%>
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
            int hostID = Integer.parseInt(request.getParameter("hostID"));
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            String startDateStr = request.getParameter("startDate");
            java.util.Date startDateUtil = format.parse(startDateStr);
            Date startDate = new Date(startDateUtil.getTime());
            String endDateStr = request.getParameter("endDate");
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
        <br><button onclick ="window.location.href='add_reservation_landing.jsp'"> Back </button>
    </body>
</html>