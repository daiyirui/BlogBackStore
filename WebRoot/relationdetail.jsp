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
<script type="text/javascript">
function page(s){
   //当前第几页
   var p=document.getElementById("hid").value;
   //总行数
   var sum=document.getElementById("sum").value;
   //没页显示数量
   var curr=document.getElementById("curr").value;
   var d=0;
   if(parseInt(sum)%parseInt(curr)!=0){
      d=parseInt(parseInt(sum)/parseInt(curr))+1;
   }else{
      d=parseInt(parseInt(sum)/parseInt(curr));
   }
   if(s=='n'&&parseInt(p)==d){
       alert("已经是尾页!");
       return ;
   }else if(s=='c'&&parseInt(p)==1){
       alert("已经是首页!");
       return ;
   }
   if(parseInt(p)<=d&&parseInt(p)>=1){
       if(s=='n'){//下一页
           if(parseInt(p)==parseInt(sum)){
              document.getElementById("hid").value=sum;
           }else{
              p=parseInt(p)+1;
              document.getElementById("hid").value=p;
           }
           if(p!=0){
              var num1=parseInt(p)+(parseInt(p)-1);
              var num=parseInt(p)+(parseInt(p)-1)+parseInt(curr);
              for(var i=1;i<=parseInt(sum);i++){
                 if(i>=parseInt(num1)&&i<parseInt(num)){
                     document.getElementById(i).style.display='block';
                 }else{
                     document.getElementById(i).style.display='none';
                 }
              }
           }
       }else if(s=='c'){
          //上一页
          if(parseInt(p)==1){
              document.getElementById("hid").value=1;
          }else{
              p=parseInt(p)-1;
              document.getElementById("hid").value=p;
          }
          if(p!=0){
              var num1=parseInt(p)+(parseInt(p)-1);
              var num=parseInt(p)+(parseInt(p)-1)+parseInt(curr);
              for(var i=1;i<=parseInt(sum);i++){
                 if(i>=parseInt(num1)&&i<parseInt(num)){
                     document.getElementById(i).style.display='block';
                 }else{
                     document.getElementById(i).style.display='none';
                 }
              }
          }
       }else if(s=='f'){
           document.getElementById("hid").value=1;
           for(var i=1;i<=parseInt(sum);i++){
                 if(parseInt(i)>=1&&parseInt(i)<=parseInt(curr)){
                     document.getElementById(i).style.display='block';
                 }else{
                     document.getElementById(i).style.display='none';
                 }
           }
       }else if(s=='e'){
          //尾页
           var wei=0;
           if(parseInt(sum)%parseInt(curr)!=0){
               wei=parseInt(parseInt(sum)/parseInt(curr))+1;  
           }else{
               wei=parseInt(parseInt(sum)/parseInt(curr));
           }
           document.getElementById("hid").value=wei;
           var num1=parseInt(wei)+(parseInt(wei)-1);
           var num=parseInt(wei)+(parseInt(wei)-1)+2;
              for(var i=1;i<=parseInt(sum);i++){
                 if(i>=parseInt(num1)&&i<=parseInt(num)){
                     document.getElementById(i).style.display='block';
                 }else{
                     document.getElementById(i).style.display='none';
                 }
              }
       }
   }else{
       alert("已经是尾页");
       return;
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
    <!-- Page Head -->
    <h2><!-- End .shortcut-buttons-set -->
    </h2>
    <div class="clear"></div>
    <!-- End .clear -->
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>关注人：${sessionScope.relationDetail.use.uname}</h3>
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
                <th>关注姓名</th>
                <th>注册时间</th>
                <th>用户地址</th>                
                <th>用户头像</th>
              </tr>
            </thead>
            <tfoot>
              <tr>
                <td colspan="6">
               <div class="pagination">
       <input type="hidden" size="1" readonly="readonly" value="${sessionScope.currentpage}" id="curr" />
      共<input type="text" size="1" readonly="readonly" value="${sessionScope.totalpage}" id="sum" />关注人&nbsp;&nbsp;&nbsp;
      当前第<input type="text" size="1" readonly="readonly" value="1" id="hid" />页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;        
             <a href="javascript:page('f')" title="First Page">&laquo; First</a>
             <a href="javascript:page('c')"  title="Previous Page">&laquo; Previous</a> 
             <a href="javascript:page('n')"  title="Next Page">Next &raquo;</a>
             <a href="javascript:page('e')" title="Last Page">Last &raquo;</a>         
                  </div>
                    <div align="right"><input class="button" type="submit" onclick="history.go(-1);" value=" 返回 " /></div>
                  <!-- End .pagination -->
                  <div class="clear"></div>
                </td>
              </tr>
            </tfoot>
            <tbody>          
             <c:if test="${!empty sessionScope.relationDetail}">
               <c:forEach items="${sessionScope.relationDetail.uselist}" var="rel" varStatus="su">
               <c:if test="${su.count <= sessionScope.currentpage}">
                   <tr id="${su.count}" style="display: block;">
                <td>${su.count}</td>
                <td>${rel.uname}</td>
                <td>${rel.udate}</td>
                <td>${rel.uaddress}</td>                
                <td>
                 <img src="${rel.upic}" width="40" height="40" alt="Edit" /> 
                </td>
              </tr>
               </c:if>
                <c:if test="${su.count > sessionScope.currentpage}">
                   <tr id="${su.count}" style="display: none;">
                <td>${su.count}</td>
                <td>${rel.uname}</td>
                <td>${rel.udate}</td>
                <td>${rel.uaddress}</td>                
                <td>
                 <img src="${rel.upic}" width="40" height="40" alt="Edit" /> 
                </td>
              </tr>
               </c:if> 
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
