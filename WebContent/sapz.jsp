<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import="javax.sql.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
 <%String fileName = request.getParameter("fileupload");
FileInputStream is = new FileInputStream(fileName);

int ch;
while ((ch = is.read()) >= 0)
  out.print((char) ch);
is.close();
%>
 </body>
</html>