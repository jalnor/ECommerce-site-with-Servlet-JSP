<%-- 
    Document   : payment
    Created on : Nov 23, 2017, 6:04:37 AM
    Author     : Jnorri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/jmansales/styles/main.css" type="text/css" />
        <link rel="stylesheet" href="/jmansales/styles/cart.css" type="text/css" />
        <title>Payment</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/commonFiles/user-navigation.jsp" %>    
        <%@ include file="/WEB-INF/commonFiles/site-navigation.jsp" %>
        
        <div class="maincontentCart"><!-- Main content area. -->
            <h2 class='cartTitle'>Enter Your Payment Information</h2><br>
            <!-- Left side of content area for primary content. -->
            <div class="leftContent">
            <form action="order" method="post">
                <table>
                    <thead><tr><th></th><th></th></tr></thead>
                    <tbody>
                        <tr>
                            <td><label for="creditCardType">Credit Card Type</label></td>
                            <td><select id="creditCardType" name="creditCardType">
                                    <option value="--">----------------</option>
                                    <option value="AE">American Express</option>
                                    <option value="MC">Master Card</option>
                                    <option value="VC">Visa</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="cardNumber">Card Number</label></td>
                            <td><input type="text" id="cardNumber" name="cardNumber" value="${cardNumber}" minlength="16" maxlength="16" required /></td>
                        </tr>
                        <tr>
                            <td><label for="ExpireDate">Expiration Date (MM/YYYY)</label></td>
                            <td>
                                <select id="month" name="month">
                                    <c:forEach items="${mnth}" var="month">
                                        <option name="month" value="${month}"><c:out value="${month}" /></option> 
                                    </c:forEach>
                                </select>
                            
                                <select id="year" name="year">
                                    <c:forEach items="${yr}" var="year">
                                        <option name="year" value="${year}"><c:out value="${year}" /></option>
                                        </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="cvv">CVV (3-Digit)</label></td>
                            <td><input type="text" id="cvv" name="cvv" value="${cvv}" /></td>
                        </tr>
                    </tbody>
                    <tfoot><tr><th></th><th></th></tr></tfoot>
                </table>
                <p>Your card will be charged a total of: <c:out value="${total}" />.</p>
                <input type="submit" class="cartButtons" name="action" value="Confirm Order" />            
            </div>
        </div>
    </body>
</html>
