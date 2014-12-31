<%@ include file="/WEB-INF/jsp/include.jsp"%>
<c:if test="${not empty lecturers}">
	<table data-role="table" class="ui-responsive" data-mode="columntoggle"
		id="myTable">
		<thead>
			<tr>
    			<th data-priority="1">Lecturer ID</th>
    			<th data-priority="3">First Name</th>
    			<th data-priority="2">Surname</th>  
    			<th data-priority="4">Email Address</th>
    			<th data-priority="2">Action</th>  
    		</tr>
		</thead>

		<tbody>

			<c:forEach var="lecturer" items="${lecturers}" varStatus="status">
				<tr>
					<td>${lecturer.lectId}</td>
					<td>${lecturer.firstName}</td>
					<td>${lecturer.lastName}</td>
					<td>${lecturer.email}</td>
					<td><a
						href="<%= request.getContextPath() %>/lecturer/delete/lectId/${status.current.lectId}"
						class="ui-btn">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
</c:if>