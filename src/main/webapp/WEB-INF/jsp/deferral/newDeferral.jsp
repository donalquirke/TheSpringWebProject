<%@ include file="/WEB-INF/jsp/include.jsp"%>

<form:form method="POST" enctype="multipart/form-data" data-ajax="false" action="addNew" modelAttribute="deferral">

<form:errors path="studentId" class="notification error" style="display:block"></form:errors>
<form:errors path="lectId" class="notification error" style="display:block"></form:errors>
<form:errors path="programmeId" class="notification error" style="display:block"></form:errors>
<form:errors path="moduleId" class="notification error" style="display:block"></form:errors>
<form:errors path="approval" class="notification error" style="display:block"></form:errors>
<form:errors path="*" cssClass="errorblock" element="div" />

 <div class="ui-field-contain">
 <form:label path="studentId">Student ID</form:label>
 <form:input path="studentId" placeholder="Student ID.."  value=""/>
 <form:errors path="studentId" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 <div class="ui-field-contain">
 <form:label path="lectId">Lecturer ID</form:label>
 <form:input path="lectId" placeholder="Lecturer ID.."   value=""/>
 <form:errors path="lectId" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 <div class="ui-field-contain">
 <form:label path="programmeId">Programme ID</form:label>
 <form:input path="programmeId" placeholder="Programme ID.."   value=""/>
 <form:errors path="programmeId" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 <div class="ui-field-contain">
 <form:label path="moduleId">Module ID</form:label>
 <form:input path="moduleId" placeholder="Module ID.."   value=""/>
 <form:errors path="moduleId" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 <div class="ui-field-contain">
 <form:label path="approval">Approval</form:label>
 <form:input path="approval" placeholder="Approval..(true/false)"   value=""/>
 <form:errors path="approval" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 
<div class="ui-field-contain">
<form:label path="file">File</form:label>
<form:input path="file" type="file"/> 
</div>
 
 <input data-theme="b" type="submit" data-icon="check" value="Save" />
  </form:form>