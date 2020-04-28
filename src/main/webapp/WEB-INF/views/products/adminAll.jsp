<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>adminAll</title>
</head>
<body>
<button type="button"
        onclick='location.href="http://localhost:8080/"'>Home
</button>
<button type="button"
        onclick='location.href="http://localhost:8080/createProduct"'>Create Product
</button>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value="${product.productId}"/>
            </td>
            <td>
                <c:out value="${product.name}"/>
            </td>
            <td>
                <c:out value="${product.price}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/deleteProduct?productId=${product.productId}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
