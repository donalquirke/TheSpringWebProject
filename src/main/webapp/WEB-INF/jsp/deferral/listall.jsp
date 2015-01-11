<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:if test="${not empty deferrals}">
<table data-role="table" class="ui-responsive" data-mode="columntoggle" id="myTable">
  <thead>
    <tr>
    <th data-priority="5">Image </th>
    <th data-priority="3">Deferral ID</th>
    <th data-priority="4">Student ID</th>
    <th data-priority="1">Lecturer ID</th>
    <th data-priority="2">Programme ID</th> 
    <th data-priority="2">Module ID</th>
    <th data-priority="2">Approval</th>  
    <th data-priority="2">Form</th>  
    </tr>
  </thead>

  <tbody>
    <c:forEach var="deferral" items="${deferrals}">
    	<tr>
   	  		<td><img alt="cit" width="24" border="0" align="middle" src="<c:url value="/resources/images/cit.jpg"/>"></td>
      		<td>${deferral.defId}</td>
      		<td>${studentMap[deferral.studentAutoID].studentId}</td>
      		<td>${lecturerMap[deferral.lectId].fullName}</td>
      		<td>${programmeMap[deferral.programmeAutoID].programmeId}</td>  
      		<td>${moduleMap[deferral.moduleAutoID].moduleId}</td>
      		<td>${deferral.approval}</td>
      		<td><a href="<%= request.getContextPath() %>/deferral/downloadImage/${deferral.defId}"
						class="ui-btn">Download</a></td>        
    	</tr>
     </c:forEach>
  </tbody>
</table>			
		
</c:if>
	
<c:if test="${empty deferrals}">
	<div class="notification warning">
		No Deferrals.
	</div>
</c:if>