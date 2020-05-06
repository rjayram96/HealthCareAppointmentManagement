$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
		$("#alertSuccess").hide();
	 }
	 $("#alertError").hide();
});

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	
	// Clear alerts---------------------
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide(); 
	 
	//Form validation-------------------
	 var status = validateItemForm();
	 if (status != true)
	  {
		  $("#alertError").text(status);
		  $("#alertError").show();
		  return;
	  }
 
	 
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
	{
		 url : "AppointmentsAPI",
		 type : type,
		 data : $("#formItem").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
			 onItemSaveComplete(response.responseText, status);
		 }
	});
		
	
	  
 });

function onItemSaveComplete(response, status)
{
	if (status == "success")
	 {
		 var resultSet = JSON.parse(response);
		 if (resultSet.status.trim() == "success")
		 {
		 $("#alertSuccess").text("Successfully saved.");
		 $("#alertSuccess").show();
		 $("#divItemsGrid").html(resultSet.data);
		 } else if (resultSet.status.trim() == "error")
		 {
			 $("#alertError").text(resultSet.data);
			 $("#alertError").show();
		 }
	 } else if (status == "error")
	 {
		 $("#alertError").text("Error while saving.");
		 $("#alertError").show();
	 } else
	 {
		 $("#alertError").text("Unknown error while saving..");
		 $("#alertError").show();
	 }
	 $("#hidItemIDSave").val("");
	 $("#formItem")[0].reset();
}

$(document).on("click", ".btnRemove", function(event)
		{
			 $.ajax(
			 {
				 url : "AppointmentsAPI",
				 type : "DELETE",
				 data : "appId=" + $(this).data("appid"),
				 dataType : "text",
				 complete : function(response, status)
				 {
					 onItemDeleteComplete(response.responseText, status);
				 }
			 });
		});

function onItemDeleteComplete(response, status)
{
	if (status == "success")
	 {
		 var resultSet = JSON.parse(response);
		 if (resultSet.status.trim() == "success")
		 {
			 $("#alertSuccess").text("Successfully deleted.");
			 $("#alertSuccess").show();
			 $("#divItemsGrid").html(resultSet.data);
		 } else if (resultSet.status.trim() == "error")
		 {
			 $("#alertError").text(resultSet.data);
			 $("#alertError").show();
		 }
	 } else if (status == "error")
	 {
		 $("#alertError").text("Error while deleting.");
		 $("#alertError").show();
	 } else
	 {
		 $("#alertError").text("Unknown error while deleting..");
		 $("#alertError").show();
	 }
	 
}





//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	 $("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
	 $("#appNo").val($(this).closest("tr").find('td:eq(0)').text());
	 $("#appDate").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#appType").val($(this).closest("tr").find('td:eq(2)').text());
	 $("#appDesc").val($(this).closest("tr").find('td:eq(3)').text());
	 $("#docName").val($(this).closest("tr").find('td:eq(4)').text());
	 $("#hospName").val($(this).closest("tr").find('td:eq(5)').text());
	 $("#patientName").val($(this).closest("tr").find('td:eq(6)').text());
});

//CLIENT-MODEL================================================================
function validateItemForm()
{
	
	if ($("#appNo").val().trim() == "")
	 {
		return "Insert appointment no.";
	 }
	
	if ($("#appDate").val().trim() == "")
	 {
		return "Insert date.";
	 }
	
	if ($("#appType").val().trim() == "")
	 {
		return "Insert type.";
	 }
	
	if ($("#appDesc").val().trim() == "")
	 {
		return "Insert appointment description.";
	 }
	
	if ($("#docName").val().trim() == "")
	 {
		return "Insert doctor name.";
	 }
	
	if ($("#hospName").val().trim() == "")
	 {
		return "Insert hospital name.";
	 }
	
	if ($("#patientName").val().trim() == "")
	 {
		return "Insert patient name.";
	 }
	
	
	return true;
}





