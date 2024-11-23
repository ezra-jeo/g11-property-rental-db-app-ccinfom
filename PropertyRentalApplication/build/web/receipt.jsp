
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="java.sql.*, java.util.*, propertyRental.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Receipt</title>
    </head>
    <body>
        <%
            int reservationID = Integer.parseInt(request.getParameter("reservationID"));
            reservation reservationRecord = reservation.getReservationRecord(reservationID);
            String pay_mode = request.getParameter("method_id");
            float pay_amount = Float.parseFloat(request.getParameter("amount_id"));
            
            if (pay_amount >= reservationRecord.totalPrice) {
                Payments B = new Payments();
                B.reservation_id = reservationID;
                B.mode = pay_mode;
                B.amount = pay_amount;
                int status = B.register_payment();
                response.sendRedirect("guest_main.jsp");
            }
            else {
                out.println("<h2>Insufficient Payment!</h2>");
            }
        %>
        </form>
            <br> <form action="payment.jsp" method="POST">
            <input type="hidden" name="reservationID" value="<%= reservationRecord.reservationID%>">;
            <button type="submit"> Try Again </button>     
            </form>
    </body>
</html>


