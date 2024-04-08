<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Edit employee</title>
	</head>
	<body>
		<h1>Edit employee details</h1>
		<s:form  action="edit" method="post">
			<s:label for="id" value="Select employee to edit"/>
			<s:select name="id" list="users" listKey="key" listValue="value"/>
			<s:submit value="Submit"/>
		</s:form>
		<br><br>
    <s:a action="logoutAction">
        <button>Logout</button>
    </s:a>
	</body>
</html>