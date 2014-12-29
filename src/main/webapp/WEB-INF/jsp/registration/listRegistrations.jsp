<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:if test="${not empty registrations}">
<table data-role="table" class="ui-responsive" data-mode="columntoggle" id="myTable">
  <thead>
    <tr>
      <th data-priority="1">Student ID</th>
      <th data-priority="3">CRN No.</th>
      <th data-priority="2">Programme Id</th>   
    </tr>
  </thead>

  <tbody>
    <c:forEach var="registration" items="${registrations}">
    <tr>
      <td>${registration.studentId}</td>
	  <td>${registration.crnNumber}</td>
	  <td>${registration.programmeId}</td>      
    </tr>
     </c:forEach>
  </tbody>
 
</table>			
		
	</c:if>
	
	<c:if test="${empty registrations}">
	<div class="notification warning">
		No Registrations.
	</div>
	</c:if>