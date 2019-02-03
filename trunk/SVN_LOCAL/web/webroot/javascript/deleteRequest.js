/**
 * 
 */
function deleteAlert() {  

    $(document).ready(function() {
        
		var deleteLink = $("a:contains('Delete')");
		      
		$('#requestForm').submit(function(event) {
		
		 var id = $('#id').val();
    	  
			$.ajax({
				url: $("#requestForm").attr( "action"),
   			  	type: "DELETE",
			  	
			  	//beforeSend: function(xhr) {
			  	//	xhr.setRequestHeader("Accept", "application/json");
			  	//	xhr.setRequestHeader("Content-Type", "application/json");
			  	//},
			  	
			  	success: function() {
			  		var cont = "";
			  		var rowToDelete = $(event.target).closest("tr");			  		
			  		rowToDelete.remove();
			  		
			  		cont += "Request Deleted";
			  		
                                   $("#info").html(cont);       
  	    	                   $('#error').hide('slow');
 	                           $('#info').show();
	                           $('#info').delay(5000).fadeOut('slow');	   		
			  	}
			});
  
			event.preventDefault();
		});
       
});   

}