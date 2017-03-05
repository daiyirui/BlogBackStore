<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>Benben Admin</title>
<link rel="stylesheet" href="css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen" />
<script type="text/javascript" src="scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
<script type="text/javascript" src="scripts/facebox.js"></script>
<script type="text/javascript" src="scripts/jquery.wysiwyg.js"></script>
<script type="text/javascript">
function SetChecked(boxname) {
	f=document.form1;
	for( i=0 ; i<f.elements.length ; i++) {
		if (f.elements[i].name==boxname) {
		f.elements[i].checked=true;
		}
	}
}
function SetReverseChecked(boxname) {
	f=document.form1;
	for( i=0 ; i<f.elements.length ; i++) {
		if (f.elements[i].name==boxname) {
			if(f.elements[i].checked==true)
			{
				f.elements[i].checked=false;
			}else{
				f.elements[i].checked=true;
			}
		}
	}
}
/*批量删除，判断是否未选by dy*/
function checkBatchDel(boxname) {
	var count = 0;
	f=document.form1;
	var s=document.getElementById("form1");	
	for( i=0 ; i<f.elements.length ; i++) {
		if (f.elements[i].name==boxname) {
			if(f.elements[i].checked==true)
			{
				count++;
			}
		}
	}
	if(count>0)
	{
		if(confirm("确定批量删除前台用户?")){
		   s.submit();
		}
		
		//return true;
	}
	else
	{	
		alert("请至少选择一项进行删除！");
		return false;
	}
}
</script>
<!-- -->
<style>
<!--
body{font-family:宋体; font-size:12px; padding:0px; margin:0px;}
.showWindow:hover{color:#FF0000}

.win_bg{background:#CCC; opacity:0.2; filter:alpha(opacity=20); position:absolute; left:0px; top:0px; width:100%; height:100%; z-index:998;}
.winTitle{background:#9DACBF; height:20px; line-height:20px}
.winTitle .title_left{font-weight:bold; color:#FFF; padding-left:5px; float:left}
.winTitle .title_right{float:right}
.winTitle .title_right a{color:#000; text-decoration:none}
.winTitle .title_right a:hover{text-decoration:underline; color:#FF0000}
.winContent{padding:5px;}
-->
</style>
</head>
<body>

<div id="body-wrapper">
  <!-- Wrapper for the radial gradient background -->
  <div id="main-content">
    <!-- Main Content Section with everything -->
    <noscript>
    <!-- Show a notification if the user has disabled javascript -->
    </noscript>
    <ul class="shortcut-buttons-set">
      <li></li>
    </ul>
    <!-- End .shortcut-buttons-set -->
    <div class="clear"></div>
    <!-- End .clear -->
    <div class="content-box" style="width:1200px;">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>所有前台用户信息</h3>
        <div class="clear">
        </div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content" >
        <div class="tab-content default-tab" id="tab1">
          <!-- This is the target div. id must match the href of this div's tab -->
          <form name="form1" id="form1" method="post" action="MastDeleteUserServlet">
          <table>
            <thead>
              <tr>
                <td align="center" bgcolor="lavender">&nbsp;</td>
                <td align="center" bgcolor="lavender">用户编号</td>
                <td align="center" bgcolor="lavender">用户名</td>
                <td align="center" bgcolor="lavender">用户密码</td>
                <td align="center" bgcolor="lavender">真实姓名</td>
                <td align="center" bgcolor="lavender">用户性别</td>
                <td align="center" bgcolor="lavender">用户地址</td>
                <td align="center" bgcolor="lavender">注册时间</td>
                <td align="center" bgcolor="lavender">用户学历</td>
                <td align="center" bgcolor="lavender">用户QQ</td>
                <td align="center" bgcolor="lavender">用户头像</td>
                <td align="center" bgcolor="lavender">操作</td>                
              </tr>
            </thead>
            <tfoot>
              <tr>
              	<td colspan="7">
                	<a class="button" href="javascript:;" onClick="SetChecked('chkOrdersnos');return false;">全选</a> 
        			<a class="button" href="javascript:;" onClick="SetReverseChecked('chkOrdersnos');return false;">反选</a>          
        			<input class="button" type="button" name="btnBatch" id="btnBatch" value="批量删除" onclick="return checkBatchDel('chkOrdersnos');" />
                </td>
                <td colspan="8">
             
                  <!-- End .pagination -->
                  <div class="clear">
                  </div>
                </td>
              </tr>
            </tfoot>
            <tbody>
           <!-- <c:forEach items="${sessionScope.UserList.data}" var="use" varStatus="su">  </c:forEach>-->
             <c:if test="${!empty UsersList}">
               <c:forEach items="${UsersList}" var="use"> 
                 <c:if test="${use.uremarks eq 'no'}">
                   <tr style="background-color: #CC66FF;">
                 </c:if>
               <c:if test="${use.uremarks ne 'no'}">
                   <tr>                     
               </c:if>           
                <td align="center">          
                <input type="checkbox" name="chkOrdersnos" id="chkOrdersnos" value="${use.uid}" />
                <label for="chkOrdersnos"></label></td>
                <td align="center">${use.uid}</td>
        		<td align="center">${use.uname}</td>
        		<td align="center">${use.upwd}</td>
	    		<td align="center">${use.urealname}</td>	    	
                <td align="center">${use.usex}</td>
                <td align="center">${use.uaddress}</td>
                <td align="center">${use.udate}</td>
                <td align="center">${use.uedu}</td>
                <td align="center">${use.uqq}</td>
                <td align="center"> <img src="${use.upic}" width="40" height="40" /></td>               
                <td align="center">
              <c:if test="${use.uremarks eq 'no'}">
                <a href="javascript:if(confirm('确定反禁用${use.uname}吗?')==true){location='EditUserServlet?eid=1&uid=${use.uid}';}" title="Update"><!-- 禁用用户 -->
                    	<img src="images/icons/hammer_screwdriver.png" alt="Edit" /></a>&nbsp;
              </c:if>
              <c:if test="${use.uremarks ne 'no'}">
                <a href="javascript:if(confirm('确定禁用${use.uname}吗?')==true){location='EditUserServlet?eid=2&uid=${use.uid}';}" title="Update"><!-- 禁用用户 -->
                    	<img src="images/icons/hammer_screwdriver.png" alt="Edit" /></a>&nbsp;
              </c:if>
               		 
                	<a href="javascript:if(confirm('确定删除${use.uname}吗?')==true){location='EditUserServlet?did=1&uid=${use.uid}';}" title="Delete">
                    	<img src="images/icons/cross.png" alt="Delete"/></a>
                </td>
              </tr> 
               </c:forEach>
             </c:if>  
                             
           
            <!-- forEach -->
            
            
            </tbody>
          </table>
          </form>
        </div>
        <!-- End #tab1 -->
      </div>
      <!-- End .content-box-content -->
    </div>
    <!-- End .content-box -->
    <div class="clear"></div>
    <div id="footer"> <small>
      <!-- Remove this notice or replace it with whatever you want -->
      &#169; Copyright 2011 7-UP Company | Powered by <a href="#">Benben Admin</a> | <a href="#">Top</a> </small> </div>
    <!-- End #footer -->
  </div>
  <!-- End #main-content -->
</div>
</body>
</html>
