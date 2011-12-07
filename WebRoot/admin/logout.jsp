<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="http://<%=request.getHeader("host")%><%=request.getContextPath()%>/">
    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
   session.setAttribute("user","");
   session.setAttribute("username","");
   
   String c = request.getHeader("host")+request.getContextPath();
   response.sendRedirect("http://"+c+"/page/login.jsp");
%>

</body>
</html>