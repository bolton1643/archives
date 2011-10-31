<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="common/global.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
</head>
<body>
    <a href="admin/registe.jsp">注册用户</a>
 	<table style="border: 1px dashed #000000">
 		<thead>
		<tr>
			<th align="left">编号</th>
			<th align="left">用户姓名</th>
			<th align="left">登录名</th>
			<th align="left">编辑</th>
			<th align="left">权限</th>
		</tr>
		</thead>
		<tbody>
		<s:iterator id="cl" value="cl" status="st">
           <tr align="left" >
             <td><s:property value="userId"/></td>
             <td><s:property value="userName"/></td>
             <td><s:property value="loginName"/></td>
             <td><a href="GetUserById.action?user.userId=<s:property value="userId"/>" >编辑</a></td>
             <td><a href="UserRight.action?user.userId=<s:property value="userId"/>" >权限</a></td>
           </tr>
        </s:iterator>
        </tbody>
    </table>
</body>
</html>