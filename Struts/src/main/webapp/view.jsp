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
	            <th>ID</th>
	            <th>Name</th>
	            <th>Manager ID</th>
	    	</tr>
			<s:iterator value="users">
				<tr>
					<td>id</td>
					<td>name</td>
					<td>manager_id</td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>