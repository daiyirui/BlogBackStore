<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>Benben Admin</title>
<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="css/reset.css" type="text/css" media="screen" />
<!-- Main Stylesheet -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="css/invalid.css" type="text/css" media="screen" />
<!--                       Javascripts                       -->
<!-- jQuery -->
<script type="text/javascript" src="scripts/jquery-1.3.2.min.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript" src="scripts/simpla.jquery.configuration.js"></script>
<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="scripts/facebox.js"></script>
<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" src="scripts/jquery.wysiwyg.js"></script>
<!-- jQuery Datepicker Plugin -->
<script type="text/javascript" src="scripts/jquery.datePicker.js"></script>
<script type="text/javascript" src="scripts/jquery.date.js"></script>
		<script language="JavaScript">
	function SetChecked(boxname) {
		f = document.form1;
		for (i = 0; i < f.elements.length; i++) {
			if (f.elements[i].name == boxname) {
				f.elements[i].checked = true;
			}
		}
	}
	function SetReverseChecked(boxname) {
		f = document.form1;
		for (i = 0; i < f.elements.length; i++) {
			if (f.elements[i].name == boxname) {
				if (f.elements[i].checked == true) {
					f.elements[i].checked = false;
				} else {
					f.elements[i].checked = true;
				}
			}
		}
	}
	function checkForm(boxname) {
		f = document.form1;
		var count =0;
		for (i = 0; i < f.elements.length; i++) {
			if (f.elements[i].name == boxname) {
				if (f.elements[i].checked == true) {
					count=count+1;
					}
			}
		}
		if(count==0){
			alert("����Ϊ�գ�");
			return false;
		}
	}
   function poe(img){
     window.open("image.jsp?id="+img,"news","width=400,height=300,scrollbars=1,toolbar=0,resizable=0");
     return;
  }  
  function muckdel(){
    var s=document.getElementById("for");
    if(confirm("ȷ������ɾ��΢��?")){
       s.submit();
    }
  }
</script>
</head>
<body>
<!-- xu -->
	
<div id="body-wrapper">
  <!-- Wrapper for the radial gradient background -->
  <div id="main-content">
    <div class="content-box">
      <!-- Start Content Box -->
      <div class="content-box-header">
        <h3>΢����Ϣ</h3>
    
        <div class="clear"></div>
      </div>
      <form name="form1" id="for" method="post" onsubmit="return checkForm('chkDeptnos');" action="MastDeleteWeiboServlet" >
      <div class="content-box-content">
        <div class="tab-content default-tab" id="tab1">
          <table>
            <thead>
              <tr bgcolor="lavender">
                <th></th>
                <th>΢�����</th>
                <th>΢����Ϣ</th>
                <th>΢���û�</th>
                <th>����ʱ��</th>
                <th>ת������</th>
                <th>���۴���</th> 
                <th>����ͼƬ</th>      
                <th>��ز���</th>
              </tr>
            </thead>
            <tfoot>
              <tr>
                <td colspan="6">
                  <a class="button" href="javascript:;"
							onclick="SetChecked('chkDeptnos');return false;">ȫѡ</a>
						<a class="button" href="javascript:;"
							onclick="SetReverseChecked('chkDeptnos');return false;">��ѡ</a>
						<input type="button" onclick="muckdel()" name="btnBatch" id="btnBatch" class="button" value="����ɾ��"/>
               
                  <!-- End .pagination -->
                  <div class="clear"></div>                </td>
              </tr>
            </tfoot>
            <tbody>
             <c:if test="${!empty WeiboList}">
               <c:forEach items="${WeiboList}" var="weibo">
               <c:if test="${weibo.wremarks eq 'no'}">
                 <tr style="background-color: #CC66FF; ">
               </c:if>
               <c:if test="${weibo.wremarks ne 'no'}">
                  <tr>           
               </c:if>  
                <td>  <input type="checkbox" name="chkDeptnos" id="chkDeptnos"	value="${weibo.wid}"/>     </td>
                <td>${weibo.wid}</td>
                <td>${weibo.wcontent}</td>
                <td>${weibo.use.uname}</td>
                <td>${weibo.wdate}</td>
                <td>${weibo.wtimes}</td>
                <td>${weibo.wcountcomment}</td>
                <td>
                  <c:if test="${weibo.wimage ne null}">
                    <a href="javascript:poe('${weibo.wimage}')">���ͼƬ</a>
                  </c:if>
                <c:if test="${weibo.wimage eq null}">
                                                     ��΢��ͼƬ
                  </c:if>
                                </td>
                <td>  
                <c:if test="${weibo.wremarks eq 'no'}">
                  <a href="javascript:if(confirm('ȷ�������ô�΢����?')==true){location='EditWeiboServlet?eid=1&wid=${weibo.wid}';}" title="Update"><!-- �����û� -->
                    	<img src="images/icons/hammer_screwdriver.png" alt="Edit" /></a>&nbsp;
                </c:if>    
                <c:if test="${weibo.wremarks ne 'no'}">      
                  <a href="javascript:if(confirm('ȷ�����ô�΢����?')==true){location='EditWeiboServlet?eid=2&wid=${weibo.wid}';}" title="Update"><!-- �����û� -->
                    	<img src="images/icons/hammer_screwdriver.png" alt="Edit" /></a>&nbsp;
                </c:if>              
                   <a href="javascript:if(confirm('ȷ��ɾ����΢��?')==true){location='EditWeiboServlet?did=1&wid=${weibo.wid}';}" title="Delete">
                         <img src="images/icons/cross.png" alt="Delete" /></a>
                   </td>                                     
              </tr>
               </c:forEach>
             </c:if>
                
              
            </tbody>
          </table>
        </div>

      </div>
  </form>
    </div>
    
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
