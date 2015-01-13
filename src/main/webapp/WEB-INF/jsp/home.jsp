<%@ include file="/WEB-INF/jsp/include.jsp"%> 
<html>
	<head>
		<%-- <link href="<c:url value="/resources/somecss/deferrals.css" />" rel="stylesheet"> --%>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Welcome page</title>
		<%--<link href="css/bootstrap.css" rel="stylesheet" /> --%>
	</head>
	
	<body>
		
			<img alt="CitCloud" src="resources/images/citcloud.jpg" width="950" height="150">
			<div id="messageTest">
				<h3>Cork Institute of Technology - Deferrals</h3>
				<h5>${message}</h5>
			</div>
 
			<table>
				<tr>
					<td class="deferralDescription">To download Deferral Application Form please click here </td>
					<td><a href="downloadForm"><img alt="DownloadForm" src="resources/images/downloadbutton.png" width="180" height="140"></a></td>
				</tr>
		
				<tr>
					<td class="deferralDescription">CIT are currently accepting applications for deferrals </td>
					<td><a href="deferral/doDeferModule"><img alt="ModuleDeferral" src="resources/images/deferbutton.png" width="180" height="40"></a></td>
				</tr>


				<!-- 
				<tr>
					<td class="deferralDescription">To send an email please click here</td>
					<td><a href="createEmail"><img alt="ContactUs" src="resources/images/contactBtn.png" width="180" height="40"></a></td>
				</tr>  -->
			</table>
		
	</body>
</html>