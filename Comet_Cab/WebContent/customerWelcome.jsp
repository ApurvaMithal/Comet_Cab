<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/customerWelcome.js"></script>
	<title>Welcome User</title>


</head>

<body>
<h3><i><font color="gray"><center> ${message}</center></font></i></h3>
<h3><i><font color="violet"><center> Welcome to Online Comet Cab Booking Site!! </center></font></i></h3></br>
<br>
	
<div class="row">
	<div class="col-md-5"></div>
	<div class="col-md-5">
		<div class="dropdown">
		  <font color="black"><h5> Pick Up Location </h5></font>
		  <button  class="btn btn-primary dropdown-toggle btn-warning" type="button" data-toggle="dropdown" style="height:30px;width:200px" id="pick" value="None" >PickUp Location
		  <span class="caret"></span></button>
		  <ul class="dropdown-menu">
		    <li><a href="#">UTD</a></li>
		    <li><a href="#">SSN</a></li>
		    <li><a href="#">DPS</a></li>
		    <li><a href="#">GALLERIA</a></li>
		    <li><a href="#">AIRPORT</a></li>
		    
		  </ul>
		  </div>
	</div>
</div>
</br>
<div class="row">
 	<div class="col-md-5"></div>
	<div class="col-md-5">
		<div class="dropdown">
		<font color="black"><h5> Drop Location </h5></font>
		  <button class="btn btn-primary dropdown-toggle btn-success" type="button" data-toggle="dropdown" style="height:30px;width:200px" id="drop" value="None">Drop Location
		  <span class="caret"></span></button>
		  <ul class="dropdown-menu">
		    <li><a href="#">UTD</a></li>
		    <li><a href="#">SSN</a></li>
		    <li><a href="#">DPS</a></li>
		    <li><a href="#">GALLERIA</a></li>
		    <li><a href="#">AIRPORT</a></li>
		    
		  </ul>
		</div>
	</div>
</div>
</br>

<div class="row">
	<div class="col-md-5"></div>
	<div class="col-md-5">
		<div class="dropdown">
		<font color="black"><h5> Cab Type </h5></font>
		  <button class="btn btn-primary dropdown-toggle btn-info" type="button" data-toggle="dropdown" style="height:30px;width:200px" id="cab" value="None">Cab Type
		  <span class="caret"></span></button>
		  <ul class="dropdown-menu">
		    <li><a href="#">HATCHBACK</a></li>
		    <li><a href="#">SEDAN</a></li>
		    <li><a href="#">SUV</a></li>
		  </ul>
		</div>
	</div>
</div>
</br></br>

<div class="row">
	<div class="col-md-5"></div>
	<div class="col-md-5">
		<button type="button" class="btn btn-danger" style="height:30px;width:200px" id="makeBooking" name= "submit" value="makeBooking">Reserve Booking</button>
	</div>
</div>
</br></br>
<div class="row">
	<div class="col-md-5"></div>
	<div class="col-md-5">
	<button type="button" class="btn btn-basic disabled" id="confirmBooking" style="height:30px;width:200px" name= "submit" value="confirmBooking">Confirm Booking </button>
	</div>
</div>

<input type="hidden" name="netId" value=${netId} id="netId"> </input>
<input type="hidden" name="fare" value="None" id="fare"> </input>

<!-- Modal -->
<div id="makeBookingModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title" id="head">Message</h4>
      </div>
      <div class="modal-body" id = "make">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
</br></br>

<div id="confirmBookingModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title" id="head">Message</h4>
      </div>
      <div class="modal-body" id = "confirm">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>


<a href="customerLogout.jsp" ><h5><p class="text-success">LOGOUT</p><h5></a>
</body>
</html>