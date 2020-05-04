<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>adminAll</title>
</head>
<body class="bg-dark">
<button type="button" class="btn btn-secondary"
        onclick='location.href="http://localhost:8080/"'>Home
</button>
<div id="accordion">
    <div class="card">
        <div class="card-header bg-dark" id="headingOne">
            <h5 class="mb-0">
                <button class="btn btn-link btn-secondary mx-auto text-white" style="width: 1470px;"
                        data-toggle="collapse" data-target="#collapseOne"
                        aria-expanded="true" aria-controls="collapseOne">
                    Create Product
                </button>
            </h5>
        </div>
        <div id="collapseOne" class="collapse bg-dark" aria-labelledby="headingOne" data-parent="#accordion">
            <div class="card-body">
                <h1 class="mx-auto" style="color: chartreuse; width: 450px;">Provide Product details</h1>
                <form method="post" action="${pageContext.request.contextPath}/createProduct">
                    <table class="table table-striped table-dark">
                        <tr>
                            <th>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1">Name</span>
                                    </div>
                                    <input name="name" type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1">
                                </div>
                            </th>
                            <th>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon2">Price</span>
                                    </div>
                                    <input name="price" type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon2">
                                </div>
                            </th>
                            <th>
                                <button class="btn btn-primary" type="submit">Create</button>
                            </th>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>

<table border="1" class="table table-striped table-dark">
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
                <button type="button" class="btn btn-danger"
                        onclick='location
                                .href="${pageContext.request
                                .contextPath}/deleteProduct?productId=${product
                                .productId}"'>Delete
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
