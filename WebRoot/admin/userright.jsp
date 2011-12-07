<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@include file="common/global.jsp"%>  
<html>
  <head>
    <base href="http://<%=request.getHeader("host")%><%=request.getContextPath()%>/">
    <title>档案管理系统后台</title>
    <link href="css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-last.js" type="text/javascript"></script>    
    <script src="js/ligerui.all.js" type="text/javascript"></script>
    <link href="css/bruce.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
        function submitForm()
        {//begin
            if(confirm("确认要保存么？")== true)
            {//begin
                document.getElementById("sForm").submit();        
            }//end
        }//end
        
        var tab = null;
        var accordion = null;
        var tree = null;
        $(function ()
        {//beginoffunc
            //树
            $("#tree1").ligerTree({
                url: 'UserRightJson.action?user.userId=<s:property value="user.userId"/>',
                idFieldName :'id',
                parentIDFieldName :'pid',
                onBeforeExpand: onBeforeExpand,
                onExpand: onExpand,
                checkbox: false,
                nodeWidth: 420,
                attribute: ['nodename', 'url'],
                onSelect: function (node){//begin 
                }//end
            });//endoftree
             //树
            $("#sForm").ligerForm();
            //tree = $("#tree2").ligerGetTreeManager();
            //$("#pageloading").hide();
        });//endofload
        
        function getCheckedData()       
        {//b
            var notes = tree.getChecked();
            var text = "";
            for (var i = 0; i < notes.length; i++)
            {//b
                text += notes[i].data.id + ","+notes[i].data.text;
            }//e
            alert('选择的节点数：' + text);
        }//e
                
        function onBeforeExpand(note)
        {//b
        }//e
        function onExpand(note)
        { //b
        }//e
        
        function f_heightChanged(options)
        {//b
            if (tab)
                tab.addHeight(options.diff);
            if (accordion && options.middleHeight - 24 > 0)
                accordion.setHeight(options.middleHeight - 24);
        }//e
        function f_addTab(tabid,text, url)
        { //b
            tab.addTabItem({ tabid : tabid,text: text, url: url });
        } //e
     </script> 

</head>
<body style="font-size:12px;">  
    <table border="0" style="font-size:12px;">
    <tr>
      <td align="left" valign="top">
        <div title="功能列表">
            <form id="sForm" method="post" action="SaveRight.action">
            <input type="hidden" name="user.userId" value="<s:property value='user.userId' />" />
             <ul id="tree1" style="margin-top:3px;">
             </ul>   
             </form>
        </div>
      </td>
    </tr>    
    <tr>
        <td align="center">
            <input type="button" class="bruce-button" onclick="javascript:history.go(-1);" value="返回">
            <input type="button" class="bruce-button" value="保存"   onclick="submitForm();" />
        </td>
    </tr>
    </table>
</body>
</html>
