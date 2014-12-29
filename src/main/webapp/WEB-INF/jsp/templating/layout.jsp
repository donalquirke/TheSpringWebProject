<%@ include file="/WEB-INF/jsp/include.jsp"%>

 
<html>  
<head>
<title><tiles:insertAttribute name="title" ignore="true"></tiles:insertAttribute></title>
<%-- <link href="<c:url value="/resources/css/deferrals.css" />" rel="stylesheet">  --%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.4/jquery.mobile-1.4.4.min.css">
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.4/jquery.mobile-1.4.4.min.js"></script>
<style>
.error { 
	color: #ff0000; 
} 
.errorblock { 
	color: #000; 
	background-color: #ffEEEE; 
	border: 3px solid #ff0000; 
	padding: 8px; 
	margin: 16px; 
}
th {
    border-bottom: 1px solid #d6d6d6;
}

tr:nth-child(even) {
    background: #e9e9e9;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 600px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}

</style>
</head>

<body>
<div data-role="page">
	<!--header--> 
	<div data-role="header" data-position="fixed" data-theme="b" > 	
		<tiles:insertAttribute name="header"  />
	</div> 
	<!--main body with a panel to the left -->
	<div data-role="main" class="ui-content" id="pageone">	
		<a href="#myPanelDefault" class="ui-btn ui-btn-inline ui-corner-all ui-shadow">Menu Options</a>
		<tiles:insertAttribute name="body" />
	</div>			
		
	<!--footer-->	
	<div  data-role="footer" data-position="fixed" data-theme="b">
		<tiles:insertAttribute name="footer" />
	</div>

	<!-- Panel -->
	<div data-role="panel" id="myPanelDefault" style="text-align:center;"> 
	<div data-role="collapsibleset">
  	<div data-role="collapsible">
    <h1 data-icon="plus">Add</h1>
    	<ul data-role="listview" data-inset="true">
      	<li><a href="<%= request.getContextPath() %>/deferral/addNew">Deferral</a></li>
      	<li><a href="<%= request.getContextPath() %>/student/addNew">Student</a></li>
      	<li><a href="<%= request.getContextPath() %>/module/addNew">Module</a></li>
      	<li><a href="<%= request.getContextPath() %>/lecturer/addNew">Lecturer</a></li>
      	<li><a href="<%= request.getContextPath() %>/programme/addNew">Programme</a></li>
      	<li><a href="<%= request.getContextPath() %>/registration/addNew">Registration</a></li>
      	<li><a href="<%= request.getContextPath() %>/semester/addNew">Semester</a></li>
 		</ul>   
  	</div>
  	
   	<div data-role="collapsible">
    	<h1 data-icon="delete">Remove</h1>
    	<ul data-role="listview" data-inset="true">
    	<li><a href="<%= request.getContextPath() %>/deferral/delete">Deferral</a></li>
    	<li><a href="<%= request.getContextPath() %>/student/delete">Student</a></li>
      	<li><a href="<%= request.getContextPath() %>/module/delete">Module</a></li>
      	<li><a href="<%= request.getContextPath() %>/lecturer/delete">Lecturer</a></li>
      	<li><a href="<%= request.getContextPath() %>/programme/delete">Programme</a></li>
      	<li><a href="<%= request.getContextPath() %>/registration/delete">Registration</a></li>
      	<li><a href="<%= request.getContextPath() %>/semester/delete">Semester</a></li>
 		</ul> 
 	</div>
 	
 	<div data-role="collapsible">
    	<h1 data-icon="search">List All</h1>
    	<ul data-role="listview" data-inset="true">
    	  <li><a href="<%= request.getContextPath() %>/deferral/listall">Deferral</a></li>  
    	  <li><a href="<%= request.getContextPath() %>/student/listStudents">Student</a></li>
      	  <li><a href="<%= request.getContextPath() %>/module/listall">Module</a></li>
      	  <li><a href="<%= request.getContextPath() %>/lecturer/listal">Lecturer</a></li>
      	  <li><a href="<%= request.getContextPath() %>/programme/listProgrammes">Programme</a></li>
      	  <li><a href="<%= request.getContextPath() %>/registration/listRegistrations">Registration</a></li>
      	  <li><a href="<%= request.getContextPath() %>/semester/listall">Semester</a></li>
 		</ul>     
  	</div>
  
  	<div data-role="collapsible">
   	 	<h1 data-icon="star">Modify</h1>
    	<ul data-role="listview" data-inset="true">
     	  <li><a href="<%= request.getContextPath() %>/deferral/modify">Deferral</a></li>
    	  <li><a href="<%= request.getContextPath() %>/student/modify">Student</a></li>
      	  <li><a href="<%= request.getContextPath() %>/module/modify">Module</a></li>
      	  <li><a href="<%= request.getContextPath() %>/lecturer/modify">Lecturer</a></li>
      	  <li><a href="<%= request.getContextPath() %>/programme/modify">Programme</a></li>
      	  <li><a href="<%= request.getContextPath() %>/registration/modify">Registration</a></li>
      	  <li><a href="<%= request.getContextPath() %>/semester/modify">Semester</a></li>
 		</ul>     
  	</div>
  
  </div>  

	<a href="#pageone" data-rel="close" class="ui-btn ui-btn-inline ui-shadow ui-corner-all ui-btn-a ui-icon-delete ui-btn-icon-left">Close Menu</a>
	</div>
</div>





<%-- 
<table>
    <tr>
        <td id="header" colspan="2" align="center">
            <tiles:insertAttribute name="header"></tiles:insertAttribute>
        </td>
    </tr>
    <tr>
        
        <td id="body">
            <tiles:insertAttribute name="body"></tiles:insertAttribute>
        </td>
         <td id="login">
            <tiles:insertAttribute name="login"></tiles:insertAttribute>
        </td>
    </tr>
    <tr>
        <td id = "footer" colspan="2" align="center">
            <tiles:insertAttribute name="footer"></tiles:insertAttribute>
        </td>
    </tr>
</table>	 --%>

</body> 
</html>