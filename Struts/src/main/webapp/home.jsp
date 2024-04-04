<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Management</title>
</head>
<body>
    <h1>Employee Management</h1>
    <br><br>
    Add employee details: 
    <s:a action="getDetails">
        <button>Click here</button>
    </s:a>
    <br><br>
    View employee details:
	<s:a action="viewDetails">
        <button>Click here</button>
    </s:a>
    <br><br>
    Edit employee details:
	<s:a action="editDetails">
        <button>Click here</button>
    </s:a>
</body>
</html>
