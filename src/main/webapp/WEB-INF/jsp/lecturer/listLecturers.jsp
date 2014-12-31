<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:if test="${not empty lecturers}">
<table data-role="table" class="ui-responsive" data-mode="columntoggle" id="myTable">
  <thead>
    <tr>
    <th data-priority="4">Lecturer ID</th>
    <th data-priority="1">First Name</th>
    <th data-priority="2">Last Name</th> 
    <th data-priority="2">Email</th> 
    </tr>
  </thead>

  <tbody>
    <c:forEach var="lecturer" items="${lecturers}">
    <tr>
      <td>${lecturer.lectId}</td>
      <td>${lecturer.firstName}</td>
      <td>${lecturer.lastName}</td>  
      <td>${lecturer.email}</td>      
    </tr>
     </c:forEach>
  </tbody>
 
</table>			
		
	</c:if>
	
	<c:if test="${empty lecturers}">
	<div class="notification warning">
		No Lecturers.
	</div>
	</c:if>