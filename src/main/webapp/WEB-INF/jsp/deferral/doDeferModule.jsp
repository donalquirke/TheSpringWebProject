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
				if(result.programmeList){ //null check
					$("#programmeAutoID").html(  //this overwrites the contents of the programmeId element
							createOptionList(result.programmeList, "programmeAutoID", "programmeId")
							).focus();
					$("#studentAutoID").val(result.studentAutoID);
				}
		  	 }
	 	});
	});
	
	//On the programmeId dropdown, on change, do ajax call
	$("#programmeAutoID").change(function(){
		 $.ajax({
			//go to getProgrammeModuleList
			 	 url:"getProgrammeModuleList",
			 	//send programmeId to the server
				 data:{"programmeAutoID" : $(this).val()},
				//run this function with the response from the server
			 	 success:function(result){
			 		if(result.moduleList)
						$("#moduleAutoID").html(
								createOptionList(result.moduleList, "moduleAutoID", "name")
								).focus();
				  }
		 });
	});

	//On the moduleId dropdown, on change, do ajax call
	$("#moduleAutoID").change(function(){
		$.ajax({
		 	 url:"getModuleDetails",
			 data:{"ModuleAutoID" : $(this).val()},
		 	 success:function(result){
		 		$("#lectId").html(
						createOptionList(result.lectList, "lecturerAutoId", "fullName")
					).focus();
			  }
	 });
	});
	
});

//create contents of a dropdown
function createOptionList(list, id, displayValue){
	var optionList="<option value='' selected>Please Select</option>'";
	for(index in list){
		optionList += "<option value='" + list[index][id] + "'>" + list[index][displayValue] + "</option>'";
	}
	return optionList;
}
</script>


<form:form method="POST" enctype="multipart/form-data" data-ajax="false" action="addNew" modelAttribute="deferral">

<form:errors path="lectId" class="notification error" style="display:block"></form:errors>
<form:errors path="programmeAutoID" class="notification error" style="display:block"></form:errors>
<form:errors path="moduleAutoID" class="notification error" style="display:block"></form:errors>
<form:errors path="approval" class="notification error" style="display:block"></form:errors>
<form:errors path="*" cssClass="errorblock" element="div" />

 <div class="ui-field-contain">
 <label for="studentId">Student ID</label>
 <input id="studentId" placeholder="Student ID.."  value=""/>
 <form:hidden path="studentAutoID" value=""/>
 
 </div>
 
 
 <div class="ui-field-contain">
 <form:label path="programmeAutoID">Programme ID</form:label>
 <form:select path="programmeAutoID" placeholder="Programme ID..">
 	<form:option value="-1"> Please Select</form:option>
	<form:options items="${deferralForm.programmeList }" itemValue="programmeAutoID" itemLabel="programmeId" />
 </form:select>
 <form:errors path="programmeAutoID" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="moduleAutoID">Module ID</form:label>
 <form:select path="moduleAutoID" placeholder="Module ID.."   value="">
 	<form:option value="-1"> Please Select</form:option>
	<form:options items="${deferralForm.moduleList }" itemValue="moduleAutoID" itemLabel="moduleId" />
 </form:select>
 <form:errors path="moduleAutoID" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="lectId">Lecturer ID</form:label>
 <form:select path="lectId" placeholder="Lecturer ID.."   value="">
 	<form:option value="-1"> Please Select</form:option>
	<form:options items="${deferralForm.lectList }" itemValue="lecturerAutoId" itemLabel="lectId" />
 </form:select>
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