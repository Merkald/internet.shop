<h1>Internet Shop</h1>
<h1>Table of Contents</h1>
<ul>
<li><a href="#project_purpose">Project purpose</a></li>
<li><a href="#project_structure">Project structure</a></li>
<li><a href="#For_developer">For developer</a></li>
<li><a href="#authors">Authors</a></li>
</ul>
<a name="project_purpose"><h2>Project purpose</h2>
This is a template for creating an e-store.
<br><br>
It has login and registration forms.
There are controllers for working with items, users, orders and buckets:
<br><br>
Registration - for registering new users, user will have USER role by default,
<br><br>
Login - for user authentication and authorization,
<br><br>
Logout - for logging out.
<br><br>
Users will have access to the following options:
<ul>
  <li>All products for user - open list of products and provide possibility to add product to shopping cart.</li>
  <li>Shopping Cart - open users shopping Cart to delete products from there and complete order</li>
  <li>All orders for user - Open list of all user orders with and information about order.</li>
</ul>
Admins will have access to the following options:
<ul>
  <li>Get all users - Opens list of all users and provide possibility to remove user from DB.</li>
  <li>All products for Admin - open list of products and provide possibility to delete or create product.</li>
  <li>All orders - open list with all orders.</li>
</ul>
<a name="project_structure"><h2>Project Structure</h2>
<ul>
<li>java 14</li>
<li>Maven</li>
<li>javax.servlet 3.1.0</li>
<li>jstl 1.2</li>
<li>log4j 1.2.17</li>
<li>maven-checkstyle-plugin</li>
<li>mysql-connector-java</li>
</ul>
<a name="For_developer"><h1>For developer</h1>

Open the project in your IDE.

Add it as maven project.

Configure Tomcat:
<ul>
<li>add artifact;</li>
<li>add sdk 11.0.3</li>
<\ul>

Add sdk 11.0.3 in project struсture.

Create a schema "internet_shop" in any SQL database.

Use file interntet.shop.src.main.java.resources.init_db.sql to create all the tables required by this app.

At interntet.shop.src.main.java.interntet.shop.util.ConnectionUtil class use username and password for your DB to create a Connection.

Change a path in internte.tshop.src.main.java.resources.log4j.properties. It has to reach your logFile.

Run the project.

<a name="authors"><h1>Authors</h1>
<a href="https://github.com/Merkald">Yaroslav Vysochanskii</a>