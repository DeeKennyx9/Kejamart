 
function search(){

	var name = $('#name').val(); 
	  
	  $.ajax({  
	    type: "POST",  
	    url: contexPath + "/allproperty.html",
	    //dataType: 'json',
	    data: "name=" + name,
	    success: function(response){	    

	      for(i =0 ; i < response.result.length ; i++){
	      
	      }

	      resultInfo = response.result;	
	      
	      if(response.status == "FAIL"){
              
	      //$('#resultx').html();
              $('#resulttext').html("No results found for " +name+"...");
              document.getElementById("searchdisplay").style.display = "none";
              	      
	      } else {
	      
	      $('#resulttext').html("");
              document.getElementById("searchdisplay").style.display = "block";
	      document.getElementById("idx").value = resultInfo;
	      document.getElementById("ida").value = resultInfo;
	      document.getElementById("idb").value = resultInfo;
	      document.getElementById("idc").value = resultInfo;
	      document.getElementById("idd").value = resultInfo;
	      document.getElementById("ide").value = resultInfo;
	      document.getElementById("idf").value = resultInfo;
	      document.getElementById("idg").value = resultInfo;
	      
	      }
	      }	      
 
	  });  
	} 

