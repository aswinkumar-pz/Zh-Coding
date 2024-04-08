<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	<title>Delete employee details</title>
	</head>
	<body>
		<h1>Delete employee details</h1>
		<s:form action="finishdelete" method="post">
			<s:hidden name="id" value="%{id}"/>
			<label>Employee name:</label>
			<s:property value="%{user.name}"/><br>
			<label>Manager name:</label>
			<s:property value="users[user.manager_id].name"/>
			
			<s:label for="new_manager_id" value="Select new manager for juniors"/>
			<s:select name="new_manager_id" list="users" listKey="key" listValue="value"/>
			<s:submit value="Delete"/>
		</s:form>
		<br><br>
    <s:a action="logoutAction">
        <button>Logout</button>
    </s:a>
	</body>
</html>