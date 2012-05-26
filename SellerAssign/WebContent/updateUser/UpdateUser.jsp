<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="dbORM.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="../static/css/assigntest.css" />
<title>Update role</title>
</head>
<body>
	<%
		User user = (User) request.getAttribute("loggedInUser");
		List role = (List) request.getAttribute("roles");
		List userRoleList = (List) request.getAttribute("userRoleList");
	%>
	<%!public boolean isUserRole(Role roles, List userRoleList) {
		Iterator it2 = userRoleList.iterator();

		while (it2.hasNext()) {
			UserRoleMapping userRoleMap = (UserRoleMapping) it2.next();
			if (roles.getId() == userRoleMap.getRole_id()) {
				return true;
			}
		}
		return false;
	}%>
	<div>
		Welcome
		<%=user.getLoginId()%></div>
	<div><form name="logout" action="/SellerAssign/login/logOut.jsp" method="post">
		<input type="submit" name="Logout" value="Logout" />
		</form>
	</div>
	<form name="updateUser" method="post"
		action="/SellerAssign/updateUserData">
		<div style="margin-left: 20px; width: 200px;" class="floatLeft">
			Login Id :</div>
		<div style="margin-left: 10px; width: 300px" class="floatLeft">
			<input type="text" name="userName" value="<%=user.getLoginId()%>">
		</div>
		<div class="clearFloat"></div>
		<input type="hidden" name="userid" value=<%=user.getId()%>>
		<div style="margin-left: 20px; width: 200px;" class="floatLeft">
			Password :</div>
		<div style="margin-left: 10px; width: 300px" class="floatLeft">
			<input type="password" name="password"
				value="<%=user.getPassword()%>">
		</div>
		<div class="clearFloat" /></div>
		<div>User's Role</div>
		<%
			Iterator it = role.iterator();
			while (it.hasNext()) {
				Role roles = (Role) it.next();
				if (isUserRole(roles, userRoleList)) {
		%>
		<input name="roles" type="checkbox" value="<%=roles.getId()%>" checked><%=roles.getRole_name()%>
		<%
			} else {
		%>
		<input name="roles" type="checkbox" value="<%=roles.getId()%>"><%=roles.getRole_name()%>
		<%
			}
			}
		%>

		<input type="submit" value="update"
			style="margin-left: 100px; margin-top: 10px;">
	</div>
	</form>
</body>
</html>