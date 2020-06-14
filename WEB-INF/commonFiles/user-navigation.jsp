<%-- 
    Document   : user-navigation
    Created on : Oct 2, 2017, 10:13:09 PM
    Author     : Jarrod Norris
    Class      : ITIS 4166-UOL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="usernav"> <!-- User specific navigation links. -->
     <ul class="usernav1">
    <%-- Need to think about this some more! --%>
        <li class="usernav" id="login">
            <c:choose>
                <c:when test="${!(theUser.firstName == null)}">
                    <a href='catalog?action=Sign-Out'>Sign Out</a>
                </c:when>
                <c:when test="${theUser.firstName == null}">
                    <a href='catalog?action=Sign-In'>Sign In</a>
                </c:when>
            </c:choose>
        </li>
        <li class="usernav">
            <c:choose>
                <c:when test="${!(theUser.firstName == null)}">
                    <a href='order?action=History'>Order History</a>
                </c:when>
                <c:when test="${theUser.firstName == null}">
                    <a href='catalog?action=Sign-In'>Order History</a>
                </c:when>
            </c:choose>
        </li>
            
        <li class="usernav"><a href='order?action=Back To Cart'>Cart</a></li>
    </ul>
    
</div>
  