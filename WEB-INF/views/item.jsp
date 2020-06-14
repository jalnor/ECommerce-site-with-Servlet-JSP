<%-- 
    Document   : item
    Created on : Oct 2, 2017, 10:13:09 PM
    Author     : Jarrod Norris
    Class      : ITIS 4166-UOL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Item Details</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/jmansales/styles/main.css" type="text/css" />
        <link rel="stylesheet" href="/jmansales/styles/item.css" type="text/css" />
    </head>
    <body>
        
        <%@ include file="/WEB-INF/commonFiles/user-navigation.jsp" %>    
        <%@ include file="/WEB-INF/commonFiles/site-navigation.jsp" %>
        <div class='maincontent'><!-- Main content area. -->
            <div id='productPic'>
                <img id="productImage" src="${p1.imageURL}" alt="${p1.catalogCategory}" />           
            </div>
            <div id='productDetails'>
                <p><b><c:out value="${p1.productName}" /></b><br><c:out value="${p1.catalogCategory}" /><br><c:out value="${p1.price}" /></p><br>
            </div>
            <form action='catalog' method='post'>
                <input type='submit' id='add' name='action' value='Add'/>
                <input type="hidden" name="productCode" value="${p1.productCode}">
            </form>
            <div id='itemDescription'>
                <p><c:out value="${p1.description}" /></p>
            </div>
            <form action='IOProductDB' method="post">
                <input type='submit' name='action' value='Continue Shopping'/>
            </form>
        </div>
        
    </body>
</html>
