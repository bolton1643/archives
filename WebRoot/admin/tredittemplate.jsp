<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%@include file="common/global.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <script type="text/javascript" src="js/jquery-last.js"></script>
  <script type="text/javascript" src="js/ligerui.all.js"></script>
  <link href="css/weebox/weebox.css" rel="stylesheet" type="text/css"/>
  <link href="css/validator/validator.css" rel="stylesheet" type="text/css"/>  
  <link href="css/ligerui-all.css" rel="stylesheet" type="text/css"/>
  <link href="css/bruce.css" rel="stylesheet" type="text/css"/>
<title>编辑子模板</title>
<script type="text/javascript">
function testFunction(id){
  var postUrl = "trDel.action";
  var d = "id="+id;
  if ((window.confirm('你确定要删除吗？')==true))
  {//beginofif
    if ((window.confirm('删除后数据无法恢复，请再次确认？')==true))
    {//beginofif
        $.ajax({
             type: "post",
             url:postUrl,
             dataType:"html",
             data:d,
             error:function(){alert('error');},
             success:function(data,textStatus)
             {//begin of suc
                if(data == 'success')
                {//begin of if
                }//endofif
             }//endof suc
        });//end of ajax 
    }//endof if
  }//end of if
  else
  {//beginofelse
      return; //返回 下面的代码不执行
  }//endofelse
}

function rm(theRow){
  var tb = document.getElementById("insertPosition");
  tb.removeChild(theRow);
}

$(document).ready(function(){
    $("#btn").click(function(){
      var insertPos = $("#insertPosition");
      var trNode = $('<li><span class="bruce_page_txt_150"><input class="bruce_page_txt_input" name="dNotes" id="dNotes" type="text"/></span><span class="bruce_page_txt_150"> <select id="dType" name="dType" ltype="select" class="l-hidden"><option>整数</option><option>字符串</option><option>日期</option><option>小数</option><option>文件</option></select> </span> <span class="bruce_page_txt_150"><input class="bruce_page_txt_input" name="dLength" id="dLength" type="text"/></span> <span class="bruce_page_txt_150" > <select id="dAllowNull" name="dAllowNull" ltype="select" class="l-hidden"><option>允许为空</option><option>不允许为空</option></select></span> <span class="bruce_page_txt_150"> <div class="btn" style="margin:0 auto;float:left"> <div class="btn_l"></div> <input class="btn_m" type="button" value="删除"  onclick="rm(this.parentNode.parentNode.parentNode);"/> <div class="btn_r"></div> </div> </span> </li>');
      //var trNode = $("<tr align='left'><td><input name='dNotes' id='dNotes' type='text'/></td><td><select id='dType' name='dType'><option>整数</option><option>字符串</option><option>日期</option><option>小数</option><option>文件</option></select></td><td><input name='dLength' id='dLength' value='11' type='text'/></td><td><select id='dAllowNull' name='dAllowNull'><option>允许为空</option><option>不允许为空</option></select></td><td><input type='button' value='删除' onclick='rm(this.parentNode.parentNode);'/></td></tr>");
      insertPos.append(trNode);
      $("#trEditForm").ligerForm();
    });//end
    $("#trEditForm").ligerForm();
});
</script>

</head>
<body>
<div class="bruce_page_txt">
<ul style="list-style-type:none;width:800px;margin:10px 10px;">
  <li>
     <span class="bruce_page_txt_80">
       <div class="btn">
        <div class="btn_l"></div>
        <div class="btn_m"><a class="txt" href="trGet.action?id=<s:property value="id" />" target="blank">查看模板</a> </div>
        <div class="btn_r"></div>
       </div>
     </span>
     <span class="bruce_page_txt_80">
       <div class="btn">
          <div class="btn_l"></div>
          <div class="btn_m"><a class="txt" href="javascript:testFunction(<s:property value="id" />)">删除模板</a> </div>
          <div class="btn_r"></div>          
        </div>
     </span>
   </li>
   <form action="trDoEditTemplate.action" id="trEditForm" name="trEditForm" method="post">
    <s:hidden name="id"></s:hidden>
    <s:hidden name="parentId"></s:hidden>
    <s:hidden name="expanded"></s:hidden>
    <input type="hidden" name="isfolder" id="isfolder" value="0"/> 
    <input type="hidden" name="openurl" id="openurl" value="dList.action"/> 
  <li>
    <span style="width:800px">
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
        <s:iterator id="mList" value="mList" status="st">
           <s:if test="dName.lastIndexOf('_PHOTO')>=0 "></s:if>
           <s:elseif test="(dName=='ID' or dName='ID2')">
           <li>
             <span class="bruce_page_txt_150">编号</span>
             <span class="bruce_page_txt_150">整数</span>
             <span class="bruce_page_txt_150">不允许为空</span>
             <span class="bruce_page_txt_150">&nbsp</span>
           </li>
           </s:elseif>
           <s:else>
             <li>
               <span class="bruce_page_txt_150"><input class="bruce_page_txt_input" name='dNotes' id='dNotes' type='text' value="<s:property value="dNotes" />"/></span>
               <span class="bruce_page_txt_150">
                 <select name="dType" ltype="select" class="l-hidden">
                   <s:if test="%{dType=='整数'}" ><option selected="selected">整数</option></s:if>
                   <s:else><option >整数</option></s:else>
                   <s:if  test="%{dType=='字符串'}" ><option selected="selected">字符串</option></s:if>
                   <s:else><option >字符串</option></s:else>
                   <s:if  test="%{dType=='日期'}" ><option selected="selected">日期</option></s:if>
                   <s:else><option >日期</option></s:else>   
                   <s:if  test="%{dType=='小数'}" ><option selected="selected">小数</option></s:if>
                   <s:else><option >小数</option></s:else>  
                   <s:if  test="%{dType=='文件'}" ><option selected="selected">文件</option></s:if>
                   <s:else><option >文件</option></s:else>
                  </select>
               </span>
               <span class="bruce_page_txt_150"><input class="bruce_page_txt_input" name='dLength' id='dLength' type='text' value="<s:property value="dLength" />"/></span>
               <span class="bruce_page_txt_150">
                 <select name="dAllowNull" ltype="select" class="l-hidden">
                   <s:else><option >varchar</option></s:else>
                   <s:if  test="%{dAllowNull=='允许为空'}" ><option selected="selected">允许为空</option></s:if>
                   <s:else><option >允许为空</option></s:else>   
                   <s:if  test="%{dAllowNull=='不允许为空'}" ><option selected="selected">不允许为空</option></s:if>
                   <s:else><option >不允许为空</option></s:else>   
                   </select>
                 </span>
               <span class="bruce_page_txt_150">
                   <div class="btn" style="margin:0 auto;float:left">
                     <div class="btn_l"></div>
                     <input class="btn_m" type='button' value='删除'  onclick='rm(this.parentNode.parentNode.parentNode);'/>
                     <div class="btn_r"></div>
                   </div>
                 </span>
             </li>
          </s:else>
        </s:iterator>        
        </ul>
        </div><!-- line2 for table -->
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
  </form>
</ul> 
</div>
</body>
</html>