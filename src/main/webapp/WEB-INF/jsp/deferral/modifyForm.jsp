<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
$(function() {
$("#modify").click(function(){		
	var approval = $("#approval").val(); 
	var defId=${deferral.defId};	
	var url="/deferral/modify/defId/"+defId+"/approval/"+approval;
	location.href="<%= request.getContextPath() %>"+url;
});
});
</script>
</head>

<body>
	<form:form modelAttribute="deferral">
		
		<div class="ui-field-contain">
 			<form:label path="studentId">Student ID</form:label>
 			<form:input path="studentId" value="${deferral.studentId}"
				disabled="true" />
 		</div>
 		
 		<div class="ui-field-contain">
 			<form:label path="lectId">Lecturer ID</form:label>
 			<form:input path="lectId" value="${deferral.lectId}"
				disabled="true" />
 		</div>
 
 		<div class="ui-field-contain">
 			<form:label path="programmeId">Programme ID</form:label>
 			<form:input path="programmeId" value="${deferral.programmeId}"
				disabled="true" />
 		</div>
 
 		<div class="ui-field-contain">
 			<form:label path="moduleId">Module ID</form:label>
 			<form:input path="moduleId" value="${deferral.moduleId}"
				disabled="true" />
 		</div>
 
 		<div class="ui-field-contain">
 			<form:label path="approval">Approval</form:label>
 			<form:input path="approval" id="approval" value="${deferral.approval}" />
 		</div>

		<input type="button" class="ui-btn" data-theme="b" data-icon="check"
			value="Modify" id="modify" />
	</form:form>
</body>
</html>