<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html>
  <head>
    <base href="http://<%=request.getHeader("host")%><%=request.getContextPath()%>/" />
    <link href="css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-last.js" type="text/javascript"></script>
    <script src="js/ligerui.all.js" type="text/javascript"></script>
    <script src="js/common.js" type="text/javascript"></script>  
    <script type="text/javascript">
        function printData()
        {//begin
           var New=document.getElementsByName("reportname");
           var strNew;
           for(var i=0;i<New.length;i++)
           {//begin for
               if(New.item(i).checked){
                 strNew=New.item(i).getAttribute("value");  
                    break;
                 }//end of if
           }//end of for
           if(strNew ==null || strNew =='')
           {//begin of if
                   alert('请选择模板');
                   return;
           }else{
                   document.getElementById("rid").value= strNew;
                  document.forms[0].action="PDF2.action";
                document.forms[0].submit();        
           }//end of else    
        } //end of func
        
   var gridManager = null;    
        function queryData()
        {//begin
     if (!gridManager) return;
     
          var qColumnVals = [];
          var control = document.getElementsByName("qColumn")
     for(var i=0;i<control.length;i++){
       qColumnVals.push(control[i].value);
     }//end of for
                
     var qCondVals = [];
     var control2 = document.getElementsByName("qCond")
     for(var i=0;i<control2.length;i++){
       qCondVals.push(control2[i].value);
     }//end of for
                
     var qValueVals = [];
     var control3 = document.getElementsByName("qValue")
     for(var i=0;i<control3.length;i++){
       qValueVals.push(control3[i].value);
     }//end of for                                
                
     gridManager.setOptions(
       { parms: [{ name: 'qColumn', value: qColumnVals},
                 {name: 'qValue', value: qValueVals} ,
                 {name: 'qCond', value: qCondVals} 
                    ] }
                );
     gridManager.loadData(true);            
        }//end of func
        
        function rm(theRow){
            var tb = document.getElementById("insertPosition");
            tb.removeChild(theRow);
        }
                
        $(document).ready(function(){
            $("#qRow").click(function(){
                var insertPos = $("#insertPosition");
                var trNode = $("<div><img src='images/minus.gif' onclick='rm(this.parentNode);'/>&nbsp;<select class='qColumn' name='qColumn' style='width:100px;'><s:iterator value='cList' status='st'><option value=<s:property value='dName' />><s:property value='dNotes' /></option></s:iterator></select>&nbsp;<select name='qCond'  class='qCond'><option value='包含'>包含</option><option value='等于'>等于</option><option value='大于等于'>大于等于</option><option value='小于等于'>小于等于</option></select>&nbsp;<input type='text' name='qValue' /></div>");
                insertPos.append(trNode);
                //alert(document.getElementById("insertPosition").innerHTML)
            });
        });    
    
      var activeDialog = null;
        
    function f_openWindow(url, title, width, height)
    {//begin of func
      var dialogOptions = { width: width, height: height, title: title, url: url, buttons: [
            { text: '关闭',  onclick: function (item, dialog){ dialog.close();} }
            ], isResize: true, timeParmName: 'a'
            };//end of dia
      activeDialog = $.ligerDialog.open(dialogOptions);
    }//end of func
        
    var alert = function (content)
    {//be
        $.ligerDialog.alert(content);
    };//end of


    $(function ()
    {//begin
                //工具条
      $("#toptoolbar").ligerToolBar({ items: [
                    <s:if test="radd==1">   { text: '增加', id:'add', click: itemclick },   </s:if>                
                    <s:if test="rmodify==1">{ text: '修改', id:'modify', click: itemclick },</s:if>   
                    <s:if test="rdelete==1">{ text: '删除', id:'delete', click: itemclick },</s:if>       
                    <s:if test="rdownload==11">    { text: '下载', id:'download', click: itemclick },    </s:if> 
                    <s:if test="rprint==1">             { text: '打印', id:'print', click: itemclick },         </s:if>                                 
          { text: 'Excel', id:'excel', click: itemclick },     { text: '查询', id:'query', click: itemclick }
     ]});//end toptoolbar

            //表格
      $("#maingrid").ligerGrid({
        columns: [
                    <s:iterator value="cList" status="st">
                      { display: '<s:property value="dNotes" />', name: '<s:property value="dName" />', align: 'left', width: 100, minWidth: 40}
                      <s:if test="#st.last==false">
                        ,
                      </s:if>
              </s:iterator>], 
              dataAction: 'server', 
              url: 'dList2.action?data=1&tid=<s:property value="tid" />', 
              sortName: 'CustomerID',
         width: '100%', height: '100%', pageSize: 30,
         checkbox : true,
                       //应用灰色表头
         cssClass: 'l-grid-gray', 
         onAfterShowData: function (grid)
         {//begin of onAfter                                     
           $("td[columnindex='0']","#maingrid").hide();  //隐藏第1列  
           $("td[columnindex='1']","#maingrid").hide();  //隐藏第2列  
         },//end of onAf
         heightDiff: -6
     });//end of biaoge
             
    gridManager = $("#maingrid").ligerGetGridManager();
    $("#pageloading").hide();
  });//end of func
        
  function f_reload(){
    gridManager && gridManager.loadData(true);
  }//end of func

  function itemclick(item){ 
    if(item.id){
        switch (item.id)
        {//begin of swi
        case "Aqua":
          $("#maingrid").removeClass("l-grid-gray");
          return;
        case "Gray":
          $("#maingrid").addClass("l-grid-gray");
          return;
        case "modify":
          var rowsdata = gridManager.getCheckedRows();
          if (!rowsdata.length) 
          {//begin
            alert('请选择行');
            return;
          }//end of if
          if(rowsdata.length >1)
          {//begin
            alert("编辑时，一次只能选择一行");
            return;
          }//end of if
          var str = "";
          $(rowsdata).each(function ()
          {//begin of each
            str += this.ID;
          });//end of each
                        
                         var url = 'dEdit.action?tid=<s:property value="tid"/>&id='+str;
                         f_openWindow(url, '修改数据', 600, 350);
                       return;
                   case "delete":
                   var data = gridManager.getCheckedRows();
                    if (data.length == 0)
                      alert('请选择行');
                    else
                    {//begin of else
                      var checkedIds = [];
                      $(data).each(function ()
                      {//begin of func
                        checkedIds.push(this.ID);
                      });//end of cunc
                      $.ligerDialog.confirm('确定删除' + checkedIds.join(',') + '?', function ()
                  {//begin of func
                      var postUrl = "dDel.action";
                      var d = "id="+checkedIds.join(',')+"&tid=<s:property value="tid"/>";
                      $.ajax({
                                    type: "post",
                                    url:postUrl,
                                    dataType:"html",
                                    data:d,
                                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                                        alert('网络错误');
                                    },//end of 
                                    success:function(data,textStatus)
                                            {//begin 
                                                if(data == 'success')
                                                {//begin
                                           f_reload();
                                                }//end of if
                                            }//end of succ
                              });  //endof aj
               }); //end of confirm
             }//end of else 
             return;
           case "add":
             f_openWindow('dAdd.action?tid=<s:property value="tid"/>', '添加数据', 600, 350);
             return;
           case "query":
             var obj = document.getElementById("queryCondition");
                     if(obj.style.display=='none')
                              obj.style.display="";
                     else
                              obj.style.display='none';
              return;
            case "print":
            {//begin of
                                var obj = document.getElementById("printCondition");
                         if(obj.style.display=='none')
                                  obj.style.display="";
                         else
                                  obj.style.display='none';
                  return;                        
                            }//end of print
                            case "excel":
                                 window.open("XLS2.action?rid=<s:property value="tid"/>",'导出Excel',"fullscreen=0");
               return;                    
             }//end of switch 
          }//end of if(item)
        }//end of func
    </script>
