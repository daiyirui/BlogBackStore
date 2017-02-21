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
        <h3>关注度比较</h3>
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
                <th>关注编号</th>
                <th>关注人数</th>
                <th>关注用户</th>
                <th>用户注册时间</th>                
                <th>关注详细</th>
              </tr>
            </thead>
            <tfoot>
              <tr>
                <td colspan="6">
               <div class="pagination">
               共${sessionScope.relationList.totalRows}前台用户&nbsp;&nbsp;&nbsp;当前第${sessionScope.relationList.currentPage}页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  	<c:if test="${sessionScope.relationList.currentPage > 1}">
                      <a href="ListRelationServlet" title="First Page">&laquo; First</a>
                      <a href="ListRelationServlet?np=${sessionScope.relationList.currentPage - 1}"  title="Previous Page">&laquo; Previous</a> 
                    </c:if>
                      <c:forEach var="i" begin="1" end="${sessionScope.relationList.totalPages }">
                    	<c:if test="${sessionScope.relationList.currentPage == i}">
                        	<a href="#" class="number current" title="${i }">${i }</a> 
                        </c:if>
                    	<c:if test="${sessionScope.relationList.currentPage != i}"> 
                    		<a href="ListRelationServlet?np=${i }" class="number" title="${i }">${i }</a>
                        </c:if>
                	  </c:forEach>
                      <c:if test="${sessionScope.relationList.currentPage < sessionScope.relationList.totalPages}">
                      <a href="ListRelationServlet?np=${sessionScope.relationList.currentPage + 1}" 
                      	 title="Next Page">Next &raquo;</a>
                      <a href="ListRelationServlet?np=${sessionScope.relationList.totalPages}" 
                      	 title="Last Page">Last &raquo;</a>
                      </c:if>
                  </div>
                 
                  <!-- End .pagination -->
                  <div class="clear"></div>
                </td>
              </tr>
            </tfoot>
            <tbody>
           <c:if test="${!empty sessionScope.relationList}">
             <c:forEach items="${sessionScope.relationList.data}" var="rel" varStatus="su">
                <tr>
                <td>${su.count}</td>
                <td>${rel.usertimes}</td>
                <td>${rel.use.uname}</td>
                <td>${rel.use.udate}</td>                
                <td>
                  <!-- Icons -->
                  <a href="SelectRelationDetailServlet?gid=${rel.g_id}" title="Edit"><img src="images/icons/tick_circle.png" alt="Edit" /></a> 
                </td>
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
      &#169; Copyright 2011 7-UP Company | Powered by <a href="#">Benben Admin</a> </small> </div>
    <!-- End #footer -->
  </div>
  <!-- End #main-content -->
</div>
</body>
<!-- Download From www.exet.tk-->
</html>
