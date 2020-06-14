<%-- 
    Document   : admin
    Created on : Nov 4, 2017, 5:49:50 AM
    Author     : Jarrod Norris
    Class      : ITIS 4166-UOL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/jmansales/styles/dashboard.css" type="text/css" />
        <title>JSP Page</title>
    </head>
    <body>        
        <div id="mainbox">
            <h1>Administrative Dashboard</h1>
            <form action="admin" method="post">
                <p>Welcome: ${theUser.firstName}</p><input type="submit" id="signOut" name="action" value="Sign Out" />
            </form>
            <div id="leftbox">             
                <form id="prod" action="IOProductDB" method="post">
                    <fieldset>
                        <legend>Product</legend>
                            <label>Product Code:</label>
                            <input type="text" name="pCode" value="${p.productCode}" />
                            <label>Product Name:</label>
                            <input type="text" name="pName" value="${p.productName}" />
                            <label>Catalog Category</label>
                            <input type="text" name="cCat" value="${p.catalogCategory}" />
                            <label>Description</label>
                            <input type="text" name="desc" value="${p.description}" />
                            <label>Price</label>
                            <input type="text" name="price" value="${p.price}" />
                            <label>Image URL</label>
                            <input type="text" name="imageurl" value="${p.imageURL}" /><br />
                            <input type="submit" name="action" value="Add" />
                            <input type="submit" name="action" value="Edit" />
                            <input type="submit" name="action" value="Delete" />
                    </fieldset>                     
                </form>
            </div>
            <div id="rightbox">
               <form id="user" action="admin" method="post">
                    <fieldset>
                        <legend>Account Information</legend>
                        
                            <label>UserID</label>
                            <input type="text" name="userID" value="${u.userID}" />
                            
                            <label>Username:</label>
                            <input type="text" name="username" value="<c:out value='${u.username}' />" required  />

                            <label>Password:</label>
                            <input type="password"   name="pWord" value="<c:out value='${u.password}' />" placeholder="minimum of 5 characters" required />

                            <label for="firstName">First Name:</label>
                            <input type="text" id="firstName" name="firstName" value="<c:out value='${u.firstName}' />" required />

                            <label for="lastName">Last Name:</label>
                            <input type="text" id="lastName" name="lastName" value="<c:out value='${u.lastName}' />" required />

                            <label for="email">Email:</label>
                            <input type="text" id="email" name="email" value="<c:out value='${u.email}' />" placeholder="must have valid email addr" required />

                            <label for="addr1">Address 1:</label>
                            <input type="text" id="addr1" name="addr1" value="<c:out value='${u.addr1}' />" required />

                            <label for="addr2">Address 2</label>
                            <input type="text" id="addr2" name="addr2" value="<c:out value='${u.addr2}' />" required />

                            <label for="city">City</label>
                            <input type="text" id="city" name="city" value="<c:out value='${u.city}' />" required /> 

                            <label for="stateRegion">State/Region</label>
                            <input type="text" id="stateRegion" name="stateRegion" value="<c:out value='${u.stateRegion}' />" required />

                            <label for="zipcode">Zipcode</label>
                            <input type="text" id="zipcode" name="zipcode" value="<c:out value='${u.zipcode}' />" placeholder="5 or 5+4 format" required />

                            <label for="country">Country</label>
                            <input type="text" id="country" name="country" value="<c:out value='${u.country}' />" required /><br />
                            
                            <label for="roleName">Role</label>
                            <input type="text" id="roleName" name="roleName" value="<c:out value='${u.roleName}' />" required />

                        <input type="submit" name="action" value="Create Account" />
                        <input type="submit" name="action" value="Edit" />
                        <input type="submit" name="action" value="Delete" />
                    </fieldset>
                </form> 
            </div>
            <div id="bottomAdmin">
                <form id="dashBtns" action="admin" method="post">
                    <input type="submit" name="action" value="View All Products" />
                    <input type="submit" name="action" value="View All Users" />
                    <input type="submit" name="action" value="View All Orders" />                    
                </form>
            </div>
        </div>
        <footer>&COPY; 2017 JMan's Computer Sales & Service</footer>
    </body>
</html>
