 
function searchProperty(){

	//var propstring = $('#country').val()+$('#county').val()+$('#location').val()+$('#propertyType').val()+$('#category').val()+$('#bedrooms').val()+$('#range').val();
	
	  var country = $('#country').val();
	  var county = $('#county').val();
	  var location = $('#location').val();
	  var propertyType = $('#propertyType').val();
	  var category = $('#category').val();
	  var bedrooms = $('#bedrooms').val();
	  var prange = $('#prange').val();
	  
	  $.ajax({  
	    type: "POST",  
	    url: contexPath + "/search.html",
	    //dataType: 'json',
	    data: "country=" + country + "&county=" + county + "&location=" + location+ "&propertyType=" + propertyType + "&category=" + category+ "&bedrooms=" +bedrooms+ "&prange=" +prange,
	    success: function(response){	    

	      for(i =0 ; i < response.result.length ; i++){
	      
	      }

	      resultInfo = response.result;	
	      
	      if(response.status == "FAIL"){
              
	      //$('#resultx').html();
              $('#rightpixel').html("No results found for " +propreff+"...");
              document.getElementById("searchdisplay").style.display = "none";
              	      
	      } else {
	      
	      $('#rightpixel').html("");
              document.getElementById("searchdisplay").style.display = "block";
	          document.getElementById("idx").value = resultInfo;
	      
	      }
	      }	      
 
	  });  
	} 

