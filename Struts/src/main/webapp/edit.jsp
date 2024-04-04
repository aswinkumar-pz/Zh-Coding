<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>Edit employee details</h1>
		<s:form  action="edit" method="post">
			<s:select name="user" list="idName" label="Select employee to edit"/>
			<s:submit value="Submit"/>
		</s:form>
	</body>
</html>