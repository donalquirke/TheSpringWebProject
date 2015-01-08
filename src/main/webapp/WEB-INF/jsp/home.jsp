<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<%-- <link href="<c:url value="/resources/css/deferrals.css" />" rel="stylesheet"> --%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome page</title>
</head>
<body>
<img alt=CitCloud" src="resources/images/citcloud.jpg" width="950" height="150">
<div id="messageTest">
<h3>Cork Institute of Technology - Deferral System</h3>
<h5>${message}</h5>

</div>
 
<table>
<tr>
	<td class="deferralDescription">CIT are currently accepting applications for deferral of modules. 
			If you wish to defer a module please click here</td>
	<td><a href="deferral/addNewDeferralForStudent"><img alt="DeferralApply" src="resources/images/deferModule.png" width="180" height="40"></a></td>
</tr>

<tr>

<td class="deferralDescription">CIT are currently accepting applications for deferral of programmes. 
		If you wish to defer a programme please click here</td>
<td><a href="#"><img alt="DeferralApply" src="resources/images/deferProgramme.png" width="180" height="40"></a></td>
</tr>

<!-- 
<tr>
	<td class="deferralDescription">To send an email please click here</td>
	<td><a href="createEmail"><img alt="ContactUs" src="resources/images/contactBtn.png" width="180" height="40"></a></td>
</tr>  -->

</table>


</body>
</html>