<%@ include file="/WEB-INF/jsp/include.jsp"%>

<form:form method="POST" enctype="multipart/form-data" data-ajax="false" action="addNew" modelAttribute="deferral">

<form:errors path="studentAutoID" class="notification error" style="display:block"></form:errors>
<form:errors path="lectId" class="notification error" style="display:block"></form:errors>
<form:errors path="programmeAutoID" class="notification error" style="display:block"></form:errors>
<form:errors path="moduleAutoID" class="notification error" style="display:block"></form:errors>
<form:errors path="approval" class="notification error" style="display:block"></form:errors>
<form:errors path="*" cssClass="errorblock" element="div" />

 <div class="ui-field-contain">
 <form:label path="studentAutoID">Student ID</form:label>
 <form:input path="studentAutoID" placeholder="Student ID.."  value=""/>
 <form:errors path="studentAutoID" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 <div class="ui-field-contain">
 <form:label path="lectId">Lecturer ID</form:label>
 <form:input path="lectId" placeholder="Lecturer ID.."   value=""/>
 <form:errors path="lectId" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 <div class="ui-field-contain">
 <form:label path="programmeAutoID">Programme ID</form:label>
 <form:input path="programmeAutoID" placeholder="Programme ID.."   value=""/>
 <form:errors path="programmeAutoID" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 <div class="ui-field-contain">
 <form:label path="moduleAutoID">Module ID</form:label>
 <form:input path="moduleAutoID" placeholder="Module ID.."   value=""/>
 <form:errors path="moduleAutoID" cssClass="error" class="notification error" style="display:block"></form:errors>
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