<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>drivers</title>
</head>
<body>
<div>
    <a href="${pageContext.request.contextPath}/">Main page</a>
</div>
<h1>All drivers</h1>

<table border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>licenseNumber</th>
        <th>delete</th>
    </tr>
    <c:forEach var="driver" items="${drivers}">
        <tr>
            <td>
                <c:out value="${driver.id}"/>
            </td>
            <td>
                <c:out value="${driver.name}"/>
            </td>
            <td>
                <c:out value="${driver.licenceNumber}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/drivers/delete?id=${driver.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
