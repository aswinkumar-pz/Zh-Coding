<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Employee Hierarchy</title>
	</head>
	<style>
		table{
			text-align:center;padding:5px;
		}
	</style>
	<body>
		<h1>Employee Hierarchy</h1>
		<br>
		<table border=1 style='{text-align:center}'>
		<s:iterator value="levels">
			<tr>
    			<s:iterator var="id">
        			<s:if test="%{ChildCount.get(#id) == 0}">
            			<td colspan="<s:property value='%{colspace.get(#id)}'/>" rowspan="<s:property value='%{levels.size()}'/>">
                			<s:property value="users.get(#id).name"/>
            			</td>
        			</s:if>
        			<s:else>
            			<td colspan="<s:property value='%{colspace.get(#id)}'/>">
               				<s:property value="users.get(#id).name"/>
           				</td>
       				</s:else>
    			</s:iterator>
			</tr>
		</s:iterator>
		</table>
		<br><br>
		<s:a action="goHome">
			<button>Go to home page</button>
		</s:a>
	</body>
</html>