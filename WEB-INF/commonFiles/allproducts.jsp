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
        <form action="admin" method="post" class="allViews">
            <table>
                <thead>
                    <tr>
                        <th></th><th>Product Code</th><th>Product Name</th><th>Catalog Category</th><th>Description</th><th>Price</th><th>Image URL</th><th></th> 
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items='${products}' var='product'>
                        <tr class="all">
                            <td><a href="IOProductDB?action=getProduct&amp;productCode=${product.productCode}">Update</a></td>
                            <td><c:out value='${product.productCode}' /></td>
                            <td><c:out value='${product.productName}' /></td>
                            <td><c:out value='${product.catalogCategory}' /></td>
                            <td><c:out value='${product.description}' /></td>
                            <td><c:out value='${product.price}' /></td> 
                            <td><c:out value='${product.imageURL}' /></td>
                            <td><a href="IOProductDB?action=getProduct&amp;productCode=${product.productCode}">Delete</a></td>
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