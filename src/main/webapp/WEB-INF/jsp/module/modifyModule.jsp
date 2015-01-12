<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:if test="${not empty programmes}">
	<table data-role="table" class="ui-responsive" data-mode="columntoggle"
		id="myTable">
		<thead>
			<tr>
    			<th data-priority="1">Module Id</th>
      			<th data-priority="3">CRN</th>
      			<th data-priority="2">Name</th>  
      			<th data-priority="4">Lecturer</th>
      			<th data-priority="4">Semester Id</th>
    			<th data-priority="2">Action</th>
			</tr>
		</thead>

		<tbody>

			<c:forEach var="programme" items="${programmes}" varStatus="status">
				<tr>
					<td>${module.moduleId}</td>
	  				<td>${module.crnNumber}</td>
	  				<td>${module.name}</td> 
	  				<td>${lecturerMap[module.lecturerAutoID].fullName}</td> 
	  				<td>${module.semesterAutoID}</td>
					<td><a
						href="<%= request.getContextPath() %>/module/modify/moduleAutoID/${status.current.moduleAutoID}"
						class="ui-btn">Modify</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>