package com;

import javax.servlet.ServletException;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Appointment;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@WebServlet("/AppointmentsAPI")
public class AppointmentsAPI extends HttpServlet
{
	Appointment itemObj = new Appointment();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String output = itemObj.insertAppointment(request.getParameter("appNo"),
		request.getParameter("appDate"),
		request.getParameter("appType"),
		request.getParameter("appDesc"),
		request.getParameter("docName"),
		request.getParameter("hospName"),
		request.getParameter("patientName"));
		response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
	{
		 Map<String, String> map = new HashMap<String, String>();
		 try
		 {
			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			 String queryString = scanner.hasNext() ?
			 scanner.useDelimiter("\\A").next() : "";
			 scanner.close();
			 String[] params = queryString.split("&");
			 for (String param : params)
			 { 
				 String[] p = param.split("=");
				 map.put(p[0], p[1]);
			 }
		 }catch (Exception e){}
			
		 return map;
		
	 }
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		 Map paras = getParasMap(request);
		 String output = itemObj.updateAppointment(paras.get("hidItemIDSave").toString(),
		 paras.get("appNo").toString(),
		 paras.get("appDate").toString(),
		 paras.get("appType").toString(),
		 paras.get("appDesc").toString(),
		 paras.get("docName").toString(),
		 paras.get("hospName").toString(),
		 paras.get("patientName").toString());
		 response.getWriter().write(output);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		 Map paras = getParasMap(request);
		 String output = itemObj.deleteAppointment(paras.get("appId").toString());
		 response.getWriter().write(output);
	}
	
}