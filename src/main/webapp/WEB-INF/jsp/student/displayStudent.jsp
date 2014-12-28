<%@ include file="/WEB-INF/jsp/include.jsp"%>

<h2>${message}</h2>

<table data-role="table" data-mode="columntoggle" class="ui-responsive" id="myTable">
  <thead>
    <tr>
      <th data-priority="1">Student ID</th>
      <th data-priority="3">First Name</th>
      <th data-priority="2">Surname</th>  
      <th data-priority="4">Email Address</th>   
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>${student.studentId}</td>
      <td>${student.firstName}</td>
      <td>${student.lastName}</td>
      <td>${student.email}</td>           
    </tr>
  </tbody>
</table>