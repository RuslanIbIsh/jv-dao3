<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mainpage</title>
</head>
<body>
<h1>Hi there!</h1>
<h3>time is ${time}</h3>

<h1>Welcome to application</h1>
<br>
<div>
    <a href="${pageContext.request.contextPath}/drivers/add">Create new driver</a>
</div>
<br>
<div>
    <a href="${pageContext.request.contextPath}/drivers">Render all drivers</a>
</div>
<br>
<div>
    <a href="${pageContext.request.contextPath}/manufacturers/add">Create new manufacturer</a>
</div>
<br>
<div>
    <a href="${pageContext.request.contextPath}/manufacturers">Render all Manufacturers</a>
</div>
<br>
<div>
    <a href="${pageContext.request.contextPath}/cars/add">Create new car</a>
</div>
<br>
<div>
    <a href="${pageContext.request.contextPath}/cars">Render all Cars</a>
</div>
<br>
<div>
    <a href="${pageContext.request.contextPath}/cars/drivers/add">Add Driver to Car</a>
</div>

</body>
</html>
