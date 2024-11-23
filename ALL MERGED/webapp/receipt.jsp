<%-- 
    Document   : payment
    Created on : Nov 23, 2024, 1:47:48 AM
    Author     : Elisha
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="java.sql.*, java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Receipt</title>
    </head>
    <body>
        <form action = "guest.html">
        <jsp:useBean id ="B" class="Payments" scope="session" />
            <%
                int res_id = request.getParameter("reservation_id");
                String pay_mode = request.getParameter("method_id");
                float pay_amount = request.getParameter("amount_id");


                B.mode = "Payment";
                B.reservation_id = res_id;
                B.mode = pay_mode;
                B.amount = pay_amount;


                int status = B.register_payment();
                if (status == 1) {
                    System.out.println("Payment successful");
                }
                else System.out.println("Payment failed");

               %>
        <div>Payment Receipt</div>
        <p><%= pay_amount %></p> was successfully paid <br>
            <input type="submit" value="Back to Guest">
        </form>
    </body>
</html>
