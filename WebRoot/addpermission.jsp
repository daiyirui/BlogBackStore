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
<script type="text/javascript" src="scripts/jquery.datePicker.js"></script>
<script type="text/javascript" src="scripts/jquery.date.js"></script>
<script type="text/javascript" >
function saveForm(){
  var s=document.getElementById("form1");
  if(confirm("确认添加权限?")){
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
<!-- End .clear -->
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>Add Permission</h3>
        <ul class="content-box-tabs">
          <!-- href must be unique and match the id of target div -->
        </ul>
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content">
        <div class="tab-content default-tab" id="tab1">
        <!-- This is the target div. id must match the href of this div's tab --></div>
        <!-- End #tab1 -->
        <div class="tab-content" id="tab2">
          <form id="form1" method="post" action="AddPermissionServlet">
            <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            <table width="80%" border="0">
              <tr>
                <td width="12%">权限名称：</td>
                <td width="22%"><input class="text-input large-input" type="text" id="pname" name="pname" onblur="checkpname()" /></td>
                <td width="16%"><div id="div_pname"></div></td>
                <td width="12%">权限说明：</td>
                <td width="22%"><input class="text-input large-input" type="text" id="storagetime" name="pcontent" /></td>
                <td width="16%"><div id="div_storagetime"></div></td>
              </tr>
               
              <tr>
                <td >权限Items:</td>
                <td colspan="5">
                <input type="checkbox" name="items" checked="checked" value="微博管理" />微博管理&nbsp;&nbsp;
                <input type="checkbox" name="items" checked="checked" value="前台用户管理" />前台用户管理&nbsp;&nbsp;
                <input type="checkbox" name="items" checked="checked" value="后台用户管理" />后台用户管理&nbsp;&nbsp;
                <input type="checkbox" name="items" checked="checked" value="收藏管理" />收藏管理&nbsp;&nbsp;
                <input type="checkbox" name="items" checked="checked" value="好友关注度" />好友关注度&nbsp;&nbsp;
                <input type="checkbox" name="items" checked="checked" value="评论管理" />评论管理<br/>                
                <input type="checkbox" name="items" checked="checked" value="微博热议" />微博热议&nbsp;&nbsp;
                           
                </td>
               
              </tr>
             
              <tr>
                <td>备注：</td>
                <td><input class="text-input large-input" size="100" type="text" id="displaycardsize" name="premarks" /></td>
                <td><div id="div_displaycardsize"></div></td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
            </table>
            
            <p>
              <input class="button" type="button" name="btnAdd" id="btnAdd" value="添加权限" onclick="saveForm()"/>
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
