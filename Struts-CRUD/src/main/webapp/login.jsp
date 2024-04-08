<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login page</title>
	</head>
	<body>
		<s:property value="message"/>
		<s:form action="loginAction" method="post">
			<s:textfield name="username" label="Enter username: "/>
			<s:password name="password" label="Enter password: "/>
			<s:submit/>
		</s:form> 
	</body>
</html>