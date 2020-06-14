<%-- 
    Document   : header
    Created on : Oct 2, 2017, 2:51:54 PM
    Author     : Jarrod Norris
    Class      : ITIS 4166-UOL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header><!-- Main header for each page. -->
    
    <div id="Logo"><a href='index.jsp'>
    <h1 id="Logo1">JMan's Computer Sales & Service</h1></a></div>
    
        <div id="status"> 
            <c:choose>
                <c:when test="${!(theUser.firstName == null)}">
                    <span>Welcome: <c:out value="${theUser.firstName}" /></span>
                </c:when>
                <c:when test="${!(theAdminUser == null)}">
                    <span>Welcome: <c:out value="${theAdminUser.firstName}" /></span>
                </c:when>
                <c:when test="${theUser.firstName == null}">
                    <span>Not Logged In</span>
                </c:when>
            </c:choose>
        </div>
   
        <div id="location">            
            Home <c:out value="${location}" />
        </div><!-- Breadcrumb placeholder. -->
</header>
 
