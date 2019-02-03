/**
 * 
 */
function doAjaxPost() {  

	  // get the form values  
	  var firstName = $('#firstName').val();
	  var lastName = $('#lastName').val();
	  var email = $('#email').val();
	  var password = $('#password').val();
	  var mobile = $('#mobile').val();
	  var role = $('#role').val();
	  var role = $('#mailing').val();
	   
	  $.ajax({  
	    type: "POST",  
	    url: contexPath + "/profile.html",
	    //dataType: 'json',
	    data: "firstName=" + firstName + "&lastName=" + lastName+ "&email=" + email+ "&password=" + password+ "&mobile=" + mobile+ "&role=" + role+ + "&mailing=" +mailing,
	    success: function(response){
	    
	      // we have the response 
	      if(response.status == "SUCCESS"){
	      
	      profileInfo = "<ol>";
	      for(i =0 ; i < response.result.length ; i++){
	      
	      profileInfo += "<br><li><b>First Name</b> : " + response.result[i].firstName +
	      ";<b> Last Name</b> : " + response.result[i].lastName +
	      ";<b> Email</b> : " + response.result[i].email +
	      ";<b> Password</b> : " + response.result[i].password +
	      ";<b> Mobile</b> : " + response.result[i].mobile +
	      ";<b> Category</b> : " + response.result[i].role;
	      ";<b> Mailing</b> : " + response.result[i].mailing;
	      
	      }

                  profileInfo += "</ol>";

	    	  
	    	  $('#info').html("Profile updated succesfully");
	    	  //$('#firstName').val('');
	    	  //$('#lastName').val('');
	    	  //$('#email').val('');
	    	  //$('#password').val('');
	    	  //$('#mobile').val('');
	    	//$('#mailing').val('');
	    	  //$('#role').val('');
		  $('#error').hide('slow');
		  $('#info').show();
		  $('#info').delay(3000).fadeOut('slow');
		  
	      } else {
	      //var myJSON = JSON.stringify(errorInfo);
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
	
	
