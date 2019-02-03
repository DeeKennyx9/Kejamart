/**
 * 
 */
function editProp() {  

	  // get the form values  
	  var name = $('#name').val();
	  var email = $('#email').val();
	  var company = $('#company').val();
	  var contacts = $('#contacts').val();
	  var website = $('#website').val();
	  var amount = $('#amount').val();
	  var description = $('#description').val();
	  var parking = $('#parking').val();
	  var bathroom = $('#bathroom').val();	  
	  var latitude = $('#latitude').val();
	  var longitude = $('#longitude').val();
	  var videourl = $('#videourl').val();
	  var country = $('#country').val();
	  var county = $('#county').val();
	  var location = $('#location').val();
	  var propertyType = $('#propertyType').val();
	  var category = $('#category').val();
	  var bedrooms = $('#bedrooms').val();
	  var prange = $('#prange').val();
	  var schedule = $('#schedule').val();
	  var status = $('#status').val();
	   
	  $.ajax({  
	    type: "POST",  
	    url: contexPath + "/editproperty.html",
	    //dataType: 'json',
	    data: "name=" + name + "&company=" + company + "&county=" + county + "&country=" + country + "&location=" + location+ "&email=" + email + "&videourl=" + videourl + "&website=" + website +"&propertyType=" + propertyType + "&category=" + category+ "&bedrooms=" + bedrooms + "&schedule=" + schedule + "&prange=" + prange + "&longitude=" + longitude + "&latitude=" + latitude + "&bathroom=" + bathroom + "&parking=" + parking + "&description=" + description + "&contacts=" + contacts + "&amount=" + amount + "&status=" + status,
	    success: function(response){
	    
	      // we have the response 
	      if(response.status == "SUCCESS"){
	      
	      alertInfo = "<ol>";
	      for(i =0 ; i < response.result.length ; i++) {
	      
	      alertInfo += "<br><li><b>County</b> : " + response.result[i].county +  
	      ";<b> Name</b> : " + response.result[i].name +
	      ";<b> Country</b> : " + response.result[i].country +
	      ";<b> Company</b> : " + response.result[i].company +
	      ";<b> Location</b> : " + response.result[i].location +	      
	      ";<b> Property Type</b> : " + response.result[i].propertyType +
	      ";<b> Category</b> : " + response.result[i].category +
	      ";<b> Bedrooms</b> : " + response.result[i].bedrooms +
	      ";<b> Range</b> : " + response.result[i].prange +
	      ";<b> Description</b> : " + response.result[i].description +	      
	      ";<b> Contacts</b> : " + response.result[i].contacts +
	      ";<b> Website</b> : " + response.result[i].website +
	      ";<b> Schedule</b> : " + response.result[i].schedule +
	      ";<b> Amount</b> : " + response.result[i].amount +
	      ";<b> Latitude</b> : " + response.result[i].latitude +
	      ";<b> Longitude</b> : " + response.result[i].longitude +
	      ";<b> Bathroom</b> : " + response.result[i].bathroom +
	      ";<b> Email</b> : " + response.result[i].email +
	      ";<b> Parking</b> : " + response.result[i].parking +
	      ";<b> Status</b> : " + response.result[i].status +
	      ";<b> Video URL</b> : " + response.result[i].videourl;
	      
	      }

           alertInfo += "</ol>";
	    	  $('#info').html("Property updated succesfully.");
	    	  //$('#name').val('');
	    	  //$('#county').val('');
	    	  //$('#location').val('');
	    	  //$('#propertyType').val('');
	    	  //$('#category').val('');
	    	  //$('#bedrooms').val('');	    	  
	    	  //$('#description').val('');
	    	  //$('#contacts').val('');
	    	  //$('#amount').val('');	    	  
	    	  //$('#website').val('');
	    	  //$('#schedule').val('');
	    	  $('#error').hide('slow');
			  $('#info').show();
			  $('#info').delay(3000).fadeOut('slow');		  
		  
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