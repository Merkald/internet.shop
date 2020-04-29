<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>allUsers</title>
</head>
<body>
<h1>All users page</h1>
<button type="button"
        onclick='location.href="http://localhost:8080/"'>Home
</button>
<button type="button"
        onclick='location.href="http://localhost:8080/registration"'>Register new User
</button>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>SurName</th>
        <th>Age</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Login</th>
        <th>Password</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                <c:out value="${user.userId}"/>
            </td>
            <td>
                <c:out value="${user.firstName}"/>
            </td>
            <td>
                <c:out value="${user.lastName}"/>
            </td>
            <td>
                <c:out value="${user.age}"/>
            </td>
            <td>
                <c:out value="${user.email}"/>
            </td>
            <td>
                <c:out value="${user.phone}"/>
            </td>
            <td>
                <c:out value="${user.login}"/>
            </td>
            <td>
                <c:out value="${user.password}"/>
            </td>
            <td>
                <button type="button"
                        onclick='location
                                .href="${pageContext.request
                                .contextPath}/deleteUser?userId=${user
                                .userId}"'>Delete
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
