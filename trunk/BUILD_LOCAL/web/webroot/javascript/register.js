/**
 * 
 */
function doAjaxPost() {  

	  // get the form values  
	  var firstName = $('#firstName').val();
	  var lastName = $('#lastName').val();
	  var email = $('#email').val();
	  var mobile = $('#mobile').val();
	  var password = $('#password').val();
	  var role = $('#role').val();
	  var mailing = $('#mailing').val();
	   
	  $.ajax({  
	    type: "POST",  
	    url: contexPath + "/register.html",
	    //dataType: 'json',
	    data: "firstName=" + firstName + "&lastName=" + lastName+ "&email=" + email+ "&password=" + password + "&mobile=" + mobile+ "&role=" + role+ "&mailing=" + mailing,
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
	      ";<b> Mailing</b> : " + response.result[i].mailing +
	      ";<b> Category</b> : " + response.result[i].role;
	      
	      }

             profileInfo += "</ol>";             
                 
            
	    	  $('#alert-success').html("Profile created successfully. Password has been sent to your email.");
	    	  $('#firstName').val('');
	    	  $('#lastName').val('');
	    	  $('#email').val('');
	    	  $('#mobile').val('');
	    	  $('#password').val('');
	    	  $('#mailing').val('');
	    	  $('#role').val('');
	    	  
		  //$('#error').hide('slow');
		  //$('#info').show('slow');
		  
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
	    	  //$('#alert-danger').show('slow');
	      }	      
	    },  
	    error: function(e){  
	      //alert('Error: ' + e); 
	      $('#alert-danger').html(e);
	    }  
	  });  
	}  