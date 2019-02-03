/**
 * 
 */
function publishPic() {  

	  // get the form values  
	  var fileName = $('#fileName').val();
	  var form = document.getElementById("publishForm");
	  form.submit();
	   
	  $.ajax({  
	    type: "POST",  
	    url: contexPath + "/publishpics.html",
	    //dataType: 'json',
	    data: "fileName=" + fileName,
	    success: function(response){
	    
	      // we have the response 
	      if(response.status == "SUCCESS"){
	      
	      picInfo = "<ol>";
	      for(i =0 ; i < response.result.length ; i++){
	      
	      picInfo += "<br><li><b>Picture Name</b> : " + response.result[i].fileName;
	      
	      }
           picInfo += "</ol>";
	    	  $('#info').html("Picture published succesfully.");
	    	  $('#error').hide('slow');
	          $('#info').show();
	          //$('#info').delay(3000).fadeOut('slow');		  
		  
	      } else {

	      errorInfo = "";
	      for(i =0 ; i < response.result.length ; i++){
	      errorInfo += "<br>" + (i + 1) + " . " + response.result[i].code;
	      
	      }
      
	    	  $('#error').html("Please note the below errors: " +errorInfo);
	    	  $('#info').hide('slow');
	    	  $('#error').show('slow');
	      }	      
	    },  
	    error: function(e){  
	      $('#error').html(e);
	    }  
	  });  
	}  