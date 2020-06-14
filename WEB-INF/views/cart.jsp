<%-- 
    Document   : cart
    Created on : Oct 2, 2017, 11:03:33 PM
    Author     : Jarrod Norris
    Class      : ITIS 4166-UOL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
        <title>Cart</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/jmansales/styles/main.css" type="text/css" />
        <link rel="stylesheet" href="/jmansales/styles/cart.css" type="text/css" />
       
    </head>
    <body>
        
        <%@ include file="/WEB-INF/commonFiles/user-navigation.jsp" %>    
        <%@ include file="/WEB-INF/commonFiles/site-navigation.jsp" %>
        <div class="maincontentCart"><!-- Main content area. -->
            <div id="cartHeading"><h2 class='cartTitle'>Shopping Cart</h2><P>To remove an item, reduce the quantity to zero.</p></div><br>
            <!-- Left side of content area for primary content. -->
            <div class="leftContent">
            <form action="order" method="post">
            <table>
                <thead>
                    <tr>
                        <th>Item #</th><th>Product Name</th><th>Price</th><th>Update Quantity</th><th>Total</th> 
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items='${theShoppingCart.items}' var='items'>
                        <tr>
                            <td><c:out value="${items.product.productCode}" />
                            <input type="hidden" name="productList[]" value="${items.product.productCode}" /> </td>
                            
                            <td><c:out value="${items.product.productName}" /></td>
                            <td><c:out value="${items.product.priceCurrencyFormat}" /></td> 
                            <td><input type="number" name="quantity" value="<c:out value='${items.quantity}' />" min="0" />
                            
                            <td><c:out value="${items.totals}" /></td>
                    </tr>
                    </c:forEach>                 
                </tbody>
                <tfoot>
                    <tr>
                        <th>Sub-Total</th><th></th><th></th><th></th><th><c:out value="${totals}" /></th>
                    </tr>
                </tfoot>            
            </table>                  
                <div id='updateButton'>                    
                        <input type='submit' id='update' class="cartButtons" name='action' value='Update' />
                </div>
                  
                <div id="checkoutButton">                    
                        <input type='submit' id='checkout' class="cartButtons" name='action' value='Select Payment' />
                </div>
            </form>
                <form action='IOUserDB' method="post"> 
                    <input type='submit' name='action' value='Continue Shopping'/>
                </form> 
            
            </div>
            
        </div>
        
    </body>
</html>
