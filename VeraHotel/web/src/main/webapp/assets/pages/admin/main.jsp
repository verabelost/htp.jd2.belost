<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
	<head>
		<title>Добро пожаловать</title>
	</head>
	<body>
		<h2>${user.firstName} ${user.lastName}</h2>
		<h3>Вы вошли в систему как администратор</h3>
		<h4>Выберите операцию:</h4>
		<a href="controller?command=clients">Показать список клиентов</a> <br/>
		<a href="controller?command=showroom">Показать список комнат</a> <br/>
		<a href="controller?command=operations">Показать список всех операций</a> <br/>
		<a href="controller?command=gotounblock">Разблокировать счет клиента</a> <br/>
		<a href="controller?command=logout">Выйти из системы</a> <br/>


		<form name="showRoom" method="POST" action="controller">
			<input type="hidden" name="command" value="showroom" />
			<input type="submit" value="Show rooms"/>
	</form>

		<a href="controller?command=gotoaddroom">добавить комнату</a> <br/>



	</body>
</html>