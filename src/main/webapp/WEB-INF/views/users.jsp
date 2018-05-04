<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Details</title>
</head>
<body>
<c:if test="${!empty model.user}">
    <table border="1" bgcolor="black" width="600px">
        <tr style="background-color: teal;color: white;text-align: center;" height="40px">
            <td>User Id</td>
            <td>Username</td>
            <td>Password</td>
            <td>First Name</td>
            <td>Last Name</td>
            <td>Email</td>
            <td>Phone</td>
            <td>Photo</td>
            <td>Action</td>
        </tr>
        <c:forEach items="${model.user}" var="user">
            <c:url var="editUrl" value="/users/${user.id}" />
            <c:url var="deleteUrl" value="/users/${user.id}" />

            <tr style="background-color:white;color: black;text-align: center;" height="30px">
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.username}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.firstName}"/></td>
                <td><c:out value="${user.lastName}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.phone}"/></td>
                <td><img alt="img" src="data:image/jpg;base64,${model.photo}"/></td>
                <td>
                    <a href="<c:out value='${editUrl}'/>">Edit user</a>
                    <a href="<c:out value='${deleteUrl}'/>">Delete user</a>

                    <%--<c:redirect url="/users/${user.id}"><c:redirect>Edit</c:redirect></c:redirect>--%>
                    <%--<c:redirect url="/users/${user.id}"><c:redirect>Delete</c:redirect></c:redirect>--%>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<br>
<a href="register.html">Click Here to add new User</a>
</body>
</html>  