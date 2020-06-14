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
        <title>Order Items</title>
    </head>
    <body>
        
        <h2 class="">Order Items</h2>
        <form action="admin" method="post" class="allViews">
            <table>
                <thead>
                    <tr>
                        <th>Image</th><th>Product Code</th><th>Product Name</th><th>Catalog Category</th><th>Description</th><th>Price</th><th>Quantity</th> 
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items='${orderItems}' var='item'>
                        <tr class="all">
                            <td><img src="${item.product.imageURL}" alt='Picture of product'/></td>
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
            <input type="submit" name="action" value="Dashboard" />
            <input type="submit" name="action" value="Catalog" />
            <input type="submit" name="action" value="View All Orders" />
            </form>
        
        <footer>&COPY; 2017 JMan's Computer Sales & Service</footer>
    </body>
</html>