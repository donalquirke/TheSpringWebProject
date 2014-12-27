<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<%-- <link href="<c:url value="/resources/css/deferrals.css" />" rel="stylesheet"> --%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome page</title>
</head>
<body>

<div id="messageTest">
<h3>Our Home Page Test</h3>
<h5>${message}</h5>
<%--
<hr></hr>
<p>Formatted Date (1): <fmt:formatDate type="time" 
            value="${now}" /></p>
<p>Formatted Date (2): <fmt:formatDate type="date" 
            value="${now}" /></p>
<p>Formatted Date (3): <fmt:formatDate type="both" 
            value="${now}" /></p>
<p>Formatted Date (4): <fmt:formatDate type="both" 
            dateStyle="short" timeStyle="short" 
            value="${now}" /></p>
<p>Formatted Date (5): <fmt:formatDate type="both" 
            dateStyle="medium" timeStyle="medium" 
            value="${now}" /></p>
<p>Formatted Date (6): <fmt:formatDate type="both" 
            dateStyle="long" timeStyle="long" 
            value="${now}" /></p>
<p>Formatted Date (7): <fmt:formatDate pattern="yyyy-MM-dd" 
            value="${now}" /></p>

<hr></hr>
 --%>
</div>

<table>
<tr>

<td class="deferralDescription">CIT are currently accepting applications for deferral of modules. 
		If you wish to defer a module please click here</td>
<td><a href="#"><img alt="DeferralApply" src="resources/images/deferModule.png" width="180" height="40"></a></td>
</tr>

<tr>

<td class="deferralDescription">CIT are currently accepting applications for deferral of programmes. 
		If you wish to defer a programme please click here</td>
<td><a href="#"><img alt="DeferralApply" src="resources/images/deferProgramme.png" width="180" height="40"></a></td>
</tr>

</table>
</body>
</html>