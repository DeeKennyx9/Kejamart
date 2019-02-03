

function animator(){
 var xf = '<img src="images/spinner.gif">';
 return xf;
}

function closeDialogue() {

    var x = document.getElementById('responseholder');
    x.style.display = 'none';

}

function clearResults() {

    //var x = document.getElementById('phonedetails');
    //x.style.display = 'none';
    
    var error = $('#error').val();
    
    $('#error').val('');

}

function showphone() {

    var x = document.getElementById('phonedetails');
    x.style.display = 'block';
    var y = document.getElementById('cell2');
    y.style.display = 'none';    
    
    var form = document.getElementById("sendEnquiry");
    form.submit();    

}

function showprange() {

    var x = document.getElementById('prange');
    var y = document.getElementById('prangex');
    var z = document.getElementById('propertyType');
        
    if (z.value == "For Sale") {
    	
    x.style.display = 'block';
    y.style.display = 'none';   
    	
    } else {
    
    y.style.display = 'block';
    x.style.display = 'none'; 
    
}
}

function showvals() {

    var x = document.getElementById('yyy');
    var y = document.getElementById('xxx');
    var z = document.getElementById('propertyType');
        
    if (z.value == "For Sale") {
    	
    x.style.display = 'block';
    y.style.display = 'none';   
    	
    } else {
    
    y.style.display = 'block';
    x.style.display = 'none'; 
    
}
}

function showrange() {

    var x = document.getElementById('rangeholder');
    var y = document.getElementById('rangeholderx');
    var z = document.getElementById('propertyType');
        
    if (z.value == "For Sale") {
    	
    x.style.display = 'block';
    y.style.display = 'none';   
    	
    } else {
    
    y.style.display = 'block';
    x.style.display = 'none'; 
    
}
}

function register() {

	var form = document.getElementById("registerForm");
	form.submit();

}

function sendMail() {

	var form = document.getElementById("emailForm");
	form.submit();

}

function goSearch() {

	var form = document.getElementById("search");
	form.submit();

}

function adminLogin() {

	var form = document.getElementById("adminForm");
	form.submit();

	} 

function doLogin() {

var form = document.getElementById("loginForm");
form.submit();

} 

function postRequest() {

var form = document.getElementById("requestForm");
form.submit();

}

function changePassword() {

	var form = document.getElementById("changeForm");
	form.submit();

}

function reqDial(){

document.getElementById('dial').innerHTML = '<div id ="responseholder" class="responseholder"><div id="info" class="info"></div><div class="submitb"><div class="dialoguelabel">Are you sure you want to delete?</div><div class="updatex" id="update" onclick="postRequest()">Delete Record</div><div class="cancel" id="cancel" onclick="closeDialogue()">Cancel</div></div>';

}

function postProperty() {

	var form = document.getElementById("propertyForm");
	form.submit();

}

function delProperty() {

	var form = document.getElementById("propertyFormDel");
	form.submit();

}

function delMessage() {

	var form = document.getElementById("messageFormDel");
	form.submit();

}

function edMessage() {

	var form = document.getElementById("messageForm");
	form.submit();

}

function delPropertyPic() {

	var form = document.getElementById("delForm");
	form.submit();

}

function propDialEd() {
	
	document.getElementById('dial').innerHTML = '<div id ="responseholder" class="responseholder"><div id="info" class="info"></div><div class="submitb"><div class="dialoguelabel">Are you sure you want to update?</div><div class="updatex" id="update" onclick="postProperty()">Update Record</div><div class="cancel" id="cancel" onclick="closeDialogue()">Cancel</div></div>';

}

function editMessages() {
	
	document.getElementById('dial').innerHTML = '<div id ="responseholder" class="responseholder"><div id="info" class="info"></div><div class="submitb"><div class="dialoguelabel">Are you sure you want to open this notification?</div><div class="updatex" id="update" onclick="edMessage()">Open Message</div><div class="cancel" id="cancel" onclick="closeDialogue()">Cancel</div></div>';

}

function deleteMessages() {
	
	document.getElementById('dial').innerHTML = '<div id ="responseholder" class="responseholder"><div id="info" class="info"></div><div class="submitb"><div class="dialoguelabel">Are you sure you want to delete?</div><div class="updatex" id="update" onclick="delMessage()">Delete Message</div><div class="cancel" id="cancel" onclick="closeDialogue()">Cancel</div></div>';

}

function propDialDel() {
	
	document.getElementById('dial').innerHTML = '<div id ="responseholder" class="responseholder"><div id="info" class="info"></div><div class="submitb"><div class="dialoguelabel">Are you sure you want to delete?</div><div class="updatex" id="updatex" onclick="delProperty()">Delete Record</div><div class="cancel" id="cancel" onclick="closeDialogue()">Cancel</div></div>';

}

