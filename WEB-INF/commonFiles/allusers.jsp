<%-- 
    Document   : allusers
    Created on : Nov 4, 2017, 8:40:53 PM
    Author     : Jarrod Norris
    Class      : ITIS 4166-UOL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/jmansales/styles/login.css" type="text/css" />
        <title>All Users</title>
    </head>
    <body>
        <form action="admin" method="post" class="allViews">
            <table>
                <thead>
                    <tr>
                        <th></th><th>User ID</th><th>First Name</th><th>Last Name</th>
                        <th>Email</th><th>Address 1</th><th>Address 2</th><th>City</th>
                        <th>State</th><th>Zip</th><th>Country</th><th></th> 
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items='${theUsers}' var='u'>
                        <tr class="all">
                            <td><a href="admin?action=getUser&amp;userID=${u.userID}">Update</a></td>
                            <td><c:out value='${u.userID}' /> </td>
                            <td><c:out value='${u.firstName}' /></td>
                            <td><c:out value='${u.lastName}' /></td>
                            <td><c:out value='${u.email}' /></td>
                            <td><c:out value='${u.addr1}' /></td>
                            <td><c:out value='${u.addr2}' /></td>
                            <td><c:out value='${u.city}' /></td>
                            <td><c:out value='${u.stateRegion}' /></td>
                            <td><c:out value='${u.zipcode}' /></td>
                            <td><c:out value='${u.country}' /></td>
                            <td><a href="admin?action=getUser&amp;userID=${u.userID}">Delete</a></td>
                        </tr>
                    </c:forEach>                 
                </tbody>                           
            </table>                  
                <input type="submit" name="action" value="Dashboard" />
                <input type="submit" name="action" value="Catalog" />    
            </form>
    </body>
</html>
