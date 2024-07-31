<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!--  <%@ page import="nitttr_1"%> -->
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Print page Show</title>
</head>
<body>
<page Size ="A4">
<div>
<center>
	<h1>Print Page Content</h1>
</center>
</div>
Gender gender =(Gender)request.getAttribute("Gender"); 
	<form action="" id="myform" name="myform" accept-charset="UTF-8">
		<label for="id">ID:</label> <input type="text" id="id" name="id"
			value="<%= request.getParameter("id") %>" readonly><br>

		<label for="code">Code:</label> <input type="text" id="code"
			name="code" value="<%= request.getParameter("code") %>" readonly><br>

		<label for="value">Value:</label> <input type="text" id="value"
			name="value" value="<%= request.getParameter("value") %>" readonly><br>
	</form>




</body>
</html>