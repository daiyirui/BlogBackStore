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
<script type="text/javascript">
function SetChecked(boxname) {
	f=document.form1;
	for( i=0 ; i<f.elements.length ; i++) {
		if (f.elements[i].name==boxname) {
		f.elements[i].checked=true;
		}
	}
}
function SetReverseChecked(boxname) {
	f=document.form1;
	for( i=0 ; i<f.elements.length ; i++) {
		if (f.elements[i].name==boxname) {
			if(f.elements[i].checked==true)
			{
				f.elements[i].checked=false;
			}else{
				f.elements[i].checked=true;
			}
		}
	}
}
/*批量删除，判断是否未选by dy*/
function checkBatchDel(boxname) {
	var count = 0;
	f=document.form1;
	var s=document.getElementById("form1");	
	for( i=0 ; i<f.elements.length ; i++) {
		if (f.elements[i].name==boxname) {
			if(f.elements[i].checked==true)
			{
				count++;
			}
		}
	}
	if(count>0)
	{
		if(confirm("确定批量删除前台用户?")){
		   s.submit();
		}
		
		//return true;
	}
	else
	{	
		alert("请至少选择一项进行删除！");
		return false;
	}
}
 function poe(img){
     window.open("image.jsp?id="+img,"news","width=400,height=300,scrollbars=1,toolbar=0,resizable=0");
     return;
     //open("demo.jsp");
  } 
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
<!-- -->
<style>
<!--
body{font-family:宋体; font-size:12px; padding:0px; margin:0px;}
.showWindow:hover{color:#FF0000}

.win_bg{background:#CCC; opacity:0.2; filter:alpha(opacity=20); position:absolute; left:0px; top:0px; width:100%; height:100%; z-index:998;}
.winTitle{background:#9DACBF; height:20px; line-height:20px}
.winTitle .title_left{font-weight:bold; color:#FFF; padding-left:5px; float:left}
.winTitle .title_right{float:right}
.winTitle .title_right a{color:#000; text-decoration:none}
.winTitle .title_right a:hover{text-decoration:underline; color:#FF0000}
.winContent{padding:5px;}
-->
</style>
</head>
<body>

<div id="body-wrapper">
  <!-- Wrapper for the radial gradient background -->
  <div id="main-content">
    <!-- Main Content Section with everything -->
    <noscript>
    <!-- Show a notification if the user has disabled javascript -->
    </noscript>
    <ul class="shortcut-buttons-set">
      <li></li>
    </ul>
    <!-- End .shortcut-buttons-set -->
    <div class="clear"></div>
    <!-- End .clear -->
    <div class="content-box" style="width:1200px;">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>微博热议信息</h3>
        <div class="clear">
        </div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content" >
        <div class="tab-content default-tab" id="tab1">
          <!-- This is the target div. id must match the href of this div's tab -->
          <form name="form1" id="form1" method="post" action="MastDeleteHotblogServlet">
          <table>
            <thead>
              <tr>              
                <td align="center" style="width: 60px;" bgcolor="lavender">热议编号</td>
                <td align="center" style="width: 60px;" bgcolor="lavender">热议状态</td>
                <td align="center" style="width: 200px;" bgcolor="lavender">热议标题</td>
                <td align="center" style="width: 170px;" bgcolor="lavender">热议items</td>
                <td align="center" style="width: 200px;" bgcolor="lavender">投票数量</td>
                <td align="center" style="width: 50px;" bgcolor="lavender">备注</td>           
                <td align="center" bgcolor="lavender">操作</td>                
              </tr>
            </thead>
            <tbody>
        <c:if test="${!empty hotBlogList}">
          <c:forEach items="${hotBlogList}" var="hot" >
                <tr>                
                <td align="center">${hot.bid}</td>
        		<td align="center">
        		  <c:if test="${hot.bstate==1}">在线状态 </c:if>
        		  <c:if test="${hot.bstate!=1}">下线状态 </c:if>
        		</td>
        		<td align="center">${hot.btitle} ${hot.bimages}</td>
 	    		<td align="center">
	 	    		   <table>
		                      <c:forEach items="${hot.bitems}" var="bitem">
		                           <tr><td>${bitem.bitemName}</td><td>${bitem.bitemimage}</td><td>${bitem.bvote}</td></tr>
		                      </c:forEach> 
	                    </table>   
 	    		</td>	    	
                <td align="center">
                       ${hot.bvote}<br/>
                </td>                
                <td align="center">${hot.bremarks}</td>               
                <td align="center">
                   <c:if test="${hot.bstate!=1}">
                     <a href="javascript:if(confirm('确定热议上线?')==true){location='EditHotblogServlet?eid=1&btitle=${hot.btitle}';}" title="Update"><!-- 禁用用户 -->
                    	<img src="images/icons/hammer_screwdriver.png" alt="Edit" /></a>&nbsp; 
                   </c:if>
                  <c:if test="${hot.bstate==1}">
                     <a href="javascript:if(confirm('确定热议下线?')==true){location='EditHotblogServlet?eid=2&btitle=${hot.btitle}';}" title="Update"><!-- 禁用用户 -->
                    	<img src="images/icons/hammer_screwdriver.png" alt="Edit" /></a>&nbsp; 
                   </c:if>
                            		 
                	<a href="javascript:if(confirm('确定删除此热议?')==true){location='EditHotblogServlet?did=1&btitle=${hot.btitle}';}" title="Delete">
                    	<img src="images/icons/cross.png" alt="Delete"/></a>
                </td>
              </tr>
             
             <c:if test="${su.count > sessionScope.currentpageh}">
                <tr id="${su.count}" style="display: none;">                
                <td align="center">${hot.bid}</td>
        		<td align="center">
        		  <c:if test="${hot.bstate==1}">在线状态 </c:if>
        		  <c:if test="${hot.bstate!=1}">下线状态 </c:if>
        		</td>
        		<td align="center">${hot.btitle}</td>
 	    		<td align="center">
                   <c:forEach items="${hot.item}" var="ite">
                       ${ite }<br/>
                   </c:forEach>    
 	    		</td>	    	
                <td align="center">
                   <c:forEach items="${hot.vote}" var="vot">
                       ${vot}<br/>
                   </c:forEach>
                </td>                
                <td align="center">${hot.bremarks}</td>               
                <td align="center">
                   <c:if test="${hot.bstate!=1}">
                     <a href="javascript:if(confirm('确定热议上线?')==true){location='EditHotblogServlet?eid=1&btitle=${hot.btitle}';}" title="Update"><!-- 禁用用户 -->
                    	<img src="images/icons/hammer_screwdriver.png" alt="Edit" /></a>&nbsp; 
                   </c:if>
                  <c:if test="${hot.bstate==1}">
                     <a href="javascript:if(confirm('确定热议下线?')==true){location='EditHotblogServlet?eid=2&btitle=${hot.btitle}';}" title="Update"><!-- 禁用用户 -->
                    	<img src="images/icons/hammer_screwdriver.png" alt="Edit" /></a>&nbsp; 
                   </c:if>
                            		 
                	<a href="javascript:if(confirm('确定删除此热议?')==true){location='EditHotblogServlet?did=1&btitle=${hot.btitle}';}" title="Delete">
                    	<img src="images/icons/cross.png" alt="Delete"/></a>
                </td>
              </tr>
             </c:if>
          </c:forEach>
        </c:if>
                
            <!-- forEach -->
            
            
            </tbody>
          </table>
          </form>
        </div>
        <!-- End #tab1 -->
      </div>
      <!-- End .content-box-content -->
    </div>
    <!-- End .content-box -->
    <div class="clear"></div>
    <div id="footer"> <small>
      <!-- Remove this notice or replace it with whatever you want -->
      &#169; Copyright 2011 7-UP Company | Powered by <a href="#">Benben Admin</a> | <a href="#">Top</a> </small> </div>
    <!-- End #footer -->
  </div>
  <!-- End #main-content -->
</div>
</body>
</html>
