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
	  var mailing = $('#mailing').val();
	   
	  $.ajax({  
	    type: "POST",  
	    url: contexPath + "/profile.html",
	    //dataType: 'json',
	    data: "firstName=" + firstName + "&lastName=" + lastName+ "&email=" + email+ "&password=" + password+ "&mobile=" + mobile+ "&mailing=" + mailing,
	    success: function(response){
	    	
		  var x = document.getElementById('alert-danger');
	      var y = document.getElementById('alert-success'); 	    	
	    
	      // we have the response 
	      if(response.status == "SUCCESS"){
	    
	      x.style.display = 'none';
	      y.style.display = 'block';
	      
	      profileInfo = "<ol>";
	      for(i =0 ; i < response.result.length ; i++){
	      
	      profileInfo += "<br><li><b>First Name</b> : " + response.result[i].firstName +
	      ";<b> Last Name</b> : " + response.result[i].lastName +
	      ";<b> Email</b> : " + response.result[i].email +
	      ";<b> Password</b> : " + response.result[i].password +
	      ";<b> Mobile</b> : " + response.result[i].mobile +
	      ";<b> Mailing</b> : " + response.result[i].mailing;
	      
	      }
              profileInfo += "</ol>";
	    	  
	       $('#alert-success').html("Your profile has been updated succesfully");
	    	  //$('#firstName').val('');
	    	  //$('#lastName').val('');
	    	  //$('#email').val('');
	    	  //$('#password').val('');
	    	  //$('#mobile').val('');
	    	  //$('#role').val('');
		  //$('#error').hide('slow');
		  //$('#info').show();
		  //$('#info').delay(3000).fadeOut('slow');
		  
	      } else {
	      //var myJSON = JSON.stringify(errorInfo);
	      errorInfo = "";
	      for(i =0 ; i < response.result.length ; i++){
	      errorInfo += "<br>" + (i + 1) + " . " + response.result[i].code;
	      
	      }
          y.style.display = 'none';
          x.style.display = 'block';      
	      $('#alert-danger').html("Please note the errors below: " +errorInfo);
	    	  //$('#info').hide('slow');
	    	  //$('##alert-danger').show('slow');
	      }	      
	    },  
	    error: function(e){  
	      //alert('Error: ' + e); 
	      $('#alert-danger').html(e);
	    }  
	  });  
	}  
	
	
