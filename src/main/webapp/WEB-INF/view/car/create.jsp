<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new Car</title>
</head>
<body>
<div>
    <a href="${pageContext.request.contextPath}/">Main page</a>
</div>
<br>
<h1>Let's create new Car</h1>
<form method="post" action="${pageContext.request.contextPath}/cars/add">
    Please provide mode:<input type="text" name="model">
    <br>
    <br>
    and input Manufacturer id:<input type="number" name="manufacturerId">
    <button type="submit">Add new car</button>
</form>
</body>
</html>
