<%-- 
    Document   : webmaster
    Created on : Nov 2, 2017, 3:05:31 PM
    Author     : Jarrod Norris
    Class      : ITIS 4166-UOL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/jmansales/styles/login.css" type="text/css" />
        <title>Administration</title>
    </head>
    <body>
        <Header></Header>
        <form id="login" name="login" action="admin" method="post">
            <fieldset>
                <legend>Log-In</legend>
                <p>${messageAdmin}</p>
                <label>Username:</label> 
                <input type="text" name="username" autofocus required /><br /><br />
                <label>Password:</label> 
                <input type="password" name="password" 
                           maxlength="20" placeholder="Enter you password" required />
            
            </fieldset>    
                <input id="sign-in-btn" type="submit" name="action" value="Log-In" />
        </form>
        <form name="goback" action="IOUserDB" method="post">
            <input type="submit" id="goback" name="action" value="Go Back" />
        </form>
        <footer>&COPY; 2017 JMan's Computer Sales & Service</footer>
    </body>
</html>
