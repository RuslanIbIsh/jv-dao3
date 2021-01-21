<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new Driver</title>
</head>
<body>
<div>
    <a href="${pageContext.request.contextPath}/">Main page</a>
</div>
<h1>Let's create new driver, provide data</h1>
<form method="post" action="${pageContext.request.contextPath}/drivers/add">
    Please provide driver name: <input type="text" name="name">
    provide license number: <input type="text" name="licenseNumber">
    <button type="submit">Add new driver</button>
</form>

</body>
</html>
