<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Login</title>
</head>
<body class="bg-secondary">
<button type="button" class="btn btn-dark"
        onclick='location.href="/InjectData"'>Inject Data
</button>
<br>
<br>
<br>
<div class="mx-auto my-auto" style="width: 500px; height: 200px;">
    <h1 class="mx-auto" style="width: 200px;">Login Page</h1>
    <h6 class="mx-auto" style="width: 310px;">
        If you doesnt have account please
        <a href="http://localhost:8080/registration">Register</a>
    </h6>
    <form action="${pageContext.request.contextPath}/login"
          method="post"
          class="w-30 p-4">
        <div class="form-group">
            <label>Login</label>
            <input type="text"
                   class="form-control"
                   aria-describedby="emailHelp"
                   name="login">
        </div>
        <div class="form-group">
            <label class="mx-auto">Password</label>
            <input type="password"
                   class="form-control"
                   name="password">
        </div>
        <div class="mx-auto" style="width: 230px;">
            <h6 style="width: 300px; color:red">${errorMessage}</h6>
        </div>
        <div class="mx-auto my-5" style="width: 200px;">
            <button type="submit" class="btn btn-primary"
                    style="width: 200px;">Submit
            </button>
        </div>

    </form>
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
