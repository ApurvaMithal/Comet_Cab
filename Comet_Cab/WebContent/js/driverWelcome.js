	jQuery(window).ready(function() {

                $("#getRequest").click(function(event) {
                    
                	var driverId = $('#driverId').val();
                	var submit = $("#getRequest").val();
                    jQuery.ajax({
                        url: "DriverController",
                        type: "post",
                        dataType: "json",
                        data: {
                            driverId: driverId,
                            submit: submit
                            
                        },
                        success: function(data) {
                       
                            drawTable(data);
                            $("#startRide").click(function(event) {
                        	/*	var bookingId = this.parentNode.parentNode.cells[0].innerHTML;
                        		var submit = $("#startRide").val();
            				  jQuery.ajax({
                                url: "DriverController",
                                type: "post",
                                dataType: "text",
                                data: {
                                	bookingId: bookingId,
                                	submit: submit
                                },
                                success: function(data) {
                              */  	$('#start').html('<p>'+'Started the ride'+'</p>');
            		            	$("#startRideModal").modal();    
            		            	if(($("#endRide").hasClass('disabled')))
        		            			$("#endRide").removeClass('disabled');
            		            	$("#endRide").click(function(event) {
                                		var bookingId = this.parentNode.parentNode.cells[0].innerHTML;
                                		var submit = $("#endRide").val();
                    					jQuery.ajax({
                                            url: "DriverController",
                                            type: "post",
                                            dataType: "text",
                                            data: {
                                            	bookingId: bookingId,
                                            	submit: submit
                                            },
                                            success: function(data) {
                                                
                                            	$('#end').html('<p>'+'Ended the ride'+'</p>');
                        		            	$("#endRideModal").modal();         
                                            }
                                        });
                                    	});
                                    
                          //      }
                          //    });
                            });
                           

                            
                        }
                    });
                    
                });
               
                
                function drawTable(data) {
                	  $("#userRequest").find("tr:not(:first)").remove();
                 /*   for (var i = 0; i < data.length; i++) {
                        drawRow(data[i]);
                    }*/
                	  drawRow(data);
                }

                function drawRow(rowData) {
                    var row = $("<tr />");
	                    $("#userRequest").append(row); 
	                    row.append($("<td>" + rowData.bookingId + "</td>"));
	                    row.append($("<td>" + rowData.customer.firstName + "</td>"));
	                    row.append($("<td>" + rowData.customer.middleName + "</td>"));
	                    row.append($("<td>" + rowData.customer.lastName + "</td>"));
	                    row.append($("<td>" + rowData.customer.phoneNo + "</td>"));
	                    row.append($("<td>" + rowData.location.pickUpLocation + "</td>"));
	                    row.append($("<td>" + rowData.location.dropOffLocation + "</td>"));
	                    row.append($("<td>" + "<button type=\"button\" class=\"btn btn-success\" style=\"height:30px;width:100px\" id=\"startRide\" name= \"submit\" value=\"startRide\">Start Ride</button></td>"));
	                    row.append($("<td>" + "<button type=\"button\" class=\"btn btn-danger disabled\" style=\"height:30px;width:100px\" id=\"endRide\" name= \"submit\" value=\"endRide\">End Ride</button></td>"));

                }
           
	  });
    
    
    
                    
  
                
                
            	
                
            