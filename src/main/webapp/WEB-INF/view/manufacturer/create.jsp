<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Manufacturer</title>
</head>
<body>
<div>
    <a href="${pageContext.request.contextPath}/">Main page</a>
</div>
<h1>Let's create new Manufacturer</h1>
<form method="post" action="${pageContext.request.contextPath}/manufacturers/add">
    Provide manufacturer name: <input type="text" name="name">
    <br>
    <br>
    and manufacturer country: <input type="text" name="country">
    <button type="submit">Add new Manufacturer</button>
</form>
</body>
</html>
