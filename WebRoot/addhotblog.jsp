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
  function savehot(){
    var s=document.getElementById("for");
    if(confirm("确认新增微博热议?")){
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
        <h3>添加微博热议</h3>
        <div class="clear"></div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content"><!-- End #tab1 -->
        <div class="tab-content" style="display:block;">
          <form id="for" action="AddHotblogServlet" method="post">
            <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            <table>
            	<tr>
            		<td style="width: 200px;"><label>微博热议标题</label></td>
            		<td style="width: 1000px;"><input class="text-input small-input" type="text" id="small-input" name="btitle" />
              <span class="input-notification success png_bg" style="display: none;">Successful message</span></td>
            	</tr>
            	<tr>
            		<td><label>热议items：</label><br/>最多5项</td>
            		<td> 
            		<input class="text-input small-input" id="small-input" name="bitems0" /><br/>
            		<input class="text-input small-input"  id="small-input" name="bitems1" /><br/>
            		<input class="text-input small-input" id="small-input" name="bitems2" /><br/>
            		<input class="text-input small-input"  id="small-input" name="bitems3" /><br/>
            		<input class="text-input small-input"  id="small-input" name="bitems4" /><br/>
                            </td>
            	</tr>            	
            	           
            	<tr>
            		<td><label>是否上线</label></td>
            		<td><input  type="checkbox" value="1"  name="bstate" />
                  若选中则变为上线状态</td>
            	</tr>
            	<tr>
            		<td><label>备注</label></td>
            		<td><input class="text-input small-input" type="text" id="small-input" name="aremarks" />
              <span class="input-notification success png_bg" style="display: none;">Successful message</span></td>
            	</tr>
            </table>
            <p>
              <input class="button" onclick="savehot()" type="button" value="添加微博热议" />&nbsp;&nbsp;&nbsp;&nbsp;
            
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
