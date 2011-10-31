<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%@ page contentType="text/html; charset=UTF-8"%>
	<%@include file="common/global.jsp"%>
	<head>
	</head>
	<body>
		<div style="padding-top: 30px">

		</div>
		<div id="systemFailed"
			style="text-align: left; padding-left: 80px; padding-right: 80px;">
			<s:property value="exception" />
		</div>
		<br />
		<div
			style="text-align: left; padding-left: 80px; padding-right: 80px;">
			<s:property value="exceptionStack" />
		</div>

	</body>
</html>