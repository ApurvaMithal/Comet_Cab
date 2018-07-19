

$(document).ready(function() {

		$(".dropdown-menu li a").click(function(){
				var selText = $(this).text();
				$(this).parents('.dropdown').find('.dropdown-toggle').html(selText + ' <span class="caret"></span>');	
				$(this).parents('.dropdown').find('.dropdown-toggle').val(selText);
			});
});


  jQuery(window).ready(function() {
	    $("#reserveBooking").click(function(event) {
	        
	        var pick = $('#pick').text();
	        var drop = $('#drop').text();
	        var cab = $('#cab').text();
	        var netId = $('#netId').val();
	        var submit = $('#reserveBooking').val();
	        
	        if($('#pick').val()=="None"){
        		if(!($("#confirmBooking").hasClass('disabled')))
        			$("#confirmBooking").addClass('disabled');
        		$('#reserve').html('<p>'+'Select a Pickup Location '+'</p>');
            	$("#reserveBookingModal").modal();
        	}
        	else if($('#drop').val()=="None"){
        		if(!($("#confirmBooking").hasClass('disabled')))
        			$("#confirmBooking").addClass('disabled');
        		$('#reserve').html('<p>'+'Select a Drop Location '+'</p>');
            	$("#reserveBookingModal").modal();
        	}
        	else if($('#cab').val()=="None"){
        		if(!($("#confirmBooking").hasClass('disabled')))
        			$("#confirmBooking").addClass('disabled');
        		$('#reserve').html('<p>'+'Select a Cab Type '+'</p>');
            	$("#reserveBookingModal").modal();
        	}
        	else if(pick == drop){
        		if(!($("#confirmBooking").hasClass('disabled')))
        			$("#confirmBooking").addClass('disabled');
        		$('#reserve').html('<p>'+'Pickup Location Cannot Be Same As Drop Location'+'</p>');
            	$("#reserveBookingModal").modal();	
        	} 
	        
        	else{
	        jQuery.ajax({
	            url: "CustomerController",
	            type: "post",
	            dataType: "text",
	            data: {
	                pick: pick,
	                drop: drop,
	                cab: cab,
	                netId : netId,
	                submit: submit
	            },
	            success: function(data) {
	            	   
	            	if(data!=null && data.substring(0,5)=="Error"){
	            		if((!$("#confirmBooking").hasClass('disabled')))
	            			$("#confirmBooking").addClass('disabled');
	            		$('#reserve').html('<p>'+ "<h4> Exception Occurred: </h4>" + data+ '</br>' + '</p>');
		            	$("#reserveBookingModal").modal();
	            	}
	            	else{
	            		$('#fare').val(data);
	            		if(($("#confirmBooking").hasClass('disabled')))
		            			$("#confirmBooking").removeClass('disabled');
	            		$('#reserve').html('<p>'+ "Estimated Fare Is: " + data+ '</br>' + "Please Confirm The Ride within 1 minute. " + '</p>');
		            	$("#reserveBookingModal").modal();
		            	
		            	var buttonTimer = setTimeout(cancelBooking, 5000);
		            	 $("#confirmBooking").click(function(event) {
		            		clearTimeout(buttonTimer);
		         	        var pick = $('#pick').text();
		         	        var drop = $('#drop').text();
		         	        var cab = $('#cab').text();
		         	        var netId = $('#netId').val();
		         	        var submit = $('#confirmBooking').val();
		         	        var fare = $('#fare').val();
		         	        jQuery.ajax({
		         	            url: "CustomerController",
		         	            type: "post",
		         	            dataType: "json",
		         	            data: {
		         	                pick: pick,
		         	                drop: drop,
		         	                cab: cab,
		         	                netId : netId,
		         	                submit: submit,
		         	                fare: fare
		         	            },
		         	            success: function(dataBook) {
		         	            		
		         	            		var str = "Booking Confirmed!!!</br>";
		         	            		str+= "Booking Id: "+dataBook.booking.bookingId+"</br>";
		         	            		str+= "Driver Name: "+ dataBook.driver.firstName + " "+ dataBook.driver.lastName+"</br>";
		         	            		str+= "Driver Phone No: "+ dataBook.driver.phoneNo+"</br>";
		         	            		str+= "Cab Type: "+ dataBook.cab.cabType+"</br>";
		         		            	str+= "Cab Number: "+ dataBook.driver.licenseNo+"</br>";
		         		            	str+= "Cab Model: "+ dataBook.cab.model+"</br>";
		         	            		$('#confirm').html('<p>'+str+'</p>');
		         		            	$("#confirmBookingModal").modal();
		         		            	
		         	            	
		         	            }
		         	        });
		                 	
		         	    });	
	            		}	
	            }
	          
	        });
        	}
	    });
	    
	    function cancelBooking(){
	    	 jQuery.ajax({
		            url: "CustomerController",
		            type: "post",
		            dataType: "text",
		            data: {
		              //  bookingId: bookingId,
		                submit: "cancelBooking"
		            },
		            success: function(data) {
		            	if((!$("#confirmBooking").hasClass('disabled')))
	            			$("#confirmBooking").addClass('disabled');
		            	$('#cancel').html('<p>'+ "Oh no!! You didn't click on confirm booking.."+data+'</br>' + '</p>');
			            $("#cancelBookingModal").modal();
		            	
		            }
	    	 });
		}
   
});