</head>
<body style="padding:0px; overflow:hidden;"> 
<div class="l-loading" style="display:block" id="pageloading"></div> 

 <form id="form1"> 
 <input type="hidden" name="rid" id="rid" value="1" />
 <div id="topmenu"></div> 

  <div id="toptoolbar"></div> 
  <div id="printCondition" style="display:none;">
    <s:if test="tid=='t12'">
          <input type="radio" value="1" name="reportname" />设备类：案卷题名<br/>
          <input type="radio" value="2" name="reportname" />设备类：科技背脊条<br/>
          <input type="radio" value="3" name="reportname" />设备类：卷内目录<br/>
    </s:if>
    
    <s:if test="tid=='t11'">
          <input type="radio" value="4" name="reportname" />基建类：案卷题名<br/>
          <input type="radio" value="5" name="reportname" />基建类：卷内目录<br/>
          <input type="radio" value="6" name="reportname" />基建类：科技背脊条<br/>
          <input type="radio" value="7" name="reportname" />卷内备考表
    </s:if>      

      <input type="button" id="printbtn" value=" 打印 " onClick="printData();"/>
  </div>
<div class="l-panel-search" id="queryCondition" style="display:none;">
    <div>
        <img src="images/plus.gif" id="qRow"/>
            <select id="qCol" style="width:100px;" name="qColumn" class="qColumn">
                <s:iterator value="cList" status="st">
                    <option value="<s:property value="dName" />"><s:property value="dNotes" /></option>
                </s:iterator>
            </select>
            <select id="qCond" name="qCond" class="qCond"  style="width:100px;">
                <option value="包含">包含</option>
                <option value="等于">等于</option>
                <option value="大于等于">大于等于</option>
                <option value="小于等于">小于等于</option>
            </select>
            <input type="text" name="qValue" />
            <input type="button" id="searchbtn" value=" 查询 " onClick="queryData();"/>
            <br />
    </div>
            <div id="insertPosition"></div>

</div>

    <div id="maingrid" style="margin:0; padding:0"></div>

  </form>


  <div style="display:none;">
  
</div>
 
</body>
</html>