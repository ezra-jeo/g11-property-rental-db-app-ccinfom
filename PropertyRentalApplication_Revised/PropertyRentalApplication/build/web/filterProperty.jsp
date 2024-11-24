

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Filter The Property Listing Table</title>
    </head>
    <body>
        <form action="filterProperty_process.jsp">
            Enter column name: <input type="text" id="columnName" name="columnName"><br>
            Enter operator for query statement: <input type="text" id="operator" name="operator"><br>
            Enter operand for query statement: <input type="text" id="value" name="value"><br>


            <input type="submit" value="submit">
        </form>
    </body>
</html>
