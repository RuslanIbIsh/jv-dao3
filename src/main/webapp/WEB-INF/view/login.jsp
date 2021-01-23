<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Please sign in</h1>
<br>
<h3 style="color: red">${errorMessage}</h3>
<br>
<form method="post" action="${pageContext.request.contextPath}/login">
    Enter login:<input type="text" name="login">
    and password:<input type="password" name="password">
    <button type="submit">Sign in</button>
</form>
<br>
<br>
<a href="${pageContext.request.contextPath}/drivers/add">Sing out</a>
</body>
</html>
