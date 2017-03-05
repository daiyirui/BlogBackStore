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
        <h3>��̨�û���Ϣ</h3>
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
                <th>�û����</th>
                <th>�û�����</th>
                <th>�û��Ա�</th>
                <th>ע��ʱ��</th>
                <th>�û���ɫ</th>
                <th>��ʵ����</th>
                <th>����</th>
              </tr>
            </thead>
            <tfoot>
              <tr>
                <td colspan="6">
                 <div >
                   <a href="DownLoadAdminServlet" class="current1" title="excelExport">������̨�û�</a>
                   
                </div>
                  <!-- End .pagination -->
                  <div class="clear"></div>
                </td>
              </tr>
            </tfoot>
            <tbody>
            <!--  -->    
          <c:if test="${!empty AdminsList}">
              <c:forEach items="${AdminsList}" var="admin"> 
                 <tr>
                <td>${admin.aid}</td>
                <td>${admin.aname}</td>
                <td>${admin.asex}</td>
                <td>${admin.adate}</td>
                <td>${admin.permission.pname}</td>
                <td>${admin.arealname}</td>
                <td>
                  <!-- Icons -->
                  <a href="UpdateAdminServlet?aid=${admin.aid}" title="Edit"><img src="images/icons/pencil.png" alt="Edit" /></a> 
                  <a href="javascript:if(confirm('ȷ��ɾ��${admin.aname}��̨�û�?')==true){location='DeleteAdminServlet?aid=${admin.aid}';}" title="Delete">
                     <img src="images/icons/cross.png" alt="Delete" />
                  </a>
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
