<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script type="text/javascript">
$(document).ready(function(){  //don't execute until page is fully loaded
	
	//On the studentId field, on change, do ajax call
	$("#studentId").change(function(){
	 $.ajax({
		 //go to getStudentProgrammeList
			 url:"getStudentProgrammeList",
			 //send studentId to the server, $(this).val() points to the value of the field that is in context
			 data:{"studentId" : $(this).val()},
			 //run this function with the response from the server
			 success:function(result){
				 //result is what the server has returned from the getStudentProgrammeList method
				if(result.programmeList) //null check
					$("#programmeId").html(  //this overwrites the contents of the programmeId element
							createOptionList(result.programmeList, "programmeId", "programmeId")
							);
		  	 }
	 	});
	});
	
	//On the programmeId dropdown, on change, do ajax call
	$("#programmeId").change(function(){
		 $.ajax({
			//go to getProgrammeModuleList
			 	 url:"getProgrammeModuleList",
			 	//send programmeId to the server
				 data:{"programmeId" : $(this).val()},
				//run this function with the response from the server
			 	 success:function(result){
			 		if(result.moduleList)
						$("#moduleId").html(
								createOptionList(result.moduleList, "combindedKey", "name")
								);
				  }
		 });
	});

	//On the moduleId dropdown, on change, do ajax call
	$("#moduleId").change(function(){
		$.ajax({
		 	 url:"getModuleDetails",
			 data:{"combindedKey" : $(this).val()},
		 	 success:function(result){
		 		$("#lectId").val(result.firstName + " " + result.lastName);
			  }
	 });
	});
	
});

//create contents of a dropdown
function createOptionList(list, id, displayValue){
	var optionList="";
	for(index in list){
		optionList += "<option value='" + list[index][id] + "'>" + list[index][displayValue] + "</option>'";
	}
	return optionList;
}
</script>


<form:form method="POST" enctype="multipart/form-data" data-ajax="false" action="addNew" modelAttribute="deferral">

<form:errors path="studentId" class="notification error" style="display:block"></form:errors>
<form:errors path="lectId" class="notification error" style="display:block"></form:errors>
<form:errors path="programmeId" class="notification error" style="display:block"></form:errors>
<form:errors path="moduleId" class="notification error" style="display:block"></form:errors>
<form:errors path="approval" class="notification error" style="display:block"></form:errors>
<form:errors path="*" cssClass="errorblock" element="div" />

 <div class="ui-field-contain">
 <form:label path="studentId">Student ID</form:label>
 <form:input path="studentId" placeholder="Student ID.."  value=""/>
 <form:errors path="studentId" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="programmeId">Programme ID</form:label>
 <form:select path="programmeId" placeholder="Programme ID.."   value="">
	<form:options list="${deferralForm.programmeList }"/>
 </form:select>
 <form:errors path="programmeId" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="moduleId">Module ID</form:label>
 <form:select path="moduleId" placeholder="Module ID.."   value="">
	<form:options list="${deferralForm.moduleList }"/>
 </form:select>
 <form:errors path="moduleId" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="lectId">Lecturer ID</form:label>
 <form:input path="lectId" placeholder="Lecturer ID.."   value=""/>
 <form:errors path="lectId" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="approval">Approval</form:label>
 <form:input path="approval" placeholder="Approval..(true/false)"   value=""/>
 <form:errors path="approval" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 
<div class="ui-field-contain">
<form:label path="file">File</form:label>
<form:input path="file" type="file"/> 
</div>
 
 <input data-theme="b" type="submit" data-icon="check" value="Save" />
  </form:form>