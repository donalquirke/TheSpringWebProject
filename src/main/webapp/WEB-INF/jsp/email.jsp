<%@ include file="/WEB-INF/jsp/include.jsp"%>
<form:form method="POST" action="sendEmail" modelAttribute="email">

<form:errors path="*" cssClass="errorblock" element="div" />

<div class="ui-field-contain">
 <form:label path="to">To</form:label>
 <form:input path="to" value=""/>
 <form:errors path="to" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="from">From</form:label>
 <form:input path="from" value=""/>
 <form:errors path="from" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="subject">Subject</form:label>
 <form:input path="subject" value=""/>
 <form:errors path="subject" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 
 	
<div data-role="fieldcontain">
<label for="message">Message:</label>
<textarea name="message" id="message"></textarea>
<form:errors path="message" cssClass="error" class="notification error" style="display:block"></form:errors>
</div> 

<input data-theme="b" type="submit" data-icon="check" value="Send email" />

</form:form>