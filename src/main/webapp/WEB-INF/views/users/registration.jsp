<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<button type="button"
        onclick='location.href="../../.."'>Home
</button>
<button type="button"
        onclick='location.href="http://localhost:8080/users/all"'>Back to all Users
</button>
<h1>Hello! Provide your user details.</h1>
<h4 style="color:red">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/registration">
    <br>
    First name <input type="text" name="firstName">
    <br>
    Last name <input type="text" name="lastName">
    <br>
    age <input type="number" name="age">
    <br>
    login <input type="text" name="login">
    <br>
    email <input type="text" name="email">
    <br>
    password <input type="password" name="password">
    <br>
    repeat password <input type="password" name="password-repeat">
    <br>
    <button type="submit">Register</button>
</form>
</body>
</html>
