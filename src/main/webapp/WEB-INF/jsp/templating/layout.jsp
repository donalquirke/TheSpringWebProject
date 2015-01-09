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
			
<h1 data-icon="plus">Deferral</h1>
			<ul data-role="listview" data-inset="true">
				<li><a href="<%= request.getContextPath() %>/deferral/addNew">Add</a></li>
			 	<li><a href="<%= request.getContextPath() %>/deferral/delete">Delete</a></li>
				<li><a href="<%= request.getContextPath() %>/deferral/listall">List All</a></li>  
				<li><a href="<%= request.getContextPath() %>/deferral/modify">Modify</a></li> 
				<li><a href="<%= request.getContextPath() %>/deferral/search">Search Deferrals</a></li>
				<li><a href="<%= request.getContextPath() %>/deferral/modify">Approved Deferrals</a></li>
				<li><a href="<%= request.getContextPath() %>/deferral/modify">Pending Deferrals</a></li>  
			</ul>
		</div>
		<div data-role="collapsible">
			<h1 data-icon="plus">Student</h1>
			<ul data-role="listview" data-inset="true">
				<li><a href="<%= request.getContextPath() %>/student/addNew">Add</a></li>
				<li><a href="<%= request.getContextPath() %>/student/delete">Delete</a></li>
				<li><a href="<%= request.getContextPath() %>/student/listStudents">List All</a></li>
				<li><a href="<%= request.getContextPath() %>/student/modify">Modify</a></li>
      	
			</ul>
		</div>
		<div data-role="collapsible">
			<h1 data-icon="plus">Module</h1>
			<ul data-role="listview" data-inset="true">
				<li><a href="<%= request.getContextPath() %>/module/addNew">Add</a></li>
				<li><a href="<%= request.getContextPath() %>/module/delete">Delete</a></li>
				<li><a href="<%= request.getContextPath() %>/module/listall">List All</a></li>
				<li><a href="<%= request.getContextPath() %>/module/modify">Modify</a></li>
			</ul>
		</div>
		<div data-role="collapsible">
			<h1 data-icon="plus">Lecturer</h1>
			<ul data-role="listview" data-inset="true">
				<li><a href="<%= request.getContextPath() %>/lecturer/addNew">Add</a></li>
				<li><a href="<%= request.getContextPath() %>/lecturer/delete">Delete</a></li>
				<li><a href="<%= request.getContextPath() %>/lecturer/listLecturers">List All</a></li>
				<li><a href="<%= request.getContextPath() %>/lecturer/modify">Modify</a></li> 
			</ul>
		</div>
		<div data-role="collapsible">
			<h1 data-icon="plus">Programme</h1>
			<ul data-role="listview" data-inset="true">
				<li><a href="<%= request.getContextPath() %>/programme/addNew">Add</a></li>
				<li><a href="<%= request.getContextPath() %>/programme/delete">Delete</a></li>
				<li><a href="<%= request.getContextPath() %>/programme/listProgrammes">List All</a></li>
				<li><a href="<%= request.getContextPath() %>/programme/modify">Modify</a></li>
				
			</ul>
		</div>	
  </div>  

	<a href="#pageone" data-rel="close" class="ui-btn ui-btn-inline ui-shadow ui-corner-all ui-btn-a ui-icon-delete ui-btn-icon-left">Close Menu</a>
	</div>
</div>

</body> 
</html>