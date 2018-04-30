<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration Form</title>
</head>
<body>
<%--<s:url var="userRegistration" value="registrationProcess.html"/>--%>
<form:form id="registerForm" modelAttribute="registrationModel" method="post"
           action="/register" enctype="multipart/form-data">
    <table align="center">

        <tr>
            <td><form:label path="username">Username</form:label></td>
            <td><form:input path="username"/></td>
            <td><form:errors path="username"/></td>
        </tr>
        <tr>
            <td><form:label path="password">Password</form:label></td>
            <td><form:password path="password"/></td>
            <td><form:errors path="password"/></td>
        </tr>
        <tr>
            <td><form:label path="firstName">First Name</form:label></td>
            <td><form:input path="firstName"/></td>
            <td><form:errors path="firstName"/></td>
        </tr>
        <tr>
            <td><form:label path="lastName">Last Name</form:label></td>
            <td><form:input path="lastName"/></td>
            <td><form:errors path="lastName"/></td>
        </tr>
        <tr>
            <td><form:label path="email">Email</form:label></td>
            <td><form:input path="email"/></td>
            <td><form:errors path="email"/></td>
        </tr>
        <tr>
            <td><form:label path="phone">Phone</form:label></td>
            <td><form:input path="phone"/></td>
            <td><form:errors path="phone"/></td>
        </tr>
        <tr>
            <td><form:label path="photo">Photo</form:label></td>
            <td><input type="file" name="photo" accept="image/jpeg,image/png,image/gif" ></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="Register"/>
            </td>
        </tr>
    </table>
</form:form>
<br>
<%--<a href="usersList.html">Click Here to see User List</a>--%>
</body>
</html>

