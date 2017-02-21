<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>微博系统后台-登陆界面 | Sign In by www.865171.cn</title>
<link rel="stylesheet" href="css/reset.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen" />
<script type="text/javascript" src="scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
<script type="text/javascript" src="scripts/facebox.js"></script>
<script type="text/javascript" src="scripts/jquery.wysiwyg.js"></script>
</head>
<body id="login">
<div id="login-wrapper" class="png_bg">
  <div id="login-top">
    <h1>MicroBlog BackStrone</h1>
    <!-- Logo (221px width) -->
    <a href="#"><img id="logo" src="images/log.png" alt="Benben.com Admin logo" /></a> </div>
  <!-- End #logn-top -->
  <div id="login-content">
    <form action="AdminLoginServlet" method="post">
      <p>
        <label>用户名</label>
        <input class="text-input" name="aname" type="text" />
      </p>
      <div class="clear"></div>
      <p>
        <label>密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
        <input class="text-input" name="apwd" type="password" />&nbsp;	 </p>
      
      
      <div class="clear"></div>
      <p>
        <input class="button" type="submit" value="登陆" />
      </p>
    </form>
  </div>
   
</div>
<!-- End #login-wrapper -->
</body>
</html>
