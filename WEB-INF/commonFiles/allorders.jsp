<%-- 
    Document   : allproducts
    Created on : Nov 4, 2017, 8:31:33 PM
    Author     : Jarrod Norris
    Class      : ITIS 4166-UOL    
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/jmansales/styles/login.css" type="text/css" />
        <title>All Products</title>
    </head>
    <body>
        <h2 class="">All Orders</h2>
        <form action="admin" method="post" class="allViews">
            <table>
                <thead>
                    <tr>
                        <th>Order Number</th><th>Date</th><th>User ID</th><th>Tax Rate</th><th>Total Sale</th><th>Paid</th><th></th> 
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items='${allOrders}' var='order'>
                        <tr class="all">
<!--                            <td><a href="admin?action=getOrderNumber&amp;productCode=">Update</a></td>-->
                            <td><a href="admin?action=listItems&amp;orderNumber=${order.orderNumber}"><c:out value='${order.orderNumber}' /></a></td>
                            <td><c:out value='${order.date}' /></td>
                            <td><c:out value='${order.userID}' /></td>                            
                            <td><c:out value='${order.taxRate}' /></td>
                            <td>$ <c:out value='${order.total}' /></td> 
                            <td><c:out value='${order.paid}' /></td>
<!--                            <td><a href="admin?action=getOrderNumber&amp;productCode=">Delete</a></td>-->
                        </tr>
                    </c:forEach>                 
                </tbody>
            </table>    
            <input type="submit" name="action" value="Dashboard" />
            <input type="submit" name="action" value="Catalog" />
            </form>
        <footer>&COPY; 2017 JMan's Computer Sales & Service</footer>
    </body>
</html>