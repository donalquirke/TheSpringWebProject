<%@ include file="/WEB-INF/jsp/include.jsp"%>

<div data-role="header" data-position="inline">  
 	<h1>
		Cork Institute of Technology
  	</h1> 
  	<a href="<c:url value="/home"/>" class="ui-btn ui-icon-home ui-btn-icon-left">Home</a>
  	<a href="<c:url value="/logout"/>" class="ui-btn ui-icon-search ui-btn-icon-left">Log out</a>
	<!--  <button type="button" id="ajaxBtn">ajax Button</button>  -->
   
	<div data-role="navbar">
 		<ul>
       		<li><a href="http://www.mycit.ie">MyCIT</a></li>
        	<li><a href="http://cloud.cit.ie">Cloud Home</a></li>
        	<li><a href="http://cloud.cit.ie/graduate-conversion-programmes/">HDip</a></li>
        	<li><a href="http://cloud.cit.ie/msc-cloud-computing/">MSc</a></li>
        	<li><a href="http://cloud.cit.ie/bsc-cloud-computing/">BSc (Hons)</a></li>
 		</ul>
 	</div>
</div>
