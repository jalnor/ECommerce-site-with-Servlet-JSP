<%-- 
    Document   : catalog
    Created on : Oct 2, 2017, 10:13:09 PM
    Author     : Jarrod Norris
    Class      : ITIS 4166-UOL
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Catalog</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/jmansales/styles/main.css" type="text/css" />
        <link rel="stylesheet" href="/jmansales/styles/catalog.css" type="text/css" />
    </head> 
    <body>        
        <%@ include file="/WEB-INF/commonFiles/user-navigation.jsp" %>    
        <%@ include file="/WEB-INF/commonFiles/site-navigation.jsp" %>        
        <div class="maincontent"><!-- Main content area. -->
            <h2>Catalog</h2><br>
            <div class='leftContent'>
                <form action='catalog' method='post'>
                    <c:forEach items="${y}" var="y" >                      
                        <p class="catalogHead"><c:out value="${y}" /></p>
                        <ul>
                            <c:forEach items="${t}" var="t">
                                <c:if test="${t.catalogCategory == y}">
                                    <li><a href='catalog?action=displayItem&amp;productCode=${t.productCode}'><c:out value="${t.productName}" /></a></li>
                                </c:if>
                            </c:forEach>
                        </ul><br>                 
                    </c:forEach>
                    <input type='hidden' value='<c:out value="${message}" />' />
                </form>
            </div>            
        </div>      
    </body>
</html>
