<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Hello! Provide your user details.</h1>
<h4 style="color:red">${message}</h4>
<form method="post" action="${pageContext.request.contextPath}/registration">
    First name <input type="text" name="firstName">
    Last name <input type="text" name="lastName">
    age <input type="number" name="age">
    login <input type="text" name="login">
    email <input type="text" name="email">
    password <input type="password" name="password">
    repeat password <input type="password" name="password-repeat">
    <button type="submit">Register</button>
</form>
</body>
</html>
