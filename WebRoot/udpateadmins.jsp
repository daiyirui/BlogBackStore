<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>后台用户添加</title>
<link rel="stylesheet" href="css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen" />
<script type="text/javascript" src="scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
<script type="text/javascript" src="scripts/facebox.js"></script>
<script type="text/javascript" src="scripts/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="scripts/jquery.datePicker.js"></script>
<script type="text/javascript" src="scripts/jquery.date.js"></script>
<script type="text/javascript">
function updateadmin(){
  var s=document.getElementById("for");
  if(confirm("确认修改后台用户信息?")){
    s.submit();
  }
}
</script>
</head>
<body>
<div id="body-wrapper">
  <!-- Wrapper for the radial gradient background -->
  <div id="main-content">
    <!-- Main Content Section with everything -->
    <noscript>
    <!-- Show a notification if the user has disabled javascript -->
    </noscript>
    <!-- End .shortcut-buttons-set --><!-- End .clear -->
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>更新后台用户</h3>
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content"><!-- End #tab1 SaveBackUserServlet-->
        <div class="tab-content" style="display:block;">
          <form id="for" action="AddBackUserServlet?ap=1" method="post">
            <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            <table>
            	<tr>
            		<td style="width: 200px;"><label>后台用户名</label></td>
            		<td style="width: 1000px;">
            		<input type="hidden" name="aid" value="${sessionScope.AdminDetail.aid}" />
            		<input value="${sessionScope.AdminDetail.aname}" class="text-input small-input" type="text" id="small-input" name="aname" />
              <span class="input-notification success png_bg" style="display: none;">Successful message</span></td>
            	</tr>
            	<tr>
            		<td><label>后台用户密码</label></td>
            		<td> <input class="text-input small-input" type="password" id="small-input" name="apwd" value="${sessionScope.AdminDetail.apwd}" />
              <span class="input-notification success png_bg" style="display: none;">Successful message</span></td>
            	</tr>            	
            	<tr>
            		<td><label>后台用户权限</label></td>
            		<td>
            		<select name="a_pid" class="small-input">
            		<c:if test="${!empty sessionScope.PermissionAll}">
            		  <c:forEach items="${sessionScope.PermissionAll}" var="it">
            		     <c:if test="${sessionScope.AdminDetail.permission.pname == it.pname}">
            		       <option value="${it.pid}" selected="selected">${it.pname}</option>
            		     </c:if>
            		     <c:if test="${sessionScope.AdminDetail.permission.pname != it.pname}">
            		       <option value="${it.pid}" >${it.pname}</option>
            		     </c:if>
            		  </c:forEach>
            		</c:if>	
              </select></td>
            	</tr>
            	<tr>
            		<td><label>用户性别</label></td>
            		<td>
            		<c:if test="${sessionScope.AdminDetail.asex eq '男'}">
            		  <input type="radio" name="asex" value="男" checked="checked" />男&nbsp;
            		  <input type="radio" name="asex" value="女"/>女
            	     </c:if>
            		<c:if test="${sessionScope.AdminDetail.asex eq '女'}">
            		  <input type="radio" name="asex" value="男" />男&nbsp;
            		  <input type="radio" name="asex" value="女" checked="checked" />女
            	     </c:if>           		           		
            		</td>
            	</tr>
            	<tr>
            		<td><label>真实姓名</label></td>
            		<td><input class="text-input small-input" type="text" value="${sessionScope.AdminDetail.arealname}" id="small-input" name="arealname" />
              <span class="input-notification success png_bg" style="display: none;">Successful message</span></td>
            	</tr>
            	<tr>
            		<td><label>备注</label></td>
            		<td><input class="text-input small-input" type="text" id="small-input" value="${sessionScope.AdminDetail.aremarks}" name="aremarks" />
              <span class="input-notification success png_bg" style="display: none;">Successful message</span></td>
            	</tr>
            </table>
            <p>
              <input class="button" type="button" onclick="updateadmin()" value="更新后台用户" />
            </p>
            </fieldset>
            <div class="clear"></div>
            <!-- End .clear -->
          </form>
        </div>
        <!-- End #tab2 -->
      </div>
      <!-- End .content-box-content -->
    </div>
    <!-- End .content-box --><!-- End .content-box --><!-- End .content-box -->
    <div class="clear"></div>
    <!-- Start Notifications --><!-- End Notifications -->
    <div id="footer"> <small>
      <!-- Remove this notice or replace it with whatever you want -->
      &#169; Copyright 2011 7-UP Company | Powered by <a href="#">Benben Admin</a> | <a href="#">Top</a> </small> </div>
    <!-- End #footer -->
  </div>
  <!-- End #main-content -->
</div>
</body>
<!-- Download From www.exet.tk-->
</html>
