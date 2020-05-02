<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Shopping Cart Items</title>
</head>
<body class="bg-dark">
<button type="button" class="btn btn-secondary"
        onclick='location.href="../../.."'>Home
</button>
<button type="button" class="btn bg-success"
        onclick='location.href="http://localhost:8080/products/all"'>Add Product
</button>
<button type="button" class="btn btn-primary"
        onclick='location.href="http://localhost:8080/CreateOrder?id=${shoppingCart.shoppingCartId}"'>Complete Order
</button>
<h1 style="color: white">Shopping Cart</h1>
<table border="1" class="table table-striped table-dark">
    <tr>
        <th class="mx-auto" style="width: 400px;">ID</th>
        <th class="mx-auto" style="width: 400px;">Name</th>
        <th class="mx-auto" style="width: 400px;">Price</th>
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
                <button type="button" class="btn btn-danger"
                        onclick='location
                                .href="${pageContext.request
                                .contextPath}/removeProductFromShopCart?productId=${product
                                .productId}"'>Delete from Shopping Cart
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
