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
					<td><s:property value="top"/></td>
					<td><s:property value="name"/></td>
					<td><s:property value="manager_id"/></td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>