function picDialDel() {
	
	document.getElementById('dial').innerHTML = '<div id ="responseholder" class="responseholder"><div id="info" class="info"></div><div class="submitb"><div class="dialoguelabel">Are you sure you want to delete?</div><div class="updatex" id="update" onclick="delPropertyPic()">Delete Record</div><div class="cancel" id="cancel" onclick="closeDialogue()">Cancel</div></div>';

}

function postPropPics() {

	var form = document.getElementById("picForm");
	form.submit();
	
}

function postPicsVal() {

	var form = document.getElementById("publishForm");
	validateUpload();

}

function getProps() {

	var form = document.getElementById("searchForm");
	form.submit();

}

function postMainPic() {

	var form = document.getElementById("mainPickForm");
	form.submit();
    
}

function postLogo() {

	var form = document.getElementById("logoForm");
	form.submit();
    
}

function postPics() {

	var form = document.getElementById("pickForm");
	form.submit();
  //$('#info').html("Your picture upload was succesful");
	//$('#error').hide();
	//$('#info').show();
   // window.setTimeout(function(){

   //     window.location = contexPath + "/propertypics.html";

   //       }, 3000);
    
}

function postFile() {

	var form = document.getElementById("pickForm");
	form.submit();
	//$('#info').html("Your file upload was succesful");
	//$('#error').hide();
	//$('#info').show();
    //window.setTimeout(function(){

      //  window.location = contexPath + "/resources.html";

        //  }, 3000);
    
}

function postAdverts() {

	var form = document.getElementById("advertForm");
	form.submit();
	$('#info').html("Your picture upload was succesful");
	$('#error').hide();
	$('#info').show();
        //window.setTimeout(function(){

        //window.location = contexPath + "/adverts.html";

          //}, 3000);
    
}

function viewDetails() {

	var form = document.getElementById("viewDetails");
	form.submit();

}

function detailsView() {

	var form = document.getElementById("detailsView");
	form.submit();

}

function publish() {

	var form = document.getElementById("publishForm");
	form.submit();

}

function validateUpload(){

var form = document.getElementById("pickForm");	
var photo = document.getElementById("photo").value;
if(photo!=''){
var checkimg = photo1.toLowerCase();
if (!checkimg.match(/(\.jpg|\.png|\.JPG|\.PNG|\.jpeg|\.JPEG)$/)){

	  $('#error').html("Please enter Image File Extensions .jpg,.png,.jpeg");
	  $('#info').hide('slow');
	  $('#error').show('slow');
	  //$('#error').delay(5000).fadeOut('slow');	
	  
document.getElementById("photo").focus();

} else {

postPics();

}
} 
} 

function searchEmail(){

	var email = document.getElementById("email").value;
	var firstName = document.getElementById("firstName").value;
	var lastName = document.getElementById("lastName").value;
	var category = document.getElementById("category").value;
	var role = document.getElementById("role").value;

	if(email!=''){
	$('#results').html("<p class='listclass'>"+firstName+"</p>");

	} else {

	$('#results').html("Please insert an email address to search ...");
	$('#results').show();
	$('#results').delay(4000).fadeOut('slow');
	}

}


function sendInfo(){  

var request; 
var emailx = document.getElementById("emailx").value; 
var url = contexPath + "allprofiles.html?emailx="+emailx;     
  
if(window.XMLHttpRequest){  
request = new XMLHttpRequest();  
}  
else if(window.ActiveXObject){  
request=new ActiveXObject("Microsoft.XMLHTTP");  
}  
  
try{  
request.onreadystatechange = getInfo;  
request.open("POST",url,true);  
request.send();  
}catch(e){alert("Unable to connect to server");}  
}  
  
function getInfo(){  
if(request.readyState==4){  
var val=request.responseText;  
$('#resultx').html("Hello ..." +val);
//document.getElementById('resultx').innerHTML=search;  
}  
}  

function showallf() {

    var x = document.getElementById('classifieds');
    var y = document.getElementById('classifiedsx');
    	
    x.style.display = 'block';
    y.style.display = 'none'; 


} 

function CommaFormatted(amount) {
	var delimiter = ","; // replace comma if desired
	var a = amount.split('.',2)
	var d = a[1];
	var i = parseInt(a[0]);
	if(isNaN(i)) { return ''; }
	var minus = '';
	if(i < 0) { minus = '-'; }
	i = Math.abs(i);
	var n = new String(i);
	var a = [];
	while(n.length > 3) {
		var nn = n.substr(n.length-3);
		a.unshift(nn);
		n = n.substr(0,n.length-3);
	}
	if(n.length > 0) { a.unshift(n); }
	n = a.join(delimiter);
	if(d.length < 1) { amount = n; }
	else { amount = n + '.' + d; }
	amount = minus + amount;
	return amount;
}




      
