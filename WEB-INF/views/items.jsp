<%-- 
    Document   : items
    Created on : Nov 25, 2017, 10:05:28 PM
    Author     : Jnorri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Items</title>
        <link rel="stylesheet" href="/jmansales/styles/main.css" type="text/css" />
        <link rel="stylesheet" href="/jmansales/styles/order.css" type="text/css" />
        <link rel="stylesheet" href="/jmansales/styles/cart.css" type="text/css" />
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
                           
                    <form action="order" method="post" class="allViews">
                        <div class="autoResize"> 
                        <table>
                            <thead>
                                <tr>
                                    <th>Image</th><th>Product Code</th><th>Product Name</th><th>Catalog Category</th><th>Description</th><th>Price</th><th>Quantity</th> 
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items='${orderItems}' var='item'>
                                    <tr class="all">
                                        <td><img class="itemImage" src="${item.product.imageURL}" alt='Picture of product'/></td>
                                        <td><c:out value='${item.product.productCode}' /></td>
                                        <td><c:out value='${item.product.productName}' /></td>
                                        <td><c:out value='${item.product.catalogCategory}' /></td>
                                        <td><c:out value='${item.product.description}' /></td>
                                        <td><c:out value='${item.product.price}' /></td> 
                                        <td><c:out value='${item.quantity}' /></td>                            
                                    </tr>
                                </c:forEach>                 
                            </tbody>
                        </table>
                        </div><br /> 
                        <input type="submit" name="action" value="History" />
                    </form>
                               
            </div>
        </div>
    </body>
</html>
