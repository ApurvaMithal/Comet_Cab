<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/driverLoginReg.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>

</head>
<body>
<form name="regform" action="DriverController" method="post" onsubmit="return regValidate()">
	<br>${message}<br>
	
	Driver Id: <input type="text" name="username"> <br>
	<div id="username_error"></div><br>
	Driver First Name: <input type="text" name="firstName"><br>
	Driver Middle Name: <input type="text" name="middleName"><br>
	Driver Last Name: <input type="text" name="lastName"><br>
	Driver Phone Number: <input type="text" name="phoneNo"><br>
	Password: <input type="password" name="password" id="password"> 
	<br>
	Re-Type Password: <input type="password" name="retry-password" id="retry-password"> 
	<br>
	<div id="password_error"></div><br>
	Driver License Number: <input type="text" name="licenseNo"><br>
	<input type="submit" name="submit" value="register" ></br>
	<input type="reset" name="reset"></br>
	
	</form>
	
</body>
</html>