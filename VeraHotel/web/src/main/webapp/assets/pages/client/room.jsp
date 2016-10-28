<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Комнаты</title>
	</head>
	<body>
		<table border="1">
			<tr bgcolor="#CCCCCC">
					<td align="center"><strong>Номер</strong></td>
					<td align="center"><strong>Тип</strong></td>
					<td align="center"><strong>Цена</strong></td>
					<td align="center"><strong>Статус</strong></td>
			</tr>
			<c:forEach var="room" items="${roomList}">
				<tr>
					<td><c:out value="${ room.number}" /></td>
					<td><c:out value="${ room.room_type}" /></td>
					<td><c:out value="${ room.price_a_day}" /></td>
					<td><c:out value="${ room.status}" /></td>
				</tr>
			</c:forEach>
		</table>
		<a href="controller?command=backclient">Вернуться обратно</a>
		<a href="controller?command=backadmin">Вернуться обратно на страницу админа</a>
		<a href="controller?command=logout">Выйти из системы</a>
	</body>
</html>