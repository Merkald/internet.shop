<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>allUserOrders</title>
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
        <th>OrderId</th>
        <th>UserId</th>
        <th>ShowOrder</th>
        <th>DeleteOrder</th>
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
                <a href="${pageContext.request.contextPath}/deleteUser?userId=${user.userId}">Delete</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/deleteUser?userId=${user.userId}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
