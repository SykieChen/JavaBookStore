<%-- 
    Document   : Cart
    Created on : Jun 15, 2018, 5:21:18 AM
    Author     : sykie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <h1>Shopping cart</h1>
        <form method="post" action="Controller">
            <table border="1">
                <tr><th>ISBN</th><th>Title</th><th>Price</th><th>Count</th></tr>
                <% for (List<String> i:(List<List<String>>)session.getAttribute("cart")) {
                    out.println("<tr>");
                    for(String c:i){
                        if (c!=i.get(i.size()-1)){
                            out.println("<td>" + c + "</td>");
                        }
                        else{
                            out.println("<td><input type=\"text\" name=\"" + i.get(0) + "\" value=\"" + c + "\"></td>");
                        }
                    }
                    out.println("</tr>");
                }%>
            </table>
            <input type="text" name="address" placeholder="Enter your address here.">
            <input type="hidden" name="page" value="cart">
            <input type="submit" name="submit" value="order">
            <input type="submit" name="submit" value="return">
            <input type="submit" name="submit" value="search">
        
        </form>
    </body>
</html>
