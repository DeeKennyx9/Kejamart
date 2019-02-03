/**
 * 
 */
function sendEnquiry() {  

	  // get the form values  
	  var cell = $('#cell').val();
	  var emailx = $('#emailx').val();
	  var namex = $('#namex').val();
	   
	  $.ajax({  
	    type: "POST",  
	    url: contexPath + "/viewdetails.html",
	    //dataType: 'json',
	    data: "cell=" + cell + "&emailx=" + emailx + "&namex=" + namex,
	    success: function(response){
	    
	      // we have the response 
	      if(response.status == "SUCCESS"){
	      
	      alertInfo = "<ol>";
	      for(i =0 ; i < response.result.length ; i++){
	      
	      alertInfo += "<br><b>Cell</b> : " + response.result[i].cell+
	      ";<b> Name</b> : " + response.result[i].namex +
	      ";<b> Email</b> : " + response.result[i].emailx;
	      
	      }

           alertInfo += "</ol>";
	    	  $('#info').html("<br>" +"A message has been sent to the Agent. You shall be contacted shortly.");
	              var x = document.getElementById('cell2');
                  x.style.display = 'none';
    	          var w = document.getElementById('emailx2');
                  w.style.display = 'none';   
    	          var y = document.getElementById('namex2');
                  y.style.display = 'none';                    
	              var z = document.getElementById('contacts');
                  z.style.display = 'block';          
  
	      $('#error').hide('slow');
		  $('#info').show();               
		  
	      } else {

	      errorInfo = "";
	      for(i =0 ; i < response.result.length ; i++){
	      errorInfo += "<br>" + response.result[i].code;
	      
	      }
      
	    	  $('#error').html(errorInfo);
	    	  $('#info').hide('slow');
	    	  $('#error').show('slow');
	      }	      
	    },  
	    error: function(e){  
	      $('#error').html(e);
	    }  
	  });  
	}  