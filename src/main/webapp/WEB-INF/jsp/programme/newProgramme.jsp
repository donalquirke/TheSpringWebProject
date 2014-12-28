<%@ include file="/WEB-INF/jsp/include.jsp"%>

<form:form method="POST" action="addNew" modelAttribute="programme">
<%-- generates http request /programme/addNew  --%>

<form:errors path="programmeId" class="notification error" style="display:block"></form:errors>
<form:errors path="numYears" class="notification error" style="display:block"></form:errors>
<form:errors path="coordinatorId" class="notification error" style="display:block"></form:errors>
<form:errors path="progYear" class="notification error" style="display:block"></form:errors>

 <div class="ui-field-contain">
 <form:label path="programmeId">Programme ID</form:label>
 <form:input path="programmeId" placeholder="Enter Programme ID.." value=""/>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="numYears">No. Of Years</form:label>
 <form:input path="numYears" placeholder="Enter no. of years.." value=""/>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="coordinatorId">Co-Ordinator ID</form:label>
 <form:input path="coordinatorId" placeholder="Enter Co-Ordinator ID.."  value=""/>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="progYear">progYear</form:label>
 <form:input path="progYear" placeholder="Enter year of programme.."   value=""/>
 </div>
 
 <input data-theme="b" type="submit" data-icon="check" value="Save" />
  </form:form>