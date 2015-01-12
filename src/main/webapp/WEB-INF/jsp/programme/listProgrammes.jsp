<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:if test="${not empty programmes}">
<table data-role="table" class="ui-responsive" data-mode="columntoggle" id="myTable">
  <thead>
    <tr>
      <th data-priority="1">Programme ID</th>
      <th data-priority="3">No. of Years</th>
      <th data-priority="2">Co-Ordinator</th>  
      <th data-priority="4">Year of Programme</th> 
    </tr>
  </thead>

  <tbody>
    <c:forEach var="programme" items="${programmes}">
    <tr>
      <td>${programme.programmeId}</td>
	  <td>${programme.numYears}</td>
	  <td>${lecturerMap[programme.lecturerAutoID].fullName}</td>
	  <!-- <td>${programme.lecturerAutoID}</td> -->
	  <td>${programme.progYear}</td>      
    </tr>
     </c:forEach>
  </tbody>
 
</table>			
		
	</c:if>
	
	<c:if test="${empty programmes}">
	<div class="notification warning">
		No Programmes.
	</div>
	</c:if>