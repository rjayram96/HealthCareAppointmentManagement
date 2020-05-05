// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear status msges-------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation----------------
var status = validateItemForm();
// If not valid-------------------
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid-----------------------
var student = getStudentCard($("#txtName").val().trim(),
 $('input[name="rdoGender"]:checked').val(),
 $("#ddlYear").val());
 $("#colStudents").append(student);

 $("#alertSuccess").text("Saved successfully.");
 $("#alertSuccess").show();

 $("#formStudent")[0].reset();
});