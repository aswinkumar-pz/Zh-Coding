<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Add users</title>
	</head>
	<body>
		<h1>Add user</h1>
		<s:form action="add" method="post">
			<s:textfield name="name" label="Enter name: "/>
			<s:select name="manager_id" list="users" listKey="key" listValue="value" label="Select manager"/>
			<s:hidden name="message" value="Data added successfully"/>
			<s:submit value="Submit"/>
		</s:form>
	</body>
</html>