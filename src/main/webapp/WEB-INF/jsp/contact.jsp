<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

</head>
<body>
<h1><c:if test="${action == 'add'}">Новый контакт</c:if><c:if test="${action == 'edit'}">Редактирование</c:if></h1>

<form action="/phone-directory/contact" method="post">
    <c:if test="${action == 'edit'}">
        <input type="hidden" name="id" value="${contact.id}">
    </c:if>

    Имя: <input type="text" name="firstName" value="${contact.firstName}" required><br>
    Фамилия: <input type="text" name="lastName" value="${contact.lastName}" required><br>
    Отчество: <input type="text" name="middleName" value="${contact.middleName}"><br>
    Телефон: <input type="text" name="phone" value="${contact.phone}" required><br>
    Дата рождения: <input type="date" name="birthDate" value="${contact.birth}"><br>
    <button type="submit">Сохранить</button>
    <a href="/phone-directory/contacts">Отмена</a>
</form>
</body>
</html>