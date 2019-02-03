/**
 * 
 */
function changePass() {  

	  // get the form values  
	  var email = $('#email').val();
	  var password = $('#password').val();
	   
	  $.ajax({  
	    type: "POST",  
	    url: contexPath + "/changepassword.html",
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

          	    var x = document.getElementById('alert-danger');
        	    var y = document.getElementById('exclamation-sign');
        	    var z = document.getElementById('alert-success'); 	    
        	    
	    	  $('#z').html("Password has been updated succesfully");
	    	  $('#email').val('');
	    	  $('#password').val('');	    	  
      	    
      	    x.style.display = 'none';
      	    y.style.display = 'none';
      	    z.style.display = 'block';   
		  
	      } else {
	      //var myJSON = JSON.stringify(errorInfo);
	      errorInfo = "";
	      for(i =0 ; i < response.result.length ; i++){
	      errorInfo += "<br>" + (i + 1) + " . " + response.result[i].code;
	      
	      }
      
	    	  $('#x').html("Please note the errors below: " +errorInfo);
	    	  
	      	    x.style.display = 'block';
	      	    y.style.display = 'block';
	      	    z.style.display = 'none'; 
	    	  
	      }	      
	    },  
	    error: function(e){  
	      //alert('Error: ' + e); 
	      $('#alert-danger').html(e);
	    }  
	  });  
	}  
	
	
