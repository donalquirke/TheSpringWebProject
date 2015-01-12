<%@ include file="/WEB-INF/jsp/include.jsp"%>

<form:form method="POST" action="addNew" modelAttribute="module">
<%-- generates http request /module/addNew  --%>

<form:errors path="moduleId" class="notification error" style="display:block"></form:errors>
<form:errors path="crnNumber" class="notification error" style="display:block"></form:errors>
<form:errors path="name" class="notification error" style="display:block"></form:errors>
<form:errors path="lecturerAutoID" class="notification error" style="display:block"></form:errors>
<form:errors path="semesterAutoID" class="notification error" style="display:block"></form:errors>

 <div class="ui-field-contain">
 <form:label path="moduleId">Module ID</form:label>
 <form:input path="moduleId" placeholder="Enter Module ID.." value=""/>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="crnNumber">CRN No.</form:label>
 <form:input path="crnNumber" placeholder="Enter CRN no. of years.." value=""/>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="name">Module Name</form:label>
 <form:input path="name" placeholder="Enter Module Name.." value=""/>
 </div>
 
 <%-- 
 <div class="ui-field-contain">
 <form:label path="lecturerAutoID">Co-Ordinator ID</form:label>
 <form:select path="lecturerAutoID">
 	<form:options list = "${programme.lecturerList }" id = "lecturerAutoId" value = "LecturerAutoId" />
 </form:select>
 </div>
 --%>
 
 <div class="ui-field-contain">
 <form:label path="lecturerAutoID">Lecturer</form:label>
 <form:input path="lecturerAutoID" placeholder="Enter lecturerAutoID.." value=""/>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="semesterAutoID">Semester ID</form:label>
 <form:input path="semesterAutoID" placeholder="Enter Semester ID.."   value=""/>
 </div>
 
 <input data-theme="b" type="submit" data-icon="check" value="Save" />
  </form:form>