<%-- 
    Document   : order
    Created on : Oct 2, 2017, 10:13:09 PM
    Author     : Jarrod Norris
    Class      : ITIS 4166-UOL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Checkout</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/jmansales/styles/main.css" type="text/css" />
        <link rel="stylesheet" href="/jmansales/styles/cart.css" type="text/css" />
        <link rel="stylesheet" href="/jmansales/styles/order.css" type="text/css" />
    </head>
    <body>
        
        <%@ include file="/WEB-INF/commonFiles/user-navigation.jsp" %>    
        <%@ include file="/WEB-INF/commonFiles/site-navigation.jsp" %>
        <div class="maincontentCart"><!-- Main content area. -->
            <div class="cartHeading">
                <h2 class='invoice'>Invoice</h2><br> 
            </div>
            <!-- Left side of content area for primary content. -->
            <div class="leftContent">
                <h3 id="ship">Ship/Bill To:</h3><br><br>
                <form id="billingAddr" name="biliingAddr">
                    <label for="name" >Name:</label>
                        <input type="text" id="name" name="name" value='<c:out value="${o.user.firstName} ${o.user.lastName}" />' readonly />
                    <label for="addr1">Address1:</label>
                        <input type="text" id="addr1" name="addr1" value="<c:out value="${o.user.addr1}" />" readonly />
                    <label for="addr2">Address2:</label>
                        <input type="text" id="addr2" name="addr2" value="<c:out value="${o.user.addr2}" />" readonly />
                    <label for="cityStateRegionZip">City/State/Zipcode:</label>
                        <input type="text" id="cityStateRegionZip" name="cityStateRegionZip" value='<c:out value="${o.user.city}," /><c:out value="${o.user.stateRegion}" /> <c:out value="${o.user.zipcode}" />' readonly />
                    <label for="origin">Origin/Country:</label>
                        <input type="text" id="origin" name="origin" value='<c:out value="${o.user.country}" />' readonly /> 
                    <label for="paymentType">Payment:</label>
                        <input type="text" id="paymentType" name="paymentType" value='<c:out value="${o.user.paymentType.cardNumber}" />' readonly /><img src="${cardImage}" alt="${cardText}" /> 
                    <br>
                </form>
            <form action="order" method="post">
            <table>
                <thead>
                    <tr>
                        <th>Item #</th><th>Product Name</th><th>Price</th><th>Update Quantity</th><th>Total</th> 
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${o.items}" var="item">
                        <tr>
                            <td><c:out value='${item.product.productCode}' /></td>
                            <td><c:out value='${item.product.productName}' /></td>
                            <td><c:out value='${item.product.priceCurrencyFormat}' /></td>
                            <td><c:out value='${item.quantity}' /></td>                                
                            <td><c:out value='${item.totals}' /></td>
                        </tr>
                    </c:forEach>                      
                </tbody>
                <tfoot>
                    <tr>
                        <th></th><th></th><th></th><th></th><th></th>
                    </tr>
                    <tr>
                        <td></td><td></td><td></td><td>Sub-Total</td><td>${totals}</td>
                    </tr>
                    <tr>
                        <td></td><td></td><td></td><td>Tax</td><td>${tax}</td>
                    </tr>
                    <tr>
                        <td></td><td></td><td></td><td>Total</td><td>${total}</td>
                    </tr>
                </tfoot>                
            </table>
                    
                </form>
                    <div id='updateButton'> 
                        <form action="order" method="post">
                            <input type='submit' id='update' class="cartButtons" name='action' value='Back To Cart' />                            
                        </form>
                    </div>
                    <div id="checkoutButton">
                        <p>Please confirm your information before purchasing!</p>
                        <form action="order" method="post">
                            <input type='submit' id='checkout' class="cartButtons" name='action' value='Purchase' />
                        </form>
                    </div>
                  
                </div>
            </div>
        
    </body>
</html>
