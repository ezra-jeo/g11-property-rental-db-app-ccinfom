<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="java.sql.*, java.util.*"%>

<html>
    <title>Payment Transaction</title>
    <body>
        <form action = "receipt.jsp">
            Input Reservation ID: <input type="text" id="reservation_id" name="reservation_id"><br>
            Select Mode of Payment: <select id ="method_id" name="method_id"><br>
                                            <option value ="1">Bank Transfer</option>
                                            <option value ="2">Debit Card</option>
                                            <option value ="3">PayPal</option>
                                            <option value ="4">Credit Card  </option>
                                    </select><br>
            Input Amount: <input type="text" id ="amount_id" name="amount_id"><br>
            <input type="submit" value="Confirm Payment">
        </form>
    </body>
</html>
