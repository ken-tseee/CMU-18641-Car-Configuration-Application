<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Automobile List Menu</title>
</head>
<body>
	<% ArrayList<String> list = (ArrayList<String>) request.getAttribute("list"); %>
	<h2>Please select an Automobile.</h2>
	<form method="get" action="SelectOption">
	<select name="modelName">
	<% for(String name : list) { %>
		<option value="<%=name %>"><%=name %></option>
	<% } %>
	</select>
	<input type="submit" value="Submit">
	</form>
</body>
</html>