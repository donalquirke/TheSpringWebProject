<script type="text/javascript">
$(document).ready(function(){
	$("#ajaxBtn").click(function(){
	
	  $.ajax({url:"ajaxCall",success:function(result){
		  
	    alert(result);
	  }});
	});
});
</script>

<div data-role="header" data-fullscreen="true"> 
  
 <h1>
 Deferrals
  </h1> 
  <a href="home" class="ui-btn ui-icon-home ui-btn-icon-left">Home</a>
  <a href="#/logout" class="ui-btn ui-icon-search ui-btn-icon-left">Log out</a>
  <button type="button" id="ajaxBtn">ajax Button</button>  
   <div data-role="navbar">
      <ul>
        <li><a href="#">BSc Cloud</a></li>
        <li><a href="#">MSc Cloud</a></li>
        <li><a href="#">HDip Cloud</a></li>
      </ul>
    </div>
</div>
<%-- 
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>

<head>
<title>Header</title>
<link href="<c:url value="/resources/css/deferrals.css" />" rel="stylesheet">
</head>
<body>

<div>
<ul class="tabs">
<li><a href="/TheSpringWebProject/home">Home</a></li>
<li><a href="#">Apply For Deferral</a></li>
<li>Students</li>
<li><a href="#">View Deferrals</a></li>           
</ul> 
</div>

<div>
<img class="banner" alt="Banner" src="<c:url value="/resources/images/banner.jpg"/>">
</div>

</body>
</html>
--%>
