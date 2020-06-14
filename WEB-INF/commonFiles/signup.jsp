<%-- 
    Document   : signup
    Created on : Nov 3, 2017, 7:26:40 PM
    Author     : Jarrod Norris
    Class      : ITIS 4166-UOL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/jmansales/styles/login.css" type="text/css" />
        <title>Sign Up</title>
    </head>
    <body>
        <form id="prod" action="IOUserDB" method="post">
            <fieldset>
                <legend>Account Information</legend>
                    <input type="hidden" id="userID" name="userID" value="${theUser.userID}" />
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" value="${theUser.username}" autofocus required />
                    
                    <label for="password">Password:</label>
                    <input type="password" id="password"  name="password" value="${theUser.password}" placeholder="minimum of 5 characters" required />
                    
                    <label for="firstName">First Name:</label>
                    <input type="text" id="firstName" name="firstName" value="${theUser.firstName}" required />
                    
                    <label for="lastName">Last Name:</label>
                    <input type="text" id="lastName" name="lastName" value="${theUser.lastName}" required />
                    
                    <label for="email">Email:</label>
                    <input type="text" id="email" name="email" value="${theUser.email}" placeholder="must have valid email addr" required />
                    
                    <label for="addr1">Address 1:</label>
                    <input type="text" id="addr1" name="addr1" value="${theUser.addr1}" required />
                    
                    <label for="addr2">Address 2</label>
                    <input type="text" id="addr2" name="addr2" value="${theUser.addr2}" required />
                    
                    <label for="city">City</label>
                    <input type="text" id="city" name="city" value="${theUser.city}" required /> 
                    
                    <label for="stateRegion">State/Region</label>
                    <input type="text" id="stateRegion" name="stateRegion" value="${theUser.stateRegion}" required />
                    
                    <label for="zipcode">Zipcode</label>
                    <input type="text" id="zipcode" name="zipcode" value="${theUser.zipcode}" placeholder="5 or 5+4 format" required />
                    
                    <label for="country">Country</label>
                    <input type="text" id="country" name="country" value="${theUser.country}" required />
                    
                    <label for="securityQuestion">Password Hint</label>
                    <input type="hidden" id="userID" name="userID" value="<c:out value='${fav.userID}' />" /> 
                    <select id="securityQuestion" name="securityQuestion">
                        <option value="${fav.question}"></option>
                        <option value="wyfc">What's your favorite color?</option>
                        <option value="wyfa">What's your favorite automobile?</option>
                        <option value="wyfp">What's your favorite planet?</option>
                    </select>
                    <input type='text' id="answer" name="answer" value="${fav.answer}" required />
                    
            </fieldset>
                     <input type="submit" name="action" value="Create Account" />
                     <input type="submit" name="action" value="Edit Account" />
        </form>
        <form name="goback" action="IOUserDB" method="post">
            <input type="submit" id="goback" name="action" value="Go Back" />
        </form>
        <footer>&COPY; 2017 JMan's Computer Sales & Service</footer>
    </body>
</html>
