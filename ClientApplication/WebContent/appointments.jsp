<%@page import="model.Appointment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Appointments Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.5.1.min.js"></script>
<script src="Components/appointments.js"></script>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-6">
		<h1>Appointments Management V10.1</h1>
		<form id="formItem" name="formItem">
			 Appointment No :
			 <input id="appNo" name="appNo" type="text" class="form-control form-control-sm">
			 <br> Appointment date :
			 <input id="appDate" name="appDate" type="text" class="form-control form-control-sm">
			 <br> Appointment type :
			 <input id="appType" name="appType" type="text" class="form-control form-control-sm">
			 <br> Appointment description :
			 <input id="appDesc" name="appDesc" type="text" class="form-control form-control-sm">
			 <br> Doctor name :
			 <input id="docName" name="docName" type="text" class="form-control form-control-sm">
			 <br> Hospital name :
			 <input id="hospName" name="hospName" type="text" class="form-control form-control-sm">
			 <br> Patient name :
			 <input id="patientName" name="patientName" type="text" class="form-control form-control-sm">
			 <br> 
			 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
			 <input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
		</form>
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		<div id="divItemsGrid">
			 <%
			 Appointment itemObj = new Appointment();
			 out.print(itemObj.readAppointments());
			 %>
		</div>
		</div>
	 </div>
</div>

</body>
</html>