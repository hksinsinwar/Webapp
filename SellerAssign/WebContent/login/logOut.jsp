<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<div> You have been successfully logout of the system</div>
<div>click <a href="/SellerAssign/login/Login.jsp">here</a> to login again</div>
<% 
	request.getSession().invalidate();
%>
</body>
</html>