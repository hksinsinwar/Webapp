<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="../static/css/assigntest.css" />
<title>Insert title here</title>
</head>
<body>
	<div>Login to continue</div>
	<form name="loginForm" method="post"
		action="/SellerAssign/loginServlet">
		<div style="margin-left: 20px; width: 200px;" class="floatLeft">
			Login Id :</div>
		<div style="margin-left: 10px; width: 300px" class="floatLeft">
			<input type="text" name="userName" class="floatLeft">
		</div>
		<div class="clearFloat"></div>
		<div style="margin-left: 20px; width: 200px;" class="floatLeft">
			Password :</div>
		<div style="margin-left: 10px; width: 300px" class="floatLeft">
			<input type="password" name="password" class="floatLeft">
		</div>
		<div class="clearFloat"></div>
		<div>
			<input type="submit" value="login">
		</div>
	</form>
</body>
</html>