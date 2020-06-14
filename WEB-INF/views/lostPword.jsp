<%-- 
    Document   : lostPword
    Created on : Dec 9, 2017, 7:41:47 PM
    Author     : Jnorri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/jmansales/styles/main.css" type="text/css" />
        <title>Help</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/commonFiles/user-navigation.jsp" %>    
        <%@ include file="/WEB-INF/commonFiles/site-navigation.jsp" %>
        
        <h1>Security Question</h1>
        <form action="IOUserDB" method="post">
            
            <label for="userName">User Name</label>
            <input type="text" id="userName" name="userName" focus />
                <select id="securityQuestion" name="securityQuestion">
                    <option value="wyfc">What's your favorite color?</option>
                    <option value="wyfa">What's your favorite automobile?</option>
                    <option value="wyfp">What's your favorite planet?</option>
                </select>
            <input type='text' id="answer" name="answer" required />
            <input type="submit" name="action" value="getHint" />
<!--            <label for="securityQuestion">Password</label>
            <input type="text" id="securityQuestion" name="securityQuestion" /><br />-->
            <p>
                
                    <c:if test='${forgotten != null}'>
                        <span><c:out value="${forgotten}" /></span>
                    </c:if>
                
                    
            </p> 
        </form>
    </body>
</html>
