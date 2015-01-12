<%@ include file="/WEB-INF/jsp/include.jsp"%>

<h2>${message}</h2>

<table data-role="table" data-mode="columntoggle" class="ui-responsive" id="myTable">
  <thead>
    <tr>
      <th data-priority="1">Module Id</th>
      <th data-priority="3">CRN</th>
      <th data-priority="2">Name</th>  
      <th data-priority="4">Lecturer</th>
      <th data-priority="4">Semester Id</th>  
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>${module.moduleId}</td>
	  <td>${module.crnNumber}</td>
	  <td>${module.name}</td> 
	  <td>${lecturerMap[module.lecturerAutoID].fullName}</td> 
	  <td>${module.semesterAutoID}</td>           
    </tr>
  </tbody>
</table>