<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Телефонная книга</title>
</head>
<body>
<h1>Список контактов</h1>

<c:if test="${not empty param.message}">
    <p style="color: green;">${param.message}</p>
</c:if>
<c:if test="${not empty param.error}">
    <p style="color: red;">Ошибка</p>
</c:if>

<table border="1">
    <thead>
        <tr>
            <th>Имя</th><th>Фамилия</th><th>Отчество</th><th>Телефон</th><th>Дата рождения</th><th>Редактировать</th><th>Удалить</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${contacts}" var="c">
        <tr>
            <td>${c.firstName}</td>
            <td>${c.lastName}</td>
            <td>${c.middleName}</td>
            <td>${c.phone}</td>
            <td>${c.birth}</td>
            <td><a href="/phone-directory/form?id=${c.id}">✏️</a></td>
            <td><a href="/phone-directory/all?delete=${c.id}">❌</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>
<a href="/phone-directory/form">➕ Добавить контакт</a>
</body>
</html>