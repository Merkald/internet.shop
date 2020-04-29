<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>allUserOrders</title>
</head>
<body>
<h1>All orders page</h1>
<button type="button"
        onclick='location.href="http://localhost:8080/"'>Home
</button>
<table border="1">
    <tr>
        <th>OrderId</th>
        <th>UserId</th>
        <th>ShowOrder</th>
        <th>DeleteOrder</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.orderId}"/>
            </td>
            <td>
                <c:out value="${order.user.userId}"/>
            </td>
            <td>
                <button type="button"
                        onclick='location
                                .href="${pageContext.request
                                .contextPath}/OrderInfo?orderId=${order
                                .orderId}"'>Info
                </button>
            </td>
            <td>
                <button type="button"
                        onclick='location
                                .href="${pageContext.request
                                .contextPath}/DeleteOrder?orderId=${order
                                .orderId}"'>Delete
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
