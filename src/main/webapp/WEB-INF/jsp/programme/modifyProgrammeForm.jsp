<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
$(function() {
$("#modify").click(function(){		
	var coordinatorId = $("#coordinatorId").val(); 
	var programmeId=${programme.programmeId};	
	var url="/programme/modify/programmeId/"+programmeId+"/coordinatorId/"+coordinatorId;
	location.href="<%= request.getContextPath() %>"+url;
});
});
</script>
</head>

<body>
	<form:form modelAttribute="programme">
		
		<div class="ui-field-contain">
 			<form:label path="programmeId">Programme ID</form:label>
 			<form:input path="programmeId" value="${programme.programmeId}"
				disabled="true" />
 		</div>
 		
 		<div class="ui-field-contain">
 			<form:label path="numYears">No. Of Years</form:label>
 			<form:input path="numYears" value="${programme.numYears}"
				disabled="true" />
 		</div>
 
 		<div class="ui-field-contain">
 			<form:label path="coordinatorId">Coordinator ID</form:label>
 			<form:input path="coordinatorId" value="${programme.coordinatorId}"
				 />
 		</div>
 
 		<div class="ui-field-contain">
 			<form:label path="progYear">Year of Programme</form:label>
 			<form:input path="progYear" value="${programme.progYear}"
				disabled="true" />
 		</div>

		<input type="button" class="ui-btn" data-theme="b" data-icon="check"
			value="Modify" id="modify" />
	</form:form>
</body>
</html>