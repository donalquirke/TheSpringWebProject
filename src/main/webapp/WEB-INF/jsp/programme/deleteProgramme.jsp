<%@ include file="/WEB-INF/jsp/include.jsp"%>
<c:if test="${not empty programmes}">
	<table data-role="table" class="ui-responsive" data-mode="columntoggle"
		id="myTable">
		<thead>
			<tr>
    			<th data-priority="1">Programme ID</th>
    			<th data-priority="3">No. of Years</th>
    			<th data-priority="2">Co-Ordinator Id</th>  
    			<th data-priority="4">Year of Programme</th>
    			<th data-priority="2">Action</th>  
    		</tr>
		</thead>

		<tbody>

			<c:forEach var="programme" items="${programmes}" varStatus="status">
				<tr>
					<td>${programme.programmeId}</td>
					<td>${programme.numYears}</td>
					<td>${programme.lecturerAutoID}</td>
					<td>${programme.progYear}</td>
					<td><a
						href="<%= request.getContextPath() %>/programme/delete/programmeAutoId/${status.current.programmeAutoId}"
						class="ui-btn">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
</c:if>