<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="common/global.jsp"%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" type="text/css" href="css/login.css" />
		<script type="text/javascript" src="js/jquery-last.js"></script>
		<script type="text/javascript" src="js/plugin/jquery.form.js"></script>
		<script type="text/javascript" src="js/plugin/formValidator.js"></script>
		<script type="text/javascript" src="js/plugin/bgiframe.js"></script>
		<script type="text/javascript" src="js/plugin/weebox.js"></script>
		<script type="text/javascript" src="js/common2.js"></script>
		<script type="text/javascript" src="js/list.js"></script>
		<script type="text/javascript" src="js/form.js"></script>
		<script type="text/javascript" src="js/login.js"></script>
		<style type="text/css">
			@IMPORT url("css/all.css");
			@IMPORT url("css/weebox/weebox.css");
			@IMPORT url("css/validator/validator.css");

</style>
		
		<title>用户登录</title>
	</head>
	<body>
		<form action="login.action" id="loginForm" name="loginForm" method="post">
			<div
				style="color:#B4BBCF;margin-top: 100px;" align="center">
				<font face="黑体" color="#CB4F30" size="10"><b><%=siteName%></b>
				</font>
			</div>

			<div align="center" style="margin-top: 100px;">
				<table style="width: 50%;" border="0" id='formTable'>
					<tr>
						<td>用户名：</td>
						<td style="width: 30%">
							<input type="text" id="loginName" name='loginName' size="20"/>
						</td>
						<td style='width:30%'><div id='loginNameTip'></div></td>
					</tr>
					<tr>
						<td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
						<td style="width: 30%">
							<input type="password" id="password" name='password' size="20"/>
						</td>
						<td style='width:30%'><div id='passwordTip'></div></td>
					</tr>
				</table>
				<p align=center>
					<input type="submit" value="登 陆" name="Submit" id="submit"/>
					&nbsp;&nbsp;
					<input type="reset" value="重 填" name="Reset" />
					&nbsp;
					<input type="checkbox" name="ifSave" id="ifSave" checked="checked" title="记住用户名和密码"/>
					<label for="ifSave">记住</label>
				</p>
				
			</div>

		</form>
	</body>
</html>