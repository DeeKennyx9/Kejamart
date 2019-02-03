 
function search(){

	var email = $('#email').val(); 
	  
	  $.ajax({  
	    type: "POST",  
	    url: contexPath + "/allprofiles.html",
	    //dataType: 'json',
	    data: "email=" + email,
	    success: function(response){	    

	      for(i =0 ; i < response.result.length ; i++){
	      
	      }

	      resultInfo = response.result;	
	      
	      if(response.status == "FAIL"){
              
          $('#resulttext').html("No results found for " +email+"...");
          document.getElementById("searchdisplay").style.display = "none";
              	      
	      } else {
	      
	      $('#resulttext').html("");
          document.getElementById("searchdisplay").style.display = "block";
	      document.getElementById("idx").value = resultInfo;
	      
	      }
	      }	      
 
	  });  
	} 