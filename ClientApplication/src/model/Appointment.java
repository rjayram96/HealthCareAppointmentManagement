package model;
import java.sql.*;
public class Appointment
{ //A common method to connect to the DB
	private Connection connect(){
		 Connection con = null;
		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");
		
			 //Provide the correct details: DBServer/DBName, user name, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
		 }
	 	 catch (Exception e)
		 {
		 	e.printStackTrace();
	 	 }
		 return con;
	}

	public String insertAppointment(String no, String date, String type, String desc, String docName, String hospName, String patientName)
	{
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for inserting."; 
			 }
			 // create a prepared statement
			 String query = " insert into appointments (`appId`,`appNo`,`appDate`,`appType`,`appDesc`,`docName`,`hospName`,`patientName`)"
			     + " values (?, ?, ?, ?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, no);
			 preparedStmt.setString(3, date);
			 preparedStmt.setString(4, type);
			 preparedStmt.setString(5, desc);
			 preparedStmt.setString(6, docName); 
			 preparedStmt.setString(7, hospName); 
			 preparedStmt.setString(8, patientName); 
			 //execute the statement
			 preparedStmt.execute();
			 con.close();
			 String newItems = readAppointments();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newItems + "\"}";
		
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			 System.err.println(e.getMessage());
		}
		return output;
	}

	public String readAppointments()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Appointment no</th><th>Appointment Date</th><th>Appointment Type</th><th>Appointment desc</th><th>Doctor</th><th>Hospital</th><th>Patient</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from appointments";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String appId = Integer.toString(rs.getInt("appId"));
				String appNo = rs.getString("appNo");
				String appDate = rs.getString("appDate");
				String appType = rs.getString("appType");
				String appDesc = rs.getString("appDesc");
				String docName = rs.getString("docName");
				String hospName = rs.getString("hospName");
				String patientName =  rs.getString("patientName");
				
				// Add into the html table
				 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + appId + "'>" + appNo + "</td>"; 
				output += "<td>" + appDate + "</td>";
				output += "<td>" + appType + "</td>";
				output += "<td>" + appDesc + "</td>";
				output += "<td>" + docName + "</td>";
				output += "<td>" + hospName + "</td>";
				output += "<td>" + patientName + "</td>";
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-appid='"
						 + appId + "'></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	} 
	
	public String updateAppointment(String ID, String no, String date, String type, String desc, String docName, String hospName, String patientName)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE appointments SET appNo=? , appDate=?, appType=?, appDesc=?,docName=?,hospName=?,patientName=? WHERE appId=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 
				 preparedStmt.setString(1, no);
				 preparedStmt.setString(2, date);
				 preparedStmt.setString(3, type);
				 preparedStmt.setString(4, desc);
				 preparedStmt.setString(5, docName); 
				 preparedStmt.setString(6, hospName); 
				 preparedStmt.setString(7, patientName); 
				 preparedStmt.setInt(8, Integer.parseInt(ID));
				 //execute the statement
				 preparedStmt.execute();
				 con.close();
				 String newItems = readAppointments();
				 output = "{\"status\":\"success\", \"data\": \"" +
				 newItems + "\"}";
			
		}
		catch (Exception e)
		{
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteAppointment(String appId)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for deleting."; 
			}
			// create a prepared statement
			String query = "delete from appointments where appId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.getInteger(appId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readAppointments();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newItems + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
			 System.err.println(e.getMessage());
		}
		return output;
	}

} 