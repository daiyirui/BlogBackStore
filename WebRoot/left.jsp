<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>Benben Admin</title>
<link rel="stylesheet" href="css/reset.css"type="text/css" media="screen" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen" />
<script type="text/javascript" src="scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
<script type="text/javascript" src="scripts/facebox.js"></script>
<script type="text/javascript" src="scripts/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="scripts/jquery.datePicker.js"></script>
<script type="text/javascript" src="scripts/jquery.date.js"></script>
<script type="text/javascript">
function opep(){
  alert("ssss");
  open("showadmins.jsp");
}
</script>
</head>
<body style="background: url('images/bg-body.gif');">
<div id="body-wrapper" style="width:231px;">
  <!-- Wrapper for the radial gradient background -->
  <div id="sidebar">
    <div id="sidebar-wrapper">
      <!-- Sidebar with logo and menu -->
      <h1 id="sidebar-title"><a href="#">Simpla Admin</a></h1>     
      <a href="#"><img id="logo" src="images/log.png" alt="Simpla Admin logo" /></a>    
       <c:if test="${!empty sessionScope.admins }">
          <div id="profile-links"> 您好 欢迎${sessionScope.admins.aname} 登录!
     <br />
        <br />
        <a href="" title="View the Site" target="_blank">View the Site</a> | <a href="/SevenUpLaptopStore/admins!logout.action" title="Sign Out" target="_parent">Sign Out</a>
       </div>
         <c:if test="${sessionScope.admins.permission.pname eq '管理员'}">
             <ul id="main-nav">
        <!-- Accordion Menu -->
        <li><a href="#" class="nav-top-item"> 前台用户管理 </a> 
          <ul>
            <li><a class="current" href="ListUserServlet" target="main">前台用户列表</a></li>
          </ul>
        </li>
        <li> <a href="#" class="nav-top-item"> 后台用户管理 </a>
          <ul>
            <li><a href="AdminServlet?action=listAdmins" target="main">后台用户列表</a></li>
            <li><a href="IntoBackUserServlet" target="main">添加后台用户</a></li>
             <li><a href="PermissionListServlet" target="main">后台权限列表</a></li>
             <li><a href="addpermission.jsp" target="main">后台权限添加</a></li>
          </ul>
        </li>
        <li> <a href="#" class="nav-top-item"> 评论管理 </a>
          <ul>
            <li><a href="ListCommentServlet" target="main">评论列表</a></li>
          </ul>
        </li>
        <li> <a href="#" class="nav-top-item"> 微博管理 </a>
          <ul>
            <li><a href="ListWeiboServlet" target="main">微博列表</a></li>
          </ul>
        </li>
        <li> <a href="#" class="nav-top-item"> 好友关注度比较 </a>
          <ul>
            <li><a href="ListRelationServlet" target="main">关注度列表</a></li>
            
    
          </ul>
        </li>
        <li> <a href="#" class="nav-top-item"> 微博热议 </a>
          <ul>
          <li><a href="addhotblog.jsp" target="main">添加微博热议</a></li>
            <li><a href="ListBlogHotServlet" target="main">微博热议列表</a></li>
          </ul>
        </li>
      
        	 <li> <a href="#" class="nav-top-item"> 收藏管理 </a>
          <ul>
            <li><a href="ListCollectionServlet" target="main">收藏列表</a></li>
          </ul>
        </li>       
      </ul>
         </c:if>
      <c:if test="${sessionScope.admins.permission.pname eq '后台用户'}">
         <ul id="main-nav">
        <!-- Accordion Menu -->
        <li><a href="#" class="nav-top-item"> 前台用户管理 </a> 
          <ul>
            <li><a class="current" href="ListUserServlet" target="main">前台用户列表</a></li>
          </ul>
        </li>
       
        <li> <a href="#" class="nav-top-item"> 评论管理 </a>
          <ul>
            <li><a href="ListCommentServlet" target="main">评论列表</a></li>
          </ul>
        </li>
        <li> <a href="#" class="nav-top-item"> 微博管理 </a>
          <ul>
            <li><a href="ListWeiboServlet" target="main">微博列表</a></li>
          </ul>
        </li>
        <li> <a href="#" class="nav-top-item"> 好友关注度比较 </a>
          <ul>
            <li><a href="ListRelationServlet" target="main">关注度列表</a></li>
            
    
          </ul>
        </li>
        
        	 <li> <a href="#" class="nav-top-item"> 收藏管理 </a>
          <ul>
            <li><a href="ListCollectionServlet" target="main">收藏列表</a></li>
          </ul>
        </li>       
      </ul>
      </c:if>   
      
       </c:if>
     
    
      <!-- End #main-nav -->
      <div id="messages" style="display: none">
        <!-- Messages are shown when a link with these attributes are clicked: href="#messages" rel="modal"  -->
        <h3>3 Messages</h3>
        <p> <strong>17th May 2009</strong> by Admin<br />
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus magna. Cras in mi at felis aliquet congue. <small><a href="#" class="remove-link" title="Remove message">Remove</a></small> </p>
        <p> <strong>2nd May 2009</strong> by Jane Doe<br />
          Ut a est eget ligula molestie gravida. Curabitur massa. Donec eleifend, libero at sagittis mollis, tellus est malesuada tellus, at luctus turpis elit sit amet quam. Vivamus pretium ornare est. <small><a href="#" class="remove-link" title="Remove message">Remove</a></small> </p>
        <p> <strong>25th April 2009</strong> by Admin<br />
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus magna. Cras in mi at felis aliquet congue. <small><a href="#" class="remove-link" title="Remove message">Remove</a></small> </p>
        <form action="#" method="post">
          <h4>New Message</h4>
          <fieldset>
          <textarea class="textarea" name="textfield" cols="79" rows="5"></textarea>
          </fieldset>
          <fieldset>
          <select name="dropdown" class="small-input">
            <option value="option1">Send to...</option>
            <option value="option2">Everyone</option>
            <option value="option3">Admin</option>
            <option value="option4">Jane Doe</option>
          </select>
          <input class="button" type="submit" value="Send" />
          </fieldset>
        </form>
      </div>
      <!-- End #messages -->
    </div>
  </div>
  <!-- End #sidebar --><!-- End #main-content -->
</div>
</body>
<!-- Download From www.exet.tk-->
</html>
