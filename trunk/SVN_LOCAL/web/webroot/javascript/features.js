/**
 * 
 */
function saveFeature() {  

	  // get the form values  
	  var feature = $('#feature').val();
	   
	  $.ajax({  
	    type: "POST",  
	    url: contexPath + "/features.html",
	    //dataType: 'json',
	    data: "feature=" + feature,
	    success: function(response){
	    
	      // we have the response 
	      if(response.status == "SUCCESS"){
	      
	      alertInfo = "<ol>";
	      for(i =0 ; i < response.result.length ; i++) {
	      
	      alertInfo += "<br><li><b>Feature</b> : " + response.result[i].feature;	      
	      
	      }

           alertInfo += "</ol>";
	    	  $('#info').html("Feature added succesfully.");
	    	  $('#feature').val('');   	  
	    	  $('#error').hide('slow');
			  $('#info').show();
			  //$('#info').delay(3000).fadeOut('slow');		  
		  
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