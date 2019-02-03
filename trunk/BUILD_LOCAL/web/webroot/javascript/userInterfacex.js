function showDiv(){

   var x = document.getElementById('category').value;
   
   if(x == "Commercial") {
   
      document.getElementById('bedrooms').style.display = "none";
      document.getElementById('prange').style.display = "none";
      
     } else {

      document.getElementById('bedrooms').style.display = "block";
      document.getElementById('prange').style.display = "block";      
      
      }
      
}