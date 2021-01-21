<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add driver to Car</title>
</head>
<body>
<div>
    <a href="${pageContext.request.contextPath}/">Main page</a>
</div>
<br>
<h1>Let's assign driver to car</h1>
<form method="post" action="${pageContext.request.contextPath}/cars/drivers/add">
    Please define car id:<input type="number" name="carId">
    <br>
    <br>
    Please assign driver id: <input type="number" name="driverId">
    <button type="submit">Assign</button>
</form>
</body>
</html>
