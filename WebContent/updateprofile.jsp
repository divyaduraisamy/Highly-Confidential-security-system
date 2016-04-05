<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DBDemo</title>
</head>
<body>
<%@ page import="javax.sql.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.naming.*"%>
<%Connection con = null; 
 Statement stmt = null; 
 Context envContext = null;
try { 
	envContext = new InitialContext();
	  Context initContext  = (Context)envContext.lookup("java:/comp/env");
	  DataSource ds = (DataSource)initContext.lookup("jdbc/MyDataSource");
// Get a database connection
 con = ds.getConnection(); 
 } catch(java.lang.Exception e) { 
 e.printStackTrace(); 
 System.err.print(e.getClass().getName()); 
 System.err.println(e.getMessage()); 
 } 
try { 
// Prepare a statement object used to execute query
 stmt = con.createStatement(); 

 String sql = "select title,fname,lname,uname,pwd,email,mobno from ganesh where  "; 
// Fill returned data into ResultSet object. 
 ResultSet rs = stmt.executeQuery(sql); 
%>
The employees who are older than 25 years old are: 
<BR>
<table>
<tr>
<th>Employee ID</th>
<th>Employee Name</th>
</tr>
<%
while (rs.next()) { 
int empid = rs.getInt("empID"); 
 String empname = rs.getString("empName"); 
%>
<tr>
<td><%=empid%></td>
<td><%=empname%></td>
</tr>
<%
 } 
%>
</table>
<%
 rs.close(); 
 stmt.close(); 
 con.close(); 
 } 
catch(java.lang.Exception e) { 
 e.printStackTrace(); 
 System.err.print(e.getClass().getName()); 
 System.err.println(e.getMessage()); 
 } 
%>
</body>
</html>