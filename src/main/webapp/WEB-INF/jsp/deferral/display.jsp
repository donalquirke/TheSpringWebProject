<%@ include file="/WEB-INF/jsp/include.jsp"%>

<h2>${message}</h2>

<table data-role="table" data-mode="columntoggle" class="ui-responsive" id="myTable">
  <thead>
    <tr>
    <th data-priority="3">Image </th>
    <th data-priority="3">Deferral ID</th>
    <th data-priority="4">Student ID</th>
    <th data-priority="1">Lecturer ID</th>
    <th data-priority="2">Programme ID</th>  
    <th data-priority="2">Module ID</th>
    <th data-priority="2">Approved</th>   
    </tr>
  </thead>
  <tbody>
    <tr>
   	  <td><img alt="deferral" width="48" border="0" align="middle" src="<c:url value="/resources/images/cit.jpg"/>"></td>
      <td>${deferral.defId}</td>
      <td>${deferral.studentAutoID}</td>
      <td>${deferral.lectId}</td>
      <td>${deferral.programmeAutoID}</td>  
      <td>${deferral.moduleAutoID}</td>
      <td>${deferral.approval}</td>          
    </tr>
  </tbody>
</table>