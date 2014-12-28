<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
$(function() {
$("#modify").click(function(){		
	var email = $("#email").val(); 
	var studentId=${student.studentId};	
	var url="/student/modify/studentId/"+studentId+"/email/"+email;
	location.href="<%= request.getContextPath() %>"+url;
});
});
</script>
</head>

<body>
	<form:form modelAttribute="student">
		
		<div class="ui-field-contain">
 			<form:label path="studentId">Student ID</form:label>
 			<form:input path="studentId" value="${student.studentId}"
				disabled="true" />
 		</div>
 		
 		<div class="ui-field-contain">
 			<form:label path="firstName">First Name</form:label>
 			<form:input path="firstName" value="${student.firstName}"
				disabled="true" />
 		</div>
 
 		<div class="ui-field-contain">
 			<form:label path="lastName">lastName</form:label>
 			<form:input path="lastName" value="${student.lastName}"
				disabled="true" />
 		</div>
 
 		<div class="ui-field-contain">
 			<form:label path="email">Email</form:label>
 			<form:input path="email" value="${student.email}"
				 />
 		</div>

		<input type="button" class="ui-btn" data-theme="b" data-icon="check"
			value="Modify" id="modify" />
	</form:form>
</body>
</html>