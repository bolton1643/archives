<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.sw.pojo.User" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@include file="common/global.jsp"%>  
<html>
  <head>
    <base href="http://<%=request.getHeader("host")%><%=request.getContextPath()%>/">
    <title>档案管理系统后台</title>
    <link href="css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-last.js" type="text/javascript"></script>    
    <script src="js/ligerui.all.js" type="text/javascript"></script>

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
            //布局
            $("#layout1").ligerLayout({ leftWidth: 190, height: '100%', onHeightChanged: f_heightChanged });
            var height = $(".l-layout-center").height();
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
                onSelect: function (node)
                {//end

                }//end
            });//endoftree
                
             //树
            $("#tree2").ligerTree({
                url: 'UserRightJson.action?user.userId=0',
                idFieldName :'id',
                parentIDFieldName :'pid',
                onBeforeExpand: onBeforeExpand,
                onExpand: onExpand,
                checkbox: false,
                nodeWidth: 450,
                attribute: ['nodename', 'url'],
                onSelect: function (node)
                {//begin
                }//end
            });//end
                           
                //accordion = $("#accordion1").ligerGetAccordionManager();
            tree = $("#tree2").ligerGetTreeManager();
            $("#pageloading").hide();
        });//endoftree
        
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
<body style="padding:0px;">  
    
    <div id="pageloading"></div>
    <table border="1">
    <tr>
        <td align="center"><a href="javascript:history.go(-1);">返回</a></td>
        <td align="center">
            <input type="button" value="保存" onclick="submitForm();" />
        </td>
    </tr>
    <tr><td>已有权限</td><td><div>修改用户权限</div></td></tr>
    <tr>
    <td align="left" valign="top">
                     <div title="功能列表" class="l-scroll" style="width:50%">
                         <ul id="tree1" style="margin-top:3px;">
                         
                         </ul>   
                    </div>
    </td>
    <td align="left" valign="top">
                    <div title="功能列表" class="l-scroll" style="display:block;width:50%">
                        <form id="sForm" method="post" action="SaveRight.action">
                        <input type="hidden" name="user.userId" value="<s:property value='user.userId' />" />
                         <ul id="tree2" style="margin-top:3px;">
                         
                         </ul>   
                         </form>
                    </div>    
    </td>
    </tr>
    </table>

</body>
</html>
