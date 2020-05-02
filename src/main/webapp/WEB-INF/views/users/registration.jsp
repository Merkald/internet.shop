<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Registration</title>
</head>
<body class="bg-dark">
<button type="button" class="btn btn-secondary"
        onclick='location.href="../../.."'>Home
</button>
<button type="button" class="btn btn-danger"
        onclick='location.href="http://localhost:8080/users/all"'>Back to all Users
</button>
<br>
<br>
<br>
<br>
<div class="card text-white bg-secondary mb-3 mx-auto" style="max-width: 40rem;">
    <div class="card-header">
        <h1 class="mx-auto" style="width: 120px;">Hello!</h1>
        <h3 class="mx-auto" style="width: 320px;">Provide your user details.</h3>
    </div>
    <div class="card-body">
        <form method="post" action="${pageContext.request.contextPath}/registration">
            <div class="input-group flex-nowrap">
                <div class="input-group-prepend">
                    <span class="input-group-text" style="width: 140px;">First name</span>
                </div>
                <input type="text" name="firstName" style="width: 500px;">
            </div>
            <div class="input-group flex-nowrap">
                <div class="input-group-prepend">
                    <span class="input-group-text" style="width: 140px;">Last name</span>
                </div>
                <input type="text" name="lastName" style="width: 500px;">
            </div>
            <div class="input-group flex-nowrap">
                <div class="input-group-prepend">
                    <span class="input-group-text" style="width: 140px;">age</span>
                </div>
                <input type="number" name="age" style="width: 500px;">
            </div>
            <div class="input-group flex-nowrap">
                <div class="input-group-prepend">
                    <span class="input-group-text" style="width: 140px;">login</span>
                </div>
                <input type="text" name="login" style="width: 500px;">
            </div>
            <div class="input-group flex-nowrap">
                <div class="input-group-prepend">
                    <span class="input-group-text" style="width: 140px;">email</span>
                </div>
                <input type="text" name="email" style="width: 500px;">
            </div>
            <div class="input-group flex-nowrap">
                <div class="input-group-prepend">
                    <span class="input-group-text" style="width: 140px;">password</span>
                </div>
                <input type="password" name="password" style="width: 500px;">
            </div>
            <div class="input-group flex-nowrap">
                <div class="input-group-prepend">
                    <span class="input-group-text" style="width: 140px;">repeat password</span>
                </div>
                <input type="password" name="password-repeat" style="width: 500px;">
            </div>
            <h4 style="color:red">${message}</h4>
            <div class="mx-auto my-3" style="width: 100px;">
                <button class="btn btn-primary btn-lg" type="submit">Register</button>
            </div>
        </form>
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
