<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	    <base href="http://<%=request.getHeader("host")%><%=request.getContextPath()%>/" />
		<title>JasperReports 使用示例</title>
	</head>
	<body>
		<a href="HTML.action">HTML</a>
		<br>
		<a href="PDF.action">PDF</a>
		<br>
		<a href="XML.action">XML</a>
		<br>
		<a href="CSV.action">CSV</a>
		<br>
		<a href="XLS.action">XLS</a>
	</body>
</html>
