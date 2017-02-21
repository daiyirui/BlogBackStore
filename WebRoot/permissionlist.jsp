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
    <h2><!-- End .shortcut-buttons-set -->
    </h2>
    <div class="clear"></div>
    <!-- End .clear -->
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>Permission List</h3>
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
            <thead>
              <tr>
                <th>权限编号</th>
                <th>权限名称</th>
                <th>权限说明</th>
                <th>权限items</th>
                <th>权限说明</th>                
              </tr>
            </thead>
            <tfoot>
              <tr>
                <td colspan="6">
                
                <div class="pagination">               
                共${sessionScope.permissionPageBean.totalRows}权限记录信息&nbsp;&nbsp;&nbsp;当前第${sessionScope.permissionPageBean.currentPage}页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <c:forEach var="i" begin="1" end="${sessionScope.permissionPageBean.totalPages}">
                 <c:if test="${sessionScope.permissionPageBean.currentPage == i}"><a href="#" class="number current" title="${i }">${i }</a></c:if>
                 <c:if test="${sessionScope.permissionPageBean.currentPage != i}"><a href="PermissionListServlet?np=${i }" class="number" title="${i }">${i }</a>&nbsp;</c:if>
              </c:forEach>               	
                </div>
                  <!-- End .pagination -->
                  <div class="clear"></div>
                </td>
              </tr>
            </tfoot>
            <tbody>
             <c:if test="${!empty sessionScope.permissionPageBean}">
               <c:forEach items="${sessionScope.permissionPageBean.data}" var="pers">
                    <tr>
                <td>${pers.pid}</td>
                <td>${pers.pname}</td>
                <td>${pers.pcontent}</td>
                <td>
                   <c:forEach items="${pers.content}" var="cons">
                     ${cons.pcitems}<br/>
                   </c:forEach>
                </td>
                
                <td>
                  <!-- Icons -->
                  <a href="SelectDetailPermissionServlet?pid=${pers.pid}" title="Edit"><img src="images/icons/pencil.png" alt="Edit" /></a> 
                  <a href="javascript:if(confirm('确认删除此权限信息?')==true){location='DeletePermissionServlet?pid=${pers.pid}';}" title="Delete"><img src="images/icons/cross.png" alt="Delete" /></a></td>
              </tr>
               </c:forEach>
             </c:if>   
            
           
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
      &#169; Copyright 2011 7-UP Company | Powered by <a href="#">BackStrone</a> </small> </div>
    <!-- End #footer -->
  </div>
  <!-- End #main-content -->
</div>
</body>
<!-- Download From www.exet.tk-->
</html>
