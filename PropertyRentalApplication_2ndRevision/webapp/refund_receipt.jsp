

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="java.sql.*, java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Receipt</title>
    </head>
    <body>
        <form action = "host.html">
        <jsp:useBean id ="B" class="Payments" scope="session" />
            <%
                int res_id = request.getParameter("reservation_id");
                String pay_mode = request.getParameter("method_id");
                float pay_amount = request.getParameter("amount_id");


                B.mode = "Refund";
                B.reservation_id = res_id;
                B.mode = pay_mode;
                B.amount = pay_amount;


                int status = B.register_payment();
                if (status == 1) {
                    System.out.println("Refund successful");
                }
                else System.out.println("Refund failed");

               %>
        <div>Payment Receipt</div>
        <p><%= pay_amount %></p> was successfully refunded <br>
            <input type="submit" value="Back to Host">
        </form>
    </body>
</html>
