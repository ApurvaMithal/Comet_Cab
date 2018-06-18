

$(document).ready(function() {

	//$(function() {
		$(".dropdown-menu li a").click(function(){
				var selText = $(this).text();
				$(this).parents('.dropdown').find('.dropdown-toggle').html(selText + ' <span class="caret"></span>');	
				$(this).parents('.dropdown').find('.dropdown-toggle').val(selText);
			});
		//});
});


  jQuery(window).ready(function() {
	    $("#estimateButton").click(function(event) {
	        
	        var pick = $('#pick').text();
	        var drop = $('#drop').text();
	        var cab = $('#cab').text();
	        var netId = $('#netId').val();
	        var submit = $('#estimateButton').val();
	        
	        if($('#pick').val()=="None"){
        		if(!($("#confirm").hasClass('disabled')))
        			$("#confirm").addClass('disabled');
        		$('#estimate').html('<p>'+'Select a Pickup Location '+'</p>');
            	$("#myModal").modal();
        	}
        	else if($('#drop').val()=="None"){
        		if(!($("#confirm").hasClass('disabled')))
        			$("#confirm").addClass('disabled');
        		$('#estimate').html('<p>'+'Select a Drop Location '+'</p>');
            	$("#myModal").modal();
        	}
        	else if($('#cab').val()=="None"){
        		if(!($("#confirm").hasClass('disabled')))
        			$("#confirm").addClass('disabled');
        		$('#estimate').html('<p>'+'Select a Cab Type '+'</p>');
            	$("#myModal").modal();
        	}
        	else if(pick == drop){
        		if(!($("#confirm").hasClass('disabled')))
        			$("#confirm").addClass('disabled');
        		$('#estimate').html('<p>'+'Pickup Location Cannot Be Same As Drop Location'+'</p>');
            	$("#myModal").modal();	
        	} 
	        
        	else{
	        jQuery.ajax({
	            url: "BookingController",
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
	            		if(($("#confirm").hasClass('disabled')))
	            			$("#confirm").removeClass('disabled');
	            		
		            	$('#estimate').html('<p>'+ "Estimated Fare Is: " + data+ '</br>' + "Please Confirm The Ride. " + '</p>');
		            	$("#myModal").modal();
		            	
	            	
	            }
	        });
        	}
	    });
	    
	    
	    
    $("#confirm").click(function(event) {
	        
	        var pick = $('#pick').text();
	        var drop = $('#drop').text();
	        var cab = $('#cab').text();
	        var netId = $('#netId').val();
	        var submit = $('#confirm').val();
	        var fare = $('#fare').val();
	        jQuery.ajax({
	            url: "BookingController",
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
	            	
	            	/*
	            	 else if( not enough balance){
	            	 	if(!($("#confirm").hasClass('disabled')))
	            			$("#confirm").addClass('disabled');
	            		$('#estimate').html('<p>'+'INSUFFICIENT CARD BALANCE'+'</p>');
		            	$("#myModal").modal();	
	            	}
	            	
	            	*/
		            	$('#book').html('<p>'+dataBook+'</p>');
		            	$("#myModalBook").modal();
		           	
	            	
	            }
	        });
        	
	    });
});
