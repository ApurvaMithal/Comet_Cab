

$(document).ready(function() {

		$(".dropdown-menu li a").click(function(){
				var selText = $(this).text();
				$(this).parents('.dropdown').find('.dropdown-toggle').html(selText + ' <span class="caret"></span>');	
				$(this).parents('.dropdown').find('.dropdown-toggle').val(selText);
			});
});


  jQuery(window).ready(function() {
	    $("#makeBooking").click(function(event) {
	        
	        var pick = $('#pick').text();
	        var drop = $('#drop').text();
	        var cab = $('#cab').text();
	        var netId = $('#netId').val();
	        var submit = $('#makeBooking').val();
	        
	        if($('#pick').val()=="None"){
        		if(!($("#confirmBooking").hasClass('disabled')))
        			$("#confirmBooking").addClass('disabled');
        		$('#make').html('<p>'+'Select a Pickup Location '+'</p>');
            	$("#makeBookingModal").modal();
        	}
        	else if($('#drop').val()=="None"){
        		if(!($("#confirmBooking").hasClass('disabled')))
        			$("#confirmBooking").addClass('disabled');
        		$('#make').html('<p>'+'Select a Drop Location '+'</p>');
            	$("#makeBookingModal").modal();
        	}
        	else if($('#cab').val()=="None"){
        		if(!($("#confirmBooking").hasClass('disabled')))
        			$("#confirmBooking").addClass('disabled');
        		$('#make').html('<p>'+'Select a Cab Type '+'</p>');
            	$("#makeBookingModal").modal();
        	}
        	else if(pick == drop){
        		if(!($("#confirmBooking").hasClass('disabled')))
        			$("#confirmBooking").addClass('disabled');
        		$('#make').html('<p>'+'Pickup Location Cannot Be Same As Drop Location'+'</p>');
            	$("#makeBookingModal").modal();	
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
	            	   
	            		$('#fare').val(data);
	            		if(($("#confirmBooking").hasClass('disabled')))
	            			$("#confirmBooking").removeClass('disabled');
	            		
		            	$('#make').html('<p>'+ "Estimated Fare Is: " + data+ '</br>' + "Please Confirm The Ride. " + '</p>');
		            	$("#makeBookingModal").modal();
		            	
	            	
	            }
	        });
        	}
	    });
	    
	    
	    
    $("#confirmBooking").click(function(event) {
	        
	        var pick = $('#pick').text();
	        var drop = $('#drop').text();
	        var cab = $('#cab').text();
	        var netId = $('#netId').val();
	        var submit = $('#confirmBooking').val();
	        var fare = $('#fare').val();
	        jQuery.ajax({
	            url: "CustomerController",
	            type: "post",
	            dataType: "text",
	            data: {
	                pick: pick,
	                drop: drop,
	                cab: cab,
	                netId : netId,
	                submit: submit,
	                fare: fare
	            },
	            success: function(dataBook) {
	            	
		            	$('#confirm').html('<p>'+dataBook+'</p>');
		            	$("#confirmBookingModal").modal();
		           	
	            	
	            }
	        });
        	
	    });
});
