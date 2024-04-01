<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Struts demo</title>
	</head>
	<body>
		<s:form action="UserAction">  
		<s:textfield name="id" label="Id"></s:textfield>  
		<s:textfield name="name" label="Name"></s:textfield> 
		<s:submit value="save"></s:submit>  
		</s:form>  
	</body>
</html>