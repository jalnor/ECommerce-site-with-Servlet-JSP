<%-- 
    Document   : index
    Created on : Oct 2, 2017, 10:13:09 PM
    Author     : Jarrod Norris
    Class      : ITIS 4166-UOL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<!DOCTYPE html>
<html>
    <head>
        <title>JMan's Computer Sales & Service</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/main.css" type="text/css" /><!--media="screen and (min-width:1200px) and (max-width: 2500px)"--> 
    </head>
    <body>        
        <%--@ include file="common_files/header.jsp" --%>
        <%@ include file="/WEB-INF/commonFiles/user-navigation.jsp" %>  
        <%@ include file="/WEB-INF/commonFiles/site-navigation.jsp" %>
        <div class="maincontent"><!-- Main content area. -->
            <h2>Welcome to JMan's Computer Superstore!</h2>
            <!-- Left side of content area for primary content. -->
            <div class='leftContent'><p>Whether you are looking for a computer for your:</p><br><br> 
                <!-- Calls catalog servlet with action loadCatalog to load data. -->
                <div class='options'>home<a href="IOProductDB?action=loadCatalog">
                        <img class='laptop' src='images/ASUS.png' alt='Gaming Laptop'></a></div>
                        <!-- Calls catalog servlet with action loadCatalog to load data. -->
                        <div class='options'>work<a href="IOProductDB?action=loadCatalog">
                                <img class='laptop' src='images/MacBookPro.png' alt='business laptop'></a></div>
                <div class='options'>or school</div>
                <!-- An additional link to catalog page. -->
                <p><a href='IOProductDB?action=loadCatalog'>Here</a> is where you 
                    will find what you need.</p>
<!--                <p><a href="?action=insert">Insert</a></p>-->
            </div>
            <!-- This area is for future revenue! -->
            <div class='rightContent'><a href="administrator">Test!</a></div>
        </div>
        <%--@ include file="/common_files/footer.jsp" --%>
    </body>
</html>
