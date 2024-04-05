<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	<title>Edit details</title>
	</head>
	<body>
		<h1>Update the details</h1>
		<s:form action="finishedit" method="post">
			<s:label for="name">Employee name: </s:label>
			<s:hidden name="id" value="%{id}"/>
			<s:textfield name="name" value="%{name}"/>
			<s:select name="manager_id" list="users" listKey="key" listValue="value"/>
			<s:submit value="Edit"/>
		</s:form>
	</body>
</html>