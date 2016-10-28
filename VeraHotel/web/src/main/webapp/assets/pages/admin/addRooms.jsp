<%--
  Created by IntelliJ IDEA.
  User: diby
  Date: 23.10.2016
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>


</head>

<form name="addroomForm" method="POST" action="controller">
 <input type="hidden" name="command" value="addroom" />

 <input type="text" name="number" value="" placeholder="number"/>
 <input type="text" name="room_type" value="" placeholder="type"/>
 <input type="text" name="price_a_day" value=""placeholder="price"/>
 <input type="text" name="status" value=""placeholder="status"/>
<input type="submit" value="add">
</form>
${operationMessage}
${errorUserExists} <br />
<a href="controller?command=
backadmin">Вернуться обратно</a>

</body>
</html>
