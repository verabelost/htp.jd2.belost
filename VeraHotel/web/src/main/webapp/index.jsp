<%@ page contentType="text/html; charset=UTF-8" 
		 pageEncoding="UTF-8" errorPage="/assets/pages/error/error.jsp"%>
		 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<html>
	<head>
		<title>Авторизация</title>
		<style type="text/css">
			div{
				background-color:lightsteelblue ;
				text-decoration: double;
				width: 250px;
				border-style: solid;
				border-color: darkblue;
				font-family: "Calibri Light";


			}
		</style>
	</head>
	<body>
	<div>Добро пожаловать в Отель</div>
	<br>
	<div>


		<form name="loginForm" method="POST" action="controller">
			<input type="hidden" name="command" value="login" />

			Введите ваш логин и пароль: <br/>
			<table>
				<tr>
					<td>Логин:</td>
					<td><input type="text" name="login" placeholder="только латинские буквы" value="" size="20"  required pattern="^[a-zA-Z0-9]+$"/></td>
				</tr>
				<tr>
					<td>Пароль:</td>
					<td><input type="password" placeholder="лат.буквы или цифры" name="password" value="" size="20"  required pattern="^[a-zA-Z0-9]+$"/></td>
				</tr>
			</table>			 
			${errorLoginOrPassword} <br />
			<input type="submit" value="Войти" /> 
			<a href="controller?command=gotoregistration">Регистрация</a>
		</form>
		</div>
	</body>
</html>