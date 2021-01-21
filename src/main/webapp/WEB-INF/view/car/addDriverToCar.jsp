<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>adddrivertocar</title>
</head>
<body>
<div>
    <a href="${pageContext.request.contextPath}/">Main page</a>
</div>
<br>
<h1>Let's assign driver to car</h1>
<form method="post" action="${pageContext.request.contextPath}/cars/drivers/add">
    Please define car id:<input type="number" name="carid">
    <br>
    <br>
    Please assign driver id: <input type="number" name="driverid">
    <button type="submit">Assign</button>
</form>
</body>
</html>
