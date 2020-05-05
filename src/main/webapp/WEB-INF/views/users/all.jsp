<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>allUsers</title>
</head>
<body class="bg-dark">
<button type="button" class="btn btn-secondary"
        onclick='location.href="http://localhost:8080/"'>Home
</button>
<button type="button" class="btn bg-success"
        onclick='location.href="http://localhost:8080/users/registration"'>Register new User
</button>
<h1 style="color: white">All users page</h1>
<table border="1" class="table table-striped table-dark">
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
                <button type="button" class="btn btn-danger"
                        onclick='location
                                .href="${pageContext.request
                                .contextPath}/users/delete?userId=${user
                                .userId}"'>Delete
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>
