<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login Page</h1>
<h4 style="color:red">${errorMessage}</h4>
<form action="${pageContext.request.contextPath}/login" method="post">
    Login <input type="text" name="login">
    Password <input type="password" name="password">
    <button type="submit">Login</button>
</form>
</body>
</html>