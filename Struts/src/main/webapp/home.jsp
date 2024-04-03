<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Employee management</title>
	</head>
	<body>
		<h1>Employee management</h1>
		<br>
		<s:form action="getDetails" method="input">
			<s:label for="addsub" value="Add user:"/>
			<s:submit name="addsub" value="Click here"/>
		</s:form>
		
		<s:form action="viewDetails"  >
			<s:label for="viewsub" value="Display all user:"/>
			<s:submit name="viewsub" />
		</s:form>
	</body>
</html>