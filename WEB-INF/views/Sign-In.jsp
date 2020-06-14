<%-- 
    Document   : login
    Created on : Nov 2, 2017, 3:04:48 PM
    Author     : Jarrod Norris
    Class      : ITIS 4166-UOL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/jmansales/styles/userLogin.css" type="text/css" />
        <link rel="stylesheet" href="/jmansales/styles/main.css" type="text/css" />
        <title>Sign-In</title>
    </head>
    <body id="loginBody">
        <%@ include file="/WEB-INF/commonFiles/user-navigation.jsp" %>    
        <%@ include file="/WEB-INF/commonFiles/site-navigation.jsp" %>
       
        <form id="userLogin" name="userLogin" action="IOUserDB" method="post">
            <fieldset>
                <legend>Sign-In</legend>
                <p><c:out value='${message}' /></p>
                <label>Username:</label> 
                <input type="text" name="username" autofocus required /><br /><br />
                <label>Password:</label> 
                <input type="password" name="password" 
                           maxlength="20" placeholder="Enter you password" required /><br />
                <a href="IOUserDB?action=pwordLookUp" id="forgotten">Forgot Password</a><br />
                <input id="sign-in-btn" type="submit" name="action" value="Sign-In" />
                <p><a href="IOUserDB?action=create">Don't have an account?</a></p>
            </fieldset>    
                
            
            
            
            <%-- Need to put update feature for user in another location. --%>
<!--            <p><a href="IOUserDB?action=update">Need to update you information?</a></p>-->
            
        </form>
        <form name="goback" action="IOUserDB" method="post">
            <input type="submit" id="goback" name="action" value="Go Back" />
        </form>
       
        
    </body>
    
</html>
