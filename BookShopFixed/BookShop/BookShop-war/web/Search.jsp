<%-- 
    Document   : Search
    Created on : Jun 15, 2018, 7:15:41 AM
    Author     : 80549
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <form action="Controller" method="post">
            <div>Search Page</div>
            <table>
                <tr>
                    <td>
                        <label>Key word: </label>
                    </td>
                    <td>
                        <input  name="key" type="text" placeholder="Enter key word">
                    </td>
                </tr>
            </table>
            <input name="page" type="hidden" value="search">
            <input  type="submit" value="Query"/>
        </form>
    </body>
</html>
