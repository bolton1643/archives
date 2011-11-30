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
     @IMPORT url("css/bruce.css");
</style>
		
		<title>用户登录</title>
	</head>
	<body>
		<form action="login.action" id="loginForm" name="loginForm" method="post">
			<div	style="color:#B4BBCF;margin-top: 100px;" align="center">
				<font face="黑体" color="#2993ed" size="10"><b><%=siteName%></b>
				</font>
			</div>
			<div align="center" style="margin-top: 100px;">
				<table style="width: 50%;" border="0" id='formTable'>
					<tr>
						<td><div style="color:#79797a; font-family:'宋体';font-size:14px;">用户名：</div></td>
						<td style="width: 30%">
							<input type="text" id="loginName" name='loginName' size="20" style="width:150px;"/>
						</td>
						<td style='width:30%'><div id='loginNameTip'></div></td>
					</tr>
					<tr>
						<td><div style="color:#79797a; font-family:'宋体';font-size:14px;">密&nbsp;&nbsp;码：</div></td>
						<td style="width: 30%">
							<input type="password" id="password" name='password' size="20" style="width:150px;"/>
						</td>
						<td style='width:30%'><div id='passwordTip'></div></td>
					</tr>
				</table>
			</div>
			<div style="text-align:center; width:200px;margin:0 auto;">
				 <div class="btn" style="margin:0 auto;float:left">
          <div class="btn_l"></div>
          <button class="btn_m" name="submit" id="submit">登录</button>  
          <div class="btn_r"></div>
       </div>
       
				 <div class="btn" style="margin-left:10px; float:left">
          <div class="btn_l"></div>
          <div class="btn_m"> <a href="#" class="txt" name="Reset" id="resetBtn">重填</a>    </div>
          <div class="btn_r"></div>
       </div>
       <div style="margin:0 auto;">
           <input type="checkbox" name="ifSave" id="ifSave" checked="checked" title="记住用户名和密码"/>
           <label for="ifSave">记住</label>	
       </div>
			</div>
		</form>
	</body>
</html>