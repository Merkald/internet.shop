<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Main Page</h1>
<h3>User Options</h3>
<button type="button"
        onclick='location.href="http://localhost:8080/registration"'>Registration
</button>
<button type="button"
        onclick='location.href="http://localhost:8080/products/all"'>All Products for User
</button>
<br>
<h3>Admin Options</h3>
<button type="button"
        onclick='location.href="http://localhost:8080/users/all"'>Get All Users
</button>
<button type="button"
        onclick='location.href="http://localhost:8080/products/adminAll"'>All Products for Admin
</button>
<br>
<h3>Generators</h3>
<button type="button"
        onclick='location.href="http://localhost:8080/generateUsers"'>Generate Users
</button>
<button type="button"
        onclick='location.href="http://localhost:8080/generateProducts"'>Generate Products
</button>
<button type="button"
        onclick='location.href="http://localhost:8080/CreateShopCart"'>Create Shopping Cart
</button>
</body>
</html>
