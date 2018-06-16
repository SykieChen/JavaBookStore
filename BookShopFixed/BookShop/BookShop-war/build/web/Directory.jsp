<%-- 
    Document   : Directory
    Created on : Jun 15, 2018, 2:51:03 AM
    Author     : sykie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><% session.getAttribute("key"); %> - Directory</title>
    </head>
    <body>
        <form method="post" action="Controller">
        <table>
            <tr><th>Select</th><th>ISBN</th><th>Title</th><th>Author</th><th>Press</th><th>Price</th></tr>
            <% for (List<String> i:(List<List<String>>)session.getAttribute("search_result")) {
                out.println("<tr>");
                out.println("<td><input type=\"checkbox\" name=\"choose\" value=\"" + i.get(0) + "\"></td>");
                for(String c:i){
                    out.println("<td>" + c + "</td>");
                }
                out.println("</tr>");
            }%>
        </table>
        <input type="hidden" name="page" value="directory">
        <input type="submit" name="submit" value="cart">
        <input type="submit" name="submit" value="search">
        
        </form>
    </body>
</html>
