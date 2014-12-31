<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:if test="${not empty modules}">
<table data-role="table" class="ui-responsive" data-mode="columntoggle" id="myTable">
  <thead>
    <tr>
      <th data-priority="1">Module Id</th>
      <th data-priority="3">CRN</th>
      <th data-priority="2">Name</th>  
      <th data-priority="4">Lecturer Id</th>
      <th data-priority="4">Semester Id</th>  
    </tr>
  </thead>

  <tbody>
    <c:forEach var="module" items="${modules}">
    <tr>
      <td>${module.moduleId}</td>
	  <td>${module.crnNumber}</td>
	  <td>${module.name}</td>
	  <td>${module.lectId}</td>  
	  <td>${module.semesterId}</td>    
    </tr>
     </c:forEach>
  </tbody>
 
</table>			
		
</c:if>
	
