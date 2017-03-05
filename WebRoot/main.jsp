<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />    
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
	function op(){
	  open("/back/login.jsp");
	}
	</script>
    <title>My JSP 'main.jsp' starting page</title>
      </head>
  <body>
<div id="body-wrapper">
  <!-- Wrapper for the radial gradient background -->
  <div id="main-content">
    <!-- Main Content Section with everything -->
    <noscript>
    <!-- Show a notification if the user has disabled javascript -->
    </noscript>
    <!-- Page Head -->
    <c:if test="${empty sessionScope.admins }">
       <div align="center">
       <div align="center">&nbsp;</div>
       <a href="javascript:op()"><font size="10">请先登陆</font></a>
       </div>
    </c:if>
    <c:if test="${!empty sessionScope.admins }">
        <h2>Welcome ${sessionScope.admins.permission.pname}:&nbsp;&nbsp; ${sessionScope.admins.aname},登陆!   </h2>
    <ul class="shortcut-buttons-set">
      <li><a class="shortcut-button" href="main_ShowOrders.jsp"><span> <img src="images/icons/pencil_48.png" alt="icon" /><br />
      微博管理 </span></a></li>
      <li><a class="shortcut-button" href="main_AddProduct.jsp"><span> <img src="images/icons/paper_content_pencil_48.png" alt="icon" /><br /> 
        评论管理 </span></a></li>
      <li><a class="shortcut-button" href="ShowImage.jsp"><span> <img src="images/icons/image_add_48.png" alt="icon" /><br />
        收藏管理</span></a></li>
   <c:if test="${sessionScope.admins.permission.pname eq '管理员'}">
       <li><a href="AddEmp.jsp" class="shortcut-button"><span> <img src="images/icons/comment_48.png" alt="icon" /><br />
      权限管理</span></a></li>
   </c:if>     
   <c:if test="${sessionScope.admins.permission.pname eq '后台用户'}">
       <li><a href="AddEmp.jsp" class="shortcut-button"><span> <img src="images/icons/clock_48.png" alt="icon" /><br />
      关注度比较</span></a></li>
   </c:if>    
    </ul>
    </c:if>
  
    <!-- End .shortcut-buttons-set -->
    <div class="clear"></div>
    <!-- End .clear -->
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>系统信息</h3>
        <ul class="content-box-tabs">
          <!-- href must be unique and match the id of target div -->
        </ul>
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content">
        <div class="tab-content default-tab" id="tab1">
          <!-- This is the target div. id must match the href of this div's tab -->
          <table>
            <tbody>
              <tr>
                <td>mysql版本</td>
                <td><a href="#" title="title">6.0</a></td>
              </tr>
              <tr>
                <td>服务器版本</td>
                <td><a href="#" title="title">Apache-Tomcat-7.0.5</a></td>
              </tr>
              <tr>
                <td>服务器端口：</td>
                <td>8686</td>
              </tr>
            </tbody>
          </table>
        </div>
        <!-- End #tab1 -->
        <div class="tab-content" id="tab2"></div>
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
</html>
