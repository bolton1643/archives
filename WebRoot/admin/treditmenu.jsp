<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@include file="common/global.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <script type="text/javascript" src="js/jquery-last.js"></script>
        <link href="css/ligerui-all.css" rel="stylesheet" type="text/css"/>
        <link href="css/bruce.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
            function testFunction(id){
                var postUrl = "trDel.action";
                var d = "id="+id;
                if ((window.confirm('你确定要删除吗？')==true))
                {
                    if ((window.confirm('删除后数据无法恢复，请再次确认？')==true))
                    {
                        $.ajax({
                        type: "post",
                        url:postUrl,
                        dataType:"html",
                        data:d,
                        error:function(){alert('error');},
                        success:function(data,textStatus)
                                {
                                    if(data == 'success')
                                    {
                                        //window.navigate("http://<%=request.getHeader("host")%><%=request.getContextPath()%>/"); 
                                    }
                                }
                      });
                    }
                }
                else
                {
                    return; //返回 下面的代码不执行
                }
            }
        </script>
        <title>编辑子模板</title>
    </head>

<body style="padding:10px 10px;">
<div class="bruce_page_txt">
<ul style="list-style-type:none;width:800px;margin:10px 10px;">
  <li>
     <span class="bruce_page_txt_80">
       <div class="btn">
        <div class="btn_l"></div>
        <div class="btn_m"><a href="admin/traddmenu.jsp?pid=<s:property value="id" />" class="bru_txt">增加子菜单</a> </div>
        <div class="btn_r"></div>
       </div>
     </span>
     <span class="bruce_page_txt_80">
       <div class="btn">
          <div class="btn_l"></div>
          <div class="btn_m"><a href="admin/traddtemplate.jsp?pid=<s:property value="id" />" class="bru_txt">增加子模板 </a> </div>
          <div class="btn_r"></div>
        </div>
     </span>
     <span class="bruce_page_txt_80">
       <div class="btn">
          <div class="btn_l"></div>
          <div class="btn_m"><a href="javascript:testFunction(<s:property value="id" />)" class="bru_txt">删除模板</a></div>
          <div class="btn_r"></div>
        </div>
     </span>
   </li>
   <li>
        <form action="trDoEditMenu.action" id="trAddForm" name="trAddForm" method="post">
            <s:hidden name="id"></s:hidden>
            <s:hidden name="parentId"></s:hidden>
            <s:hidden name="expanded"></s:hidden>
            <input type="hidden" name="isfolder" id="isfolder" value="1"/> 
            <div class="bruce_page_txt">
            <ul>
              <li>
                  <span class="bruce_page_txt_200">子菜单名称：</span>
                  <span class="bruce_page_txt_200"><s:textfield name="text"></s:textfield></span>
                  <span class="bruce_page_txt_200">
                <div class="bru_btn">
                    <div class="bru_btn_l"></div>
                    <div style="float:left"><input type="submit" type="submit" class="btn_m" name="submit" id="submit" value="提交"></input></div>  
                    <div class="bru_btn_r"></div>
                </div>
                   </span>
               </li>
            </ul>
            </div>
        </form>
        </li>
     </ul>
  </div>
</body>
</html>