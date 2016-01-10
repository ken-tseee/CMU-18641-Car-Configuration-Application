<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="model.Automobile" 
    import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Result Page</title>
</head>
<body>
	<h2>Congratulations! The has been successfully configured!</h2>
	<% Automobile auto = (Automobile) request.getAttribute("auto"); %>
	<table border="1" style="width:75%">
	<tr>
		<td><%= auto.getModel() %></td>
		<td>Base Price</td>
		<td>$<%= auto.getBasePrice() %></td>
	</tr>
	<% for(String optionSet : auto.getOptionSetList()) { %>
		<tr>
			<td><%= optionSet %></td>
			<td><%= auto.getOptionChoice(optionSet) %></td>
			<td>$<%= auto.getOptionChoicePrice(optionSet) %></td>
		</tr>
	<% } %>
	<tr>
		<td><strong>Total Price</strong></td>
		<td></td>
		<td><strong>$<%= auto.getTotalPrice() %></strong></td>
	</tr>
</body>
</html>