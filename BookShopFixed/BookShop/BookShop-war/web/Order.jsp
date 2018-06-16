<%-- 
    Document   : Order
    Created on : Jun 15, 2018, 6:48:09 AM
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
        <form method="post" action="Controller">
            <table>
                <tr><th>ISBN</th><th>Title</th><th>Price</th><th>Count</th></tr>
                <%  double total = 0;
                    for (List<String> i:(List<List<String>>)session.getAttribute("cart")) {
                    out.println("<tr>");
                    for(String c:i){
                        out.println("<td>" + c + "</td>");
                    }
                    total += (Double.parseDouble(i.get(2)) * Integer.parseInt(i.get(3)));
                    out.println("</tr>");
                }%>
            </table>
            <p><% out.print(total); %> in total price.</p>
            <p>Will be send to: <% out.print(session.getAttribute("address")); %></p>
        
        </form>
    </body>
</html>
