<%-- 
    Document   : error
    Created on : Nov 27, 2017, 1:54:51 PM
    Author     : Jnorri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/jmansales/styles/main.css" type="text/css" />
        <title>Error</title>
    </head>
    <body>
         <%@ include file="/WEB-INF/commonFiles/user-navigation.jsp" %>    
        <%@ include file="/WEB-INF/commonFiles/site-navigation.jsp" %>
        <h1><c:out value="${messageAdmin}" /></h1>
        <p>Click <a href="catalog?action=index">here</a> to continue.</p>
    </body>
</html>
