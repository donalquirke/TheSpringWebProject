<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:if test="${not empty students}">
	<table data-role="table" class="ui-responsive" data-mode="columntoggle"
		id="myTable">
		<thead>
			<tr>
    			<th data-priority="4">Student ID</th>
    			<th data-priority="1">First Name</th>
    			<th data-priority="2">Last Name</th>  
    			<th data-priority="2">Email</th>
    			<th data-priority="2">Action</th>
			</tr>
		</thead>

		<tbody>

			<c:forEach var="student" items="${students}" varStatus="status">
				<tr>
					<td>${student.studentId}</td>
					<td>${student.firstName}</td>
					<td>${student.lastName}</td>
					<td>${student.email}</td>
					<td><a
						href="<%= request.getContextPath() %>/student/modify/studentId/${status.current.studentId}"
						class="ui-btn">Modify</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>