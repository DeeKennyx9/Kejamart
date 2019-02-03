function showDiv(){

   var x = document.getElementById('category').value;
   
   if(x == "Commercial") {
   
      document.getElementById('bedroomholder').style.display = "none";
      document.getElementById('rangeholder').style.display = "none";
      
     } else {
      
      document.getElementById('bedroomholder').style.display = "block";
      document.getElementById('rangeholder').style.display = "block";
      
      }
      
}