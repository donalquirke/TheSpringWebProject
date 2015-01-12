<%@ include file="/WEB-INF/jsp/include.jsp"%>
<c:if test="${not empty deferrals}">
	<table data-role="table" class="ui-responsive" data-mode="columntoggle"
		id="myTable">
		<thead>
			<tr>
    			<th data-priority="3">Deferral ID</th>
    			<th data-priority="4">Student ID</th>
    			<th data-priority="1">Lecturer Name</th>
    			<th data-priority="2">Programme ID</th>  
    			<th data-priority="2">Module ID</th>
    			<th data-priority="2">Approved</th>
    			<th data-priority="2">Action</th>   
    		</tr>
		</thead>

		<tbody>

			<c:forEach var="deferral" items="${deferrals}" varStatus="status">
				<tr>
					<td>${deferral.defId}</td>
					<td>${studentMap[deferral.studentAutoID].studentId}</td>
					<td>${lecturerMap[deferral.lectId].fullName}</td>
					<td>${programmeMap[deferral.programmeAutoID].programmeId}</td>
					<td>${moduleMap[deferral.moduleAutoID].moduleId}</td>
					<td>${deferral.approval}</td>
					<td><a
						href="<%= request.getContextPath() %>/deferral/delete/defId/${status.current.defId}"
						class="ui-btn">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
</c:if>