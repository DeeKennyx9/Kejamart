/**
 * 
 */
function loginPost() {  

	  // get the form values  
	  var email = $('#email').val();
	  var password = $('#password').val();
	   
	  $.ajax({  
	    type: "POST",  
	    url: contexPath + "/login.html",
	    //dataType: 'json',
	    data: "email=" + email+ "&password=" + password,
	    success: function(response){
	    
	      // we have the response 
	      if(response.status == "SUCCESS"){
	      
	      profileInfo = "<ol>";
	      for(i =0 ; i < response.result.length ; i++){
	      
	      profileInfo += "<br><li><b>Email</b> : " + response.result[i].email +
	      ";<b> Password</b> : " + response.result[i].password;
	      
	      }

                  profileInfo += "</ol>";
	    	  $('#info').html("User has logged in");
	    	  $('#email').val('');
	    	  $('#password').val('');
		  $('#error').hide('slow');
		  $('#info').show('slow');
		  
	      } else {

	      errorInfo = "";
	      for(i =0 ; i < response.result.length ; i++){
	      errorInfo += "<br>" + (i + 1) + " . " + response.result[i].code;
	      
	      }
      
	    	  $('#error').html("Please note the errors below: " +errorInfo);
	    	  $('#info').hide('slow');
	    	  $('#error').show('slow');
	      }	      
	    },  
	    error: function(e){  
	      //alert('Error: ' + e); 
	      $('#error').html(e);
	    }  
	  });  
	}  