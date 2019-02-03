/**
 * 
 */
function addPics() {  

	  // get the form values  
	  var path = $('#path').val();
	  var form = document.getElementById("pickForm");
	  form.submit();
	   
	  $.ajax({  
	    type: "POST",  
	    url: contexPath + "/propertypics.html",
	    //dataType: 'json',
	    data: "path=" + path,
	    success: function(response){
	    
	      // we have the response 
	      if(response.status == "SUCCESS"){
	      
	      picInfo = "<ol>";
	      for(i =0 ; i < response.result.length ; i++){
	      
	      picInfo += "<br><li><b>Picture</b> : " + response.result[i].path;
	      
	      }
           picInfo += "</ol>";
	    	  $('#info').html("Picture was added succesfully.");
	    	  $('#photo').val('');
	    	  $('#error').hide('slow');
	          $('#info').show();
	          $('#info').delay(3000).fadeOut('slow');		
	          
	          window.setTimeout(function(){

		          window.location = contexPath + "/propertypics.html";
		  
                    }, 5000);
		  
	      } else {

	      errorInfo = "";
	      for(i =0 ; i < response.result.length ; i++){
	      errorInfo += "<br>" + (i + 1) + " . " + response.result[i].code;
	      
	      }      
	    	  //$('#error').html("Please note the below errors: " +errorInfo);
	    	  $('#error').html("Only a Maximum of 12 Pictures are allowed for each property.");
	    	  $('#info').hide('slow');
	    	  $('#error').show('slow');
	      }	      
	    },  
	    error: function(e){  
	      $('#error').html(e);
	    }  
	  });  
	}  