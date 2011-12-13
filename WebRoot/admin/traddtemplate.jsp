<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@include file="common/global.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
      <script src="js/jquery-last.js" type="text/javascript"></script>
      <script src="js/ligerui.all.js" type="text/javascript"></script>
      <link href="css/ligerui-all.css" rel="stylesheet" type="text/css"/>
      <link href="css/bruce.css" rel="stylesheet" type="text/css"/>
      <title>增加子模板</title>
        <script type="text/javascript">
            function rm(theRow){
                var tb = document.getElementById("insertPosition");
                tb.removeChild(theRow);
            }//end
            
            $(document).ready(function(){
              $("#btn").click(function(){
                var insertPos = $("#insertPosition");
                var trNode = $('<li><span class="bruce_page_txt_150"><input class="bruce_page_txt_input" name="dName" id="dName" type="text"/></span><span class="bruce_page_txt_150"> <select id="dType" name="dType" ltype="select" class="l-hidden"><option>整数</option><option>字符串</option><option>日期</option><option>小数</option><option>文件</option></select> </span> <span class="bruce_page_txt_150"><input class="bruce_page_txt_input" name="dLength" id="dLength" type="text"/></span> <span class="bruce_page_txt_150" > <select id="dAllowNull" name="dAllowNull" ltype="select" class="l-hidden"><option>允许为空</option><option>不允许为空</option></select></span> <span class="bruce_page_txt_150"> <div class="btn" style="margin:0 auto;float:left"> <div class="btn_l"></div> <input class="btn_m" type="button" value="删除"  onclick="rm(this.parentNode.parentNode.parentNode);"/> <div class="btn_r"></div> </div> </span> </li>');
                insertPos.append(trNode);
                $("#trAddForm").ligerForm();
              });//end
              $("#trAddForm").ligerForm();
            });//end of 
        </script>
    </head>
<body>
<div class="bruce_page_txt">
<ul style="list-style-type:none;width:800px;margin:10px 10px;">
  <li>
    <span style="width:800px">
            <form action="trAdd.action" id="trAddForm" name="trAddForm" method="post">
            <input type="hidden" name="parentId" id="pid" value="${param.pid}"/> 
            <input type="hidden" name="expanded" id="expanded" value="0"/>
            <input type="hidden" name="openurl" id="openurl" value="dList.action"/> 
            <input type="hidden" name="isfolder" id="isfolder" value="0"/>
      <div class="bruce_page_txt"><!-- line 2 for table -->          　
        <ul id="insertPosition">
        <li style="font-size:14px">
            <span class="bruce_page_txt_150"><font style="font-weight: bold;" >字段名称</font></span>
            <span class="bruce_page_txt_150"><font style="font-weight: bold;" >字段类型</font></span>
            <span class="bruce_page_txt_150"><font style="font-weight: bold;" >字段长度</font></span>
            <span class="bruce_page_txt_150"><font style="font-weight: bold;" >允许为空</font></span>
            <span class="bruce_page_txt_150">
              <div class="btn" style="margin:0 auto;float:left">
                <div class="btn_l"></div>
                <input class="btn_m" id="btn" type="button" value="增加" />
                <div class="btn_r"></div>
              </div>
            </span>
        </li>
      </ul>
      </div>
    </span>
    </li>
    <li>
           <span class="bruce_page_txt_4"></span>
           <span class="bruce_page_txt_4">子模板名称：</span>
           <span class="bruce_page_txt_200"><s:textfield name="text"></s:textfield></span>
           <span>
             <div class="bru_btn">
               <div class="bru_btn_l"></div>
               <input class="bru_btn_m" type="submit" value="提交"  />
               <div class="bru_btn_r"></div>
            </div>
           </span>
           <span class="bruce_page_txt_4">&nbsp</span>
        </li>
        </ul>
        </div>
    </form>
</body>
</html>