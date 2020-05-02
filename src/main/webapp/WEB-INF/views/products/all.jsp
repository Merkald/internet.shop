<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>allProducts</title>
</head>
<body class="bg-dark">
<table>
    <tr>
        <th>
            <button type="button" class="btn btn-secondary"
                    onclick='location.href="http://localhost:8080/"'>Home
            </button>
        </th>
        <th style="width: 300px;">
            <button type="button" class="btn bg-success"
                    onclick='location.href="http://localhost:8080/shoppingCart"'>Shopping Cart
            </button>
        </th>
        <th>
            <h4 style="width: 500px; color:red">${productInShopCard}</h4>
        </th>
    </tr>
</table>
<h1 style="color: white">All Products</h1>
<table border="1" class="table table-striped table-dark">
    <tr>
        <th style="width: 300px;">ID</th>
        <th style="width: 400px;">Name</th>
        <th style="width: 500px;">Price</th>
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
                <button type="button" class="btn btn-primary"
                        onclick='location
                                .href="${pageContext.request
                                .contextPath}/addProductToShopCart?productId=${product
                                .productId}"'>Add to Shopping Carts
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
