function doRefresh(){

	  // get the form values  
	  var county = $('#county').val();
	  var location = $('#location').val();
	  var propertyType = $('#propertyType').val();
	  var category = $('#category').val();
	   
	  $.ajax({  
	    type: "POST",  
	    url: contexPath + "/requests.html",
	    data: "county=" + county + "&location=" + location+ "&propertyType=" + propertyType+ "&category=" + category,
	    success: function(response){
	      
	      alertInfo = "<ol>";
	      for(i =0 ; i < response.result.length ; i++){
	      
	      alertInfo += "<br><li><b>County</b> : " + response.result[i].county +
	      ";<b> Location</b> : " + response.result[i].location +
	      ";<b> Property Type</b> : " + response.result[i].propertyType +
	      ";<b> Category</b> : " + response.result[i].category;
	      
	      }

              alertInfo += "</ol>";
	    	  $('#listData').html(alertInfo);
	    	  $('#county').val('');
	    	  $('#location').val('');
	    	  $('#propertyType').val('');  	  
	    	  $('#error').hide('slow');
	          $('#info').show();
	          $('#info').delay(3000).fadeOut('slow');
     
	    },  

	  });  
	  
	} 
	
	
	
