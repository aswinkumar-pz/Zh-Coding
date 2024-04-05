<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Success</title>
	</head>
	<body>
		<h1><s:property value="message"/></h1>
		<br><br>
		<s:a action="goHome">
			<button>Go to home page</button>
		</s:a>
	</body>
</html>