<!DOCTYPE>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xml:lang="jv">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>userDetails</title>
</head>
<body class="bg-dark">
<button type="button" class="btn btn-primary"
        onclick='location.href="http://localhost:8080/users/logout"'>Logout
</button>
    <h5>${sessionScope.user.firstName} ${sessionScope.user.lastName}</h5>
    <c:forEach var="role" items="${sessionScope.user.roles}">
        <tr>
            <td>
                <c:out value="Role_name: ${role.roleName}"/>
            </td>
        </tr>
    </c:forEach>
</body>
</html>
