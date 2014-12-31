<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
$(function() {
$("#modify").click(function(){		
	var email = $("#email").val(); 
	var lectId=${lecturer.lectId};	
	var url="/lecturer/modify/lectId/"+lectId+"/email/"+email;
	location.href="<%= request.getContextPath() %>"+url;
});
});
</script>
</head>

<body>
	<form:form modelAttribute="lecturer">
		
		<div class="ui-field-contain">
 			<form:label path="lectId">Lecturer ID</form:label>
 			<form:input path="lectId" value="${lecturer.lectId}"
				disabled="true" />
 		</div>
 		
 		<div class="ui-field-contain">
 			<form:label path="firstName">First Name</form:label>
 			<form:input path="firstName" value="${lecturer.firstName}"
				disabled="true" />
 		</div>
 
 		<div class="ui-field-contain">
 			<form:label path="lastName">lastName</form:label>
 			<form:input path="lastName" value="${lecturer.lastName}"
				disabled="true" />
 		</div>
 
 		<div class="ui-field-contain">
 			<form:label path="email">Email</form:label>
 			<form:input path="email" value="${lecturer.email}"
				 />
 		</div>

		<input type="button" class="ui-btn" data-theme="b" data-icon="check"
			value="Modify" id="modify" />
	</form:form>
</body>
</html>