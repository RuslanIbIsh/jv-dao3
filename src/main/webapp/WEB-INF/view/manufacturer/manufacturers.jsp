<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>manufacturers</title>
</head>
<body>
<div>
    <a href="${pageContext.request.contextPath}/">Main page</a>
</div>
<h11>All Manufacturers</h11>

<table border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>country</th>
        <th>delete</th>
    </tr>
    <c:forEach var="manufacturer" items="${manufacturers}">
        <tr>
            <td>
                <c:out value="${manufacturer.id}"/>
            </td>
            <td>
                <c:out value="${manufacturer.name}"/>
            </td>
            <td>
                <c:out value="${manufacturer.country}"/>
            </td>
            <td>
               <a href="${pageContext.request.contextPath}/manufacturers/delete?id=${manufacturer.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
