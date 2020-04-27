<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>createProduct</title>
</head>
<body>
<button type="button"
        onclick='location.href="http://localhost:8080/"'>Home
</button>
<button type="button"
        onclick='location.href="http://localhost:8080/products/all"'>Back to all Products
</button>
<h1>Provide Product details</h1>
<form method="post" action="${pageContext.request.contextPath}/createProduct">
    <br>
    Name <input type="text" name="name">
    <br>
    Price <input type="number" name="price">
    <br>
    <button type="submit">Create</button>
</form>
</body>
</html>
