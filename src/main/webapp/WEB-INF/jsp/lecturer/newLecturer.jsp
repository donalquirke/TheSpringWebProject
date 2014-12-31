<%@ include file="/WEB-INF/jsp/include.jsp"%>

<form:form method="POST" action="addNew" modelAttribute="lecturer">
<%-- generates http request /lecturer/addNew  --%>

<form:errors path="firstName" class="notification error" style="display:block"></form:errors>
<form:errors path="lastName" class="notification error" style="display:block"></form:errors>
<form:errors path="email" class="notification error" style="display:block"></form:errors>

 <div class="ui-field-contain">
 <form:label path="lectId">Lecturer ID</form:label>
 <form:input path="lectId" placeholder="Your Lecturer ID.." value=""/>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="firstName">First Name</form:label>
 <form:input path="firstName" placeholder="Your First Name.." value=""/>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="lastName">Last Name</form:label>
 <form:input path="lastName" placeholder="Your Last Name.."  value=""/>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="email">Email</form:label>
 <form:input path="email" placeholder="Your Email.."   value=""/>
 </div>
 
 <input data-theme="b" type="submit" data-icon="check" value="Save" />
  </form:form>