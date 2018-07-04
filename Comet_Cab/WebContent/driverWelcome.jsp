<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/table.css">
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/driverWelcome.js"></script>
	<title>Welcome User</title>


</head>

<body>
<h3><i><font color="gray"><center> ${message}</center></font></i></h3>
<h3><i><font color="violet"><center> Welcome to Online Comet Cab Booking Site!! </center></font></i></h3></br>
<br>
	

<div class="row">
	<div class="col-md-5"></div>
	<div class="col-md-5">
		<button type="button" class="btn btn-warning" style="height:30px;width:200px" id="getRequest" name= "submit" value="getRequest">Get User Request</button>
	</div>
</div>
</br></br>
<div class="row">
	 <div class="col-md-2"></div>  
	<div class="col-md-9">
	<table id="userRequest" border="1" style="border-collapse: collapse;" cellpadding="5"> 
            <tr>
            	<th id=bookingId> Booking Id</th>
                <th>Customer First Name</th>
                <th>Customer Middle Name</th>
                <th>Customer Last Name</th>  
                <th>Customer Phone No</th>
                <th> Pick Up Location </th> 
                <th>Drop Location</th>        
                <th> Start Ride</th> 
                <th>End Ride</th>
            </tr>

	</table>
</div>
</div>
</br></br>

<input type="hidden" name="driverId" value=${driverId} id="driverId"> </input>

<!-- Modal -->
<div id="startRideModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title" id="head">Message</h4>
      </div>
      <div class="modal-body" id = "start">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
</br></br>

<div id="endRideModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title" id="head">Message</h4>
      </div>
      <div class="modal-body" id = "end">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>

<a href="driverLogout.jsp" ><h5><p class="text-success">LOGOUT</p><h5></a>
</body>
</html>