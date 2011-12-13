<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en">
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-dojo-tags" prefix="sx" %>
<%@include file="common/global.jsp" %>
    <head>
        <base href="http://<%=request.getHeader("host")%><%=request.getContextPath()%>/" />
        <title>数据管理</title>
        <link type="text/css" href="css/ligerui-all.css" rel="stylesheet" />
        <link type="text/css" href="css/bruce.css" rel="stylesheet" />
        <script type="text/javascript" src="js/jquery-last.js"></script>
        <script type="text/javascript" src="js/ligerui.all.js"></script>
        <script type="text/javascript">
        $(document).ready(function(){            
            $("#form1").ligerForm();
        });//endoffunc
        </script>
    </head>
<body>    
<s:form action="dDoAdd.action" theme="simple" name="form1" id="form1" method="post">
    <input type="hidden" name="tid" id="tid" value="${tid}"/>
<div class="bruce_txt">
   <ul>
    <s:iterator value="cList" status="st">
            <s:if test="dName=='ID'">
            </s:if>
            <s:elseif test="dName=='ID2'">
            </s:elseif>
            <s:elseif test="dType=='日期'">
            <li>
                <span class="bruce_page_txt_80"><s:property value="dNotes" /></span>
                <span class="bruce_page_txt_200"><INPUT type="text" name=<s:property value="dName" /> id="datepicker" width="30"/></span>
            </li>                
            </s:elseif>
            <s:elseif test="dName.lastIndexOf('_PHOTO')>=0">
              <li>
              <span class="bruce_page_txt_80"><s:property value="dNotes" /></span>
              <span class="bruce_page_txt_200"><INPUT type="text" name="<s:property value="dName" />" id="<s:property value="dName" />"/></span>                        
              <span class="bruce_page_txt_90"><input class="bruce-button" type="button" name="选择图片" value="选择图片" onClick="window.open('page/d_upload.jsp?p=notice&cid=<s:property value="dName" />','fileUpload','width=480,height=320')"/></span>
               </li>    
            </s:elseif>
            <s:elseif test="dType=='文件'">
               <li>
                <span class="bruce_page_txt_80"><s:property value="dNotes" /></span>
                <span class="bruce_page_txt_200"><INPUT type="text" name="<s:property value="dName" />" id="<s:property value="dName" />"/></span>                        
                <span class="bruce_page_txt_90"><input class="bruce-button" type="button" name="选择文件" value="选择文件" onClick="window.open('page/d_upload.jsp?p=notice&cid=<s:property value="dName" />','fileUpload','width=480,height=320')"/></span>            
               </li>    
            </s:elseif>
            <s:else>
                <li>
                <span class="bruce_page_txt_80"><s:property value="dNotes" /></span>
                <span class="bruce_page_txt_200"><INPUT  type="text" name="<s:property value="dName" />" id="<s:property value="dName" />"/></span>
                </li>
            </s:else>
    </s:iterator>

    <li>
        <span class="bruce_page_txt_80"></span>
        <span class="bruce_page_txt_200"><input class="bruce-button" type="submit" value="提交" /></span>
    </li>
</ul>
</div>

</s:form>
</body>
</html>