/**
 * 
 */
function saveProperty() {  

	  // get the form values  
	  var name = $('#name').val();
	  var company = $('#company').val();
	  var country = $('#country').val();
	  var county = $('#county').val();
	  var location = $('#location').val();
	  var propertyType = $('#propertyType').val();
	  var category = $('#category').val();
	  var bedrooms = $('#bedrooms').val();
	  var prange = $('#prange').val();
	  var description = $('#description').val();
	  var contacts = $('#contacts').val();
	  var website = $('#website').val();
	  var amount = $('#amount').val();
	   
	  $.ajax({  
	    type: "POST",  
	    url: contexPath + "/property.html",
	    //dataType: 'json',
	    data: "name=" + name + "&country=" + country + "&company=" + company + "&county=" + county + "&location=" + location+ "&website=" + website +"&propertyType=" + propertyType + "&category=" + category+ "&bedrooms=" + bedrooms + "&description=" + description + "&prange=" + prange + "&contacts=" + contacts + "&amount=" + amount,
	    success: function(response){
	    
	      // we have the response 
	      if(response.status == "SUCCESS"){
	      
	      alertInfo = "<ol>";
	      for(i =0 ; i < response.result.length ; i++){
	      
	      alertInfo += "<br><li><b>County</b> : " + response.result[i].county +
	      ";<b> Country</b> : " + response.result[i].country +
	      ";<b> Name</b> : " + response.result[i].name +
	      ";<b> Company</b> : " + response.result[i].company +
	      ";<b> Location</b> : " + response.result[i].location +	      
	      ";<b> Property Type</b> : " + response.result[i].propertyType +
	      ";<b> Category</b> : " + response.result[i].category; +
	      ";<b> Bedrooms</b> : " + response.result[i].bedrooms; +
	      ";<b> Range</b> : " + response.result[i].prange; +
	      ";<b> Description</b> : " + response.result[i].location +	      
	      ";<b> Contacts</b> : " + response.result[i].propertyType +
	      ";<b> Website</b> : " + response.result[i].website +
	      ";<b> Amount</b> : " + response.result[i].bedrooms;	      
	      
	      }

           alertInfo += "</ol>";
	    	  $('#info').html("Property added succesfully.");
	    	  $('#name').val('');
	    	  $('#company').val('');
	    	  $('#country').val('');
	    	  $('#county').val('');
	    	  $('#location').val('');
	    	  $('#propertyType').val('');
	    	  $('#category').val('');
	    	  $('#bedrooms').val('');	 
	    	  $('#prange').val('');	
	    	  $('#description').val('');
	    	  $('#website').val('');	
	    	  $('#contacts').val('');
	    	  $('#amount').val('');	    	  
	    	  $('#error').hide('slow');
			  $('#info').show();
			  //$('#info').delay(3000).fadeOut('slow');	
			  
	          	          window.setTimeout(function(){
		  
		  		          window.location = contexPath + "/property.html";
		  		  
                    }, 5000);
		  
	      } else {

	      errorInfo = "";
	      for(i =0 ; i < response.result.length ; i++){
	      errorInfo += "<br>" + (i + 1) + " . " + response.result[i].code;
	      
	      }
      
	    	  $('#error').html("Please note the errors below : " +errorInfo);
	    	  $('#info').hide('slow');
	    	  $('#error').show('slow');
	      }	      
	    },  
	    error: function(e){  
	      $('#error').html(e);
	    }  
	  });  
	}  