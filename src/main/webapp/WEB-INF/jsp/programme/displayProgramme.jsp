<%@ include file="/WEB-INF/jsp/include.jsp"%>

<h2>${message}</h2>

<table data-role="table" data-mode="columntoggle" class="ui-responsive" id="myTable">
  <thead>
    <tr>
      <th data-priority="1">Programme ID</th>
      <th data-priority="3">No. of Years</th>
      <th data-priority="2">Co-Ordinator Id</th>  
      <th data-priority="4">Year of Programme</th>  
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>${programme.programmeId}</td>
	  <td>${programme.numYears}</td>
	  <td>${programme.coordinatorId}</td>
	  <td>${programme.progYear}</td>           
    </tr>
  </tbody>
</table>