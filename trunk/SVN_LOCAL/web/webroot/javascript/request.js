/**
 * 
 */
function saveRequest() {  

	  // get the form values  
	  var country = $('#country').val();
	  var county = $('#county').val();
	  var location = $('#location').val();
	  var propertyType = $('#propertyType').val();
	  var category = $('#category').val();
	  var prange = $('#prange').val();
	  var bedrooms = $('#bedrooms').val();
	   
	  $.ajax({  
	    type: "POST",  
	    url: contexPath + "/requests.html",
	    //dataType: 'json',
	    data: "country=" + country + "&county=" + county + "&location=" + location+ "&propertyType=" + propertyType + "&category=" + category+ "&prange=" + prange + "&bedrooms=" + bedrooms,
	    success: function(response){
	    
	      // we have the response 
	      if(response.status == "SUCCESS"){
	      
	      alertInfo = "<ol>";
	      for(i =0 ; i < response.result.length ; i++){
	      
	      alertInfo += "<br><li><b>Country</b> : " + response.result[i].country +
	      ";<b> County</b> : " + response.result[i].county +
	      ";<b> Location</b> : " + response.result[i].location +
	      ";<b> Property Type</b> : " + response.result[i].propertyType +
	      ";<b> Category</b> : " + response.result[i].category;
	      ";<b> Range</b> : " + response.result[i].prange;
	      ";<b> Bedrooms</b> : " + response.result[i].bedrooms;
	      
	      }

           alertInfo += "</ol>";
	    	  $('#info').html("<img src='led-icons/accept.png'>&nbsp;Your property request was succesful");
	    	  $('#country').val('');
	    	  $('#county').val('');
	    	  $('#location').val('');
	    	  $('#propertyType').val('');
	    	  $('#category').val('');
	    	  $('#bedrooms').val('');
	    	  $('#prange').val('');
	    	  $('#error').hide('slow');
			  $('#info').show();
			  //('#info').delay(5000).fadeOut('slow');
			  
	          window.setTimeout(function(){

		          window.location = contexPath + "/requests.html";
		  
                    }, 5000);
		  
	      } else {

	      errorInfo = "";
	      for(i =0 ; i < response.result.length ; i++){
	      errorInfo += "<br>" + (i + 1) + " . " + response.result[i].code;
	      
	      }
      
	    	  $('#error').html("<img src='led-icons/cross_octagon.png'>&nbsp;Please note, only 5 requests are allowed for each user : " +errorInfo);
	    	  $('#info').hide('slow');
	    	  $('#error').show('slow');
	      }	      
	    },  
	    error: function(e){  
	      $('#error').html(e);
	    }  
	  });   
	 } 