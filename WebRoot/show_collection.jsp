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
/*����ɾ�����ж��Ƿ�δѡby dy*/
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
		if(confirm("ȷ������ɾ��ǰ̨�û�?")){
		   s.submit();
		}
		
		//return true;
	}
	else
	{	
		alert("������ѡ��һ�����ɾ����");
		return false;
	}
}
 function poe(img){
     window.open("image.jsp?id="+img,"news","width=400,height=300,scrollbars=1,toolbar=0,resizable=0");
     return;
     //open("demo.jsp");
  }  
</script>
<!-- -->
<style>
<!--
body{font-family:����; font-size:12px; padding:0px; margin:0px;}
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
        <h3>�ղ���Ϣ</h3>
        <div class="clear">
        </div>
      </div>
      <!-- End .content-box-header -->
      <div class="content-box-content" >
        <div class="tab-content default-tab" id="tab1">
          <!-- This is the target div. id must match the href of this div's tab -->
          <form name="form1" id="form1" method="post" action="MastDeleteCollectionServlet">
          <table>
            <thead>
              <tr>
                <td align="center" bgcolor="lavender">&nbsp;</td>
                <td align="center" bgcolor="lavender">�ղر��</td>
                <td align="center" bgcolor="lavender">�ղ��û�</td>
                <td align="center" bgcolor="lavender">�ղ���Ϣ</td>
                <td align="center" bgcolor="lavender">�ղ�ʱ��</td>
                <td align="center" bgcolor="lavender">�ղر�ע</td>
                <td align="center" bgcolor="lavender">�ղ�ͼ��</td>           
                <td align="center" bgcolor="lavender">����</td>                
              </tr>
            </thead>
            <tfoot>
              <tr>
              	<td colspan="5">
                	<a class="button" href="javascript:;" onClick="SetChecked('chkOrdersnos');return false;">ȫѡ</a> 
        			<a class="button" href="javascript:;" onClick="SetReverseChecked('chkOrdersnos');return false;">��ѡ</a>          
        			<input class="button" type="button" name="btnBatch" id="btnBatch" value="����ɾ��" onclick="return checkBatchDel('chkOrdersnos');" />
                </td>
                <td colspan="4">
                  <div class="pagination"> <!-- collectionList -->
                ��${sessionScope.collectionList.totalRows}������Ϣ&nbsp;&nbsp;&nbsp;��ǰ��${sessionScope.collectionList.currentPage}ҳ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  	<c:if test="${sessionScope.collectionList.currentPage > 1}">
                      <a href="ListCollectionServlet" title="First Page">&laquo; First</a>
                      <a href="ListCollectionServlet?np=${sessionScope.collectionList.currentPage - 1}"  title="Previous Page">&laquo; Previous</a> 
                    </c:if>
                      <c:forEach var="i" begin="1" end="${sessionScope.collectionList.totalPages }">
                    	<c:if test="${sessionScope.collectionList.currentPage == i}">
                        	<a href="#" class="number current" title="${i }">${i }</a> 
                        </c:if>
                    	<c:if test="${sessionScope.collectionList.currentPage != i}"> 
                    		<a href="ListCollectionServlet?np=${i }" class="number" title="${i }">${i }</a>
                        </c:if>
                	  </c:forEach>
                      <c:if test="${sessionScope.collectionList.currentPage < sessionScope.collectionList.totalPages}">
                      <a href="ListCollectionServlet?np=${sessionScope.collectionList.currentPage + 1}" 
                      	 title="Next Page">Next &raquo;</a>
                      <a href="ListCollectionServlet?np=${sessionScope.collectionList.totalPages}" 
                      	 title="Last Page">Last &raquo;</a>
                      </c:if>
                  </div>
                  <!-- End .pagination -->
                  <div class="clear">
                  </div>
                </td>
              </tr>
            </tfoot>
            <tbody>
         <c:if test="${!empty sessionScope.collectionList }">
           <c:forEach items="${sessionScope.collectionList.data}" var="coll">
             <c:if test="${coll.lremarks eq 'no'}">
               <tr style="background-color: #CC66FF;">
             </c:if>
             <c:if test="${coll.lremarks ne 'no'}">
               <tr>
             </c:if>
               <td align="center">          
                <input type="checkbox" name="chkOrdersnos" id="chkOrdersnos" value="${coll.lid}" />
                <label for="chkOrdersnos"></label></td>
                <td align="center">${coll.lid}</td>
        		<td align="center">${coll.use.uname}</td>
        		<td align="center">${coll.lcontent}</td>
	    		<td align="center">${coll.ldate}</td>	    	
                <td align="center">
                  <c:if test="${coll.lremarks eq 'no'}">
                                                          ���ղ��ѽ���
                  </c:if>
             <c:if test="${coll.lremarks ne 'no'}">
              ${coll.lremarks}
             </c:if>              
                </td>                
                <td align="center">
                <c:if test="${coll.limages ne null}">
                   <a href="javascript:poe('${coll.limages}')">���ͼƬ</a>
                </c:if>
                <c:if test="${coll.limages eq null}">
                ������ͼƬ</c:if>
                </td>               
                <td align="center">
                 <c:if test="${coll.lremarks eq 'no'}">
                    <a href="javascript:if(confirm('ȷ�������ô��ղ���?')==true){location='EditCollectionServlet?eid=1&lid=${coll.lid}';}" title="Update"><!-- �����û� -->
                    	<img src="images/icons/hammer_screwdriver.png" alt="Edit" /></a>&nbsp;
                 </c:if>
                 <c:if test="${coll.lremarks ne 'no'}"> 
                <a href="javascript:if(confirm('ȷ�����ô��ղ���?')==true){location='EditCollectionServlet?eid=2&lid=${coll.lid}';}" title="Update"><!-- �����û� -->
                    	<img src="images/icons/hammer_screwdriver.png" alt="Edit" /></a>&nbsp;
               	</c:if>
                	<a href="javascript:if(confirm('ȷ��ɾ�����ղ���?')==true){location='EditCollectionServlet?did=1&lid=${coll.lid}';}" title="Delete">
                    	<img src="images/icons/cross.png" alt="Delete"/></a>
                </td>
              </tr>
             
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
