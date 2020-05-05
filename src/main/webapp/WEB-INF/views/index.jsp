<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Title</title>
</head>
<body class="bg-secondary">
<div class="bg-success">
    <%@include file="/WEB-INF/views/users/userDetails.jsp" %>
</div>
<br>
<br>
<h1 class="mx-auto"  style="width: 200px;">Main Page</h1>
<br>
<div id="accordion" class="mx-auto" style="width: 600px;">
    <div class="card">
        <div class="card-header bg-dark" id="headingOne">
            <h5 class="mb-0">
                <button class="btn btn-link btn-secondary mx-auto text-white" style="width: 560px;" data-toggle="collapse" data-target="#collapseOne"
                        aria-expanded="true" aria-controls="collapseOne">
                    User Options
                </button>
            </h5>
        </div>

        <div id="collapseOne" class="collapse show bg-dark" aria-labelledby="headingOne" data-parent="#accordion">
            <div class="card-body">
                <button type="button" class="btn btn-primary"
                        onclick='location.href="http://localhost:8080/products/all"'>All Products for User
                </button>
                <button type="button" class="btn btn-primary"
                        onclick='location.href="http://localhost:8080/users/orders"'>All Orders for User
                </button>
            </div>
        </div>
    </div>
    <div class="card">
        <div class="card-header bg-dark" id="headingTwo">
            <h5 class="mb-0">
                <button class="btn btn-link collapsed btn-secondary mx-auto text-white" style="width: 560px;" data-toggle="collapse" data-target="#collapseTwo"
                        aria-expanded="false" aria-controls="collapseTwo">
                    Admin Options
                </button>
            </h5>
        </div>
        <div id="collapseTwo" class="collapse bg-dark" aria-labelledby="headingTwo" data-parent="#accordion">
            <div class="card-body">
                <button type="button" class="btn btn-primary"
                        onclick='location.href="http://localhost:8080/users/all"'>Get All Users
                </button>
                <button type="button" class="btn btn-primary"
                        onclick='location.href="http://localhost:8080/products/adminAll"'>All Products for Admin
                </button>
                <button type="button" class="btn btn-primary"
                        onclick='location.href="http://localhost:8080/orders/all"'>All Orders
                </button>
                <br>
            </div>
        </div>
    </div>
    <div class="card">
        <div class="card-header bg-dark" id="headingThree">
            <h5 class="mb-0">
                <button class="btn btn-link collapsed btn-secondary mx-auto text-white" style="width: 560px;" data-toggle="collapse" data-target="#collapseThree"
                        aria-expanded="false" aria-controls="collapseThree">
                    Generators
                </button>
            </h5>
        </div>
        <div id="collapseThree" class="collapse bg-dark" aria-labelledby="headingThree" data-parent="#accordion">
            <div class="card-body">
                <button type="button" class="btn btn-primary"
                        onclick='location.href="/injectData"'>Inject Data
                </button>
                <button type="button" class="btn btn-primary"
                        onclick='location.href="/userInclude"'>User Include
                </button>
            </div>
        </div>
    </div>
</div>
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
