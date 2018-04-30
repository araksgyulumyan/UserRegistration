<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Details</title>
</head>
<body>
<c:if test="${!empty user}">
    <table border="1" bgcolor="black" width="600px">
        <tr style="background-color: teal;color: white;text-align: center;" height="40px">
            <td>User Id</td>
            <td>Username</td>
            <td>Password</td>
            <td>First Name</td>
            <td>Last Name</td>
            <td>Email</td>
            <td>Phone</td>
        </tr>
        <c:forEach items="${user}" var="user">
            <tr style="background-color:white;color: black;text-align: center;" height="30px">
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.username}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.firstName}"/></td>
                <td><c:out value="${user.lastName}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.phone}"/></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<br>
<a href="register.html">Click Here to add new User</a>
</body>
</html>  