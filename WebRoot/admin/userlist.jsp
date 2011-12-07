<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@include file="common/global.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-last.js" type="text/javascript"></script>
<script src="js/ligerui.all.js" type="text/javascript"></script>
<link href="css/ligerui-all.css" rel="stylesheet" type="text/css"/>
<title>用户管理</title>
 <script type="text/javascript">
 var UserData = {Rows:[<s:iterator id="cl" value="cl" status="st">{"userid":<s:property value="userId"/>,"userName":"<s:property value="userName"/>","loginName":"<s:property value="loginName"/>","edit":"<a href=\"GetUserById.action?user.userId=<s:property value="userId"/>\">编辑</a>","priority":"<a href=\"UserRight.action?user.userId=<s:property value="userId"/>\">权限</a>"}<s:if test="#st.last"></s:if><s:else>,</s:else></s:iterator>],Total:2};
 $(document).ready(function(){
      $("#maingrid").ligerGrid({
         columns:[{ display: '主键',      name: 'userid', width: 100, type: 'int' },
                  { display: '用户姓名',  name: 'userName', width: 200, type: 'text' },
                  { display: '登陆名',  name: 'loginName', width: 100, type: 'text' },
                  { display: '编辑',  name: 'edit', width: 100, type: 'text' },
                  { display: '权限',  name: 'priority', width: 100, type: 'text' }
         ],//end
         usePager: false, 
         isScroll: true,
         data: UserData
      });//end
 });//end;
 </script>
</head>
<body style="padding:10px;">
    <a  class="l-button" style="width:120px;float:left; margin-left:10px;" href="admin/registe.jsp">注册用户</a>
    <div class="l-clear"></div>
    <div id="maingrid" style="margin-top:20px">
    </div>
</body>
</html>