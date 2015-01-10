<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:if test="${not empty deferrals}">
	<table data-role="table" class="ui-responsive" data-mode="columntoggle" id="myTable">
		<thead>
			<tr>
				<th data-priority="3">Image </th>
    			<th data-priority="3">Deferral ID</th>
    			<th data-priority="4">Student ID</th>
    			<th data-priority="1">Lecturer ID</th>
    			<th data-priority="2">Programme ID</th>  
    			<th data-priority="2">Module ID</th>
    			<th data-priority="2">Approved</th>
    			<th data-priority="2">Action</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="deferral" items="${deferrals}" varStatus="status">
				<tr>
					<td><img alt="deferral" width="24" border="0" align="middle"
						src='<c:url value="/resources/images/cit.jpg"/>'></td>
					<td>${deferral.defId}</td>
					<td>${deferral.studentAutoID}</td>
					<td>${deferral.lectId}</td>
					<td>${deferral.programmeAutoID}</td>
					<td>${deferral.moduleAutoID}</td>
					<td>${deferral.approval}</td>
					<td><a
						href="<%= request.getContextPath() %>/deferral/modify/defId/${status.current.defId}"
						class="ui-btn">Modify</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>