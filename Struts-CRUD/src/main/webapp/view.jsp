<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>View all employees</title>
	</head>
	<body>
		<table>
			<tr>
	            <th>Id</th>
	            <th>Name</th>
	            <th>Manager_id</th>
	    	</tr>
	    	<s:iterator value="users">
	    		<tr>
	    			<td><s:property value="key"/></td>
	    			<td><s:property value="value.name"/></td>
	    			<td><s:property value="value.manager_id"/></td>
	    		</tr>
	    	</s:iterator>
		</table>
		<br><br>
		<s:a action="goHome">
			<button>Go to home page</button>
		</s:a>
		<br><br>
    <s:a action="logoutAction">
        <button>Logout</button>
    </s:a>
	</body>
</html>