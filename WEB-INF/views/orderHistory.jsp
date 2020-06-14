<%-- 
    Document   : orderHistory
    Created on : Nov 10, 2017, 9:52:06 AM
    Author     : Jarrod Norris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Order History</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/jmansales/styles/main.css" type="text/css" />
        <link rel="stylesheet" href="/jmansales/styles/order.css" type="text/css" />
        <link rel="stylesheet" href="/jmansales/styles/history.css" type="text/css" />
    </head>
    <body>
        
       <%@ include file="/WEB-INF/commonFiles/user-navigation.jsp" %>    
        <%@ include file="/WEB-INF/commonFiles/site-navigation.jsp" %>
        <div class="maincontentCart"><!-- Main content area. -->
            <div class="cartHeading">
                <h2 class='invoice'>Order History</h2>
            </div>
            <h3>Customer: <c:out value="${theUser.firstName} ${theUser.lastName}" /></h3>
            <div class="autoResize">
                <table>
                    <thead>
                        <tr>
                            <th>Order Number</th><th>Date/Time</th><th>Tax Rate</th><th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${orders}" var="order"> 
                            <tr>
                                <td><a href="order?action=listItems&amp;orderNumber=${order.orderNumber}"><c:out value="${order.orderNumber}" /></a></td>
                                <td><c:out value="${order.date}" /></td><td><c:out value="${order.taxRate * .01}" /></td><td>$ <c:out value="${order.total}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr><th></th><th></th><th></th><th></th></tr>
                    </tfoot>
                </table>
            </div>
        </div>
    </body>
</html>
