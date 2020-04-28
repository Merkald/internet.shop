<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping Cart Items</title>
</head>
<body>
<button type="button"
        onclick='location.href="http://localhost:8080/"'>Home
</button>
<button type="button"
        onclick='location.href="http://localhost:8080/products/all"'>Add Product
</button>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <c:forEach var="product" items="${shoppingCartItems}">
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
                <a href="${pageContext.request.contextPath}/removeProductFromShopCart?productId=${product.productId}">Delete from Shopping Cart</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
