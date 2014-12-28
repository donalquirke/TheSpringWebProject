<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:if test="${not empty students}">
<table data-role="table" class="ui-responsive" data-mode="columntoggle" id="myTable">
  <thead>
    <tr>
    <th data-priority="4">Student ID</th>
    <th data-priority="1">First Name</th>
    <th data-priority="2">Last Name</th> 
    <th data-priority="2">Email</th> 
    </tr>
  </thead>

  <tbody>
    <c:forEach var="student" items="${students}">
    <tr>
      <td>${student.studentId}</td>
      <td>${student.firstName}</td>
      <td>${student.lastName}</td>  
      <td>${student.email}</td>      
    </tr>
     </c:forEach>
  </tbody>
 
</table>			
		
	</c:if>
	
	<c:if test="${empty students}">
	<div class="notification warning">
		No Students.
	</div>
	</c:if>