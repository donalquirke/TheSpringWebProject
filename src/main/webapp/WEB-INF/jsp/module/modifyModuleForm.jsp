<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
$(function() {
$("#modify").click(function(){		
	var lecturerAutoID = $("#lecturerAutoID").val(); 
	var moduleAutoID=${module.moduleAutoID};	
	var url="/module/modify/moduleAutoID/"+moduleAutoID+"/lecturerAutoID/"+lecturerAutoID;
	location.href="<%= request.getContextPath() %>"+url;
});
});
</script>
</head>

<body>
	<form:form modelAttribute="module">
		
		<div class="ui-field-contain">
 			<form:label path="moduleId">Module ID</form:label>
 			<form:input path="moduleId" value="${module.moduleId}"
				disabled="true" />
 		</div>
 		
 		<div class="ui-field-contain">
 			<form:label path="crnNumber">CRN No.</form:label>
 			<form:input path="crnNumber" value="${module.crnNumber}"
				disabled="true" />
 		</div>
 		
 		<div class="ui-field-contain">
 			<form:label path="name">Module Name</form:label>
 			<form:input path="name" value="${module.name}"
				disabled="true" />
 		</div>
 
 		<div class="ui-field-contain">
 			<form:label path="lecturerAutoID">Lecturer ID</form:label>
 			<form:input path="lecturerAutoID" value="${module.lecturerAutoID}"
				 />
 		</div>
 
 		<div class="ui-field-contain">
 			<form:label path="semesterAutoID">Semester ID</form:label>
 			<form:input path="semesterAutoID" value="${module.semesterAutoID}"
				disabled="true" />
 		</div>

		<input type="button" class="ui-btn" data-theme="b" data-icon="check"
			value="Modify" id="modify" />
	</form:form>
</body>
</html>