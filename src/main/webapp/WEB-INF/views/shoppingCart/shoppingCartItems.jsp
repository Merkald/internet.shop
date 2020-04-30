<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shopping Cart Items</title>
</head>
<body>
<button type="button"
        onclick='location.href="../../.."'>Home
</button>
<button type="button"
        onclick='location.href="http://localhost:8080/products/all"'>Add Product
</button>
<button type="button"
        onclick='location.href="http://localhost:8080/CreateOrder?id=${shoppingCart.shoppingCartId}"'>Complete Order
</button>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
    </tr>
    <c:forEach var="product" items="${shoppingCart.items}">
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
                <button type="button"
                        onclick='location
                                .href="${pageContext.request
                                .contextPath}/removeProductFromShopCart?productId=${product
                                .productId}"'>Delete from Shopping Cart
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
