<%@ include file="/WEB-INF/jsp/include.jsp"%>

<form:form method="GET" enctype="multipart/form-data" data-ajax="false" action="search/{studentAutoID}" modelAttribute="deferral">

<form:errors path="studentAutoID" class="notification error" style="display:block"></form:errors>
<form:errors path="*" cssClass="errorblock" element="div" />

<h2>Please enter Student ID to search: </h2>

 <div class="ui-field-contain">
 <form:label path="studentAutoID">Student ID</form:label>
 <form:input path="studentAutoID" placeholder="Student ID.."  value=""/>
 <form:errors path="studentAutoID" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>

 
 <input data-theme="b" type="submit" data-icon="search" value="Search" />
  </form:form>