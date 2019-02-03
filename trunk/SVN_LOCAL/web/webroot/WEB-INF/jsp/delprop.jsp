<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored ="false" %>

<html>
<head>
<title>Find Quality Homes with Ease - kejamart</title>

<script type="text/javascript" src="javascript/menuz.js"></script> 
    
<!-- JQuery, Jackson, Spring Validation --> 
        
<script src="<%=request.getContextPath() %>/javascript/deleteProperty.js"></script>        
<script src="<%=request.getContextPath() %>/javascript/jquery.js"></script>
<script type="text/javascript">
var contexPath = "<%=request.getContextPath() %>";
</script>
<script type="text/javascript">
   
$(document).ready(function() {
    
    var deleteLink = $("a:contains('Delete')");
    var id = $('#id').val();
       
    $(deleteLink).click(function(event) {    
     
        $.ajax({
            url: $(event.target).attr("href"),
            type: "POST",
                 
              beforeSend: function(xhr) {
              xhr.setRequestHeader("Accept", "application/json");
              xhr.setRequestHeader("Content-Type", "application/json");
              },
                 
              success: function() {
                var cont = "";
                var rowToDelete = $(event.target).closest("form");
                     
                rowToDelete.remove();
                     
                cont += "Property deleted.";                     
              
                $("#info").html(cont);       
  	    	$('#error').hide('slow');
	        $('#info').show();
	        //$('#info').delay(5000).fadeOut('slow');	
	        window.location = contexPath + "/property.html";
    	
              }
        });
   
    });

});

</script>

<link rel="stylesheet" href="css/kejani.css">
  <link rel = "shortcut icon" href="img/logoxs.png"/>

</head>
<body>

<div class="mymenu">

<div class="mylogo">
Kejani
</div>

<div class="menuitem">
Home
</div>
<div class="menuitem">
About
</div>

<div class="logincred">
<div class="profiledet">
Welcome Kenny &nbsp;&nbsp;|&nbsp;&nbsp; Log Out
<div class="eventimg"><img src ="led-icons/status_online.png"></div>
</div>
</div>

</div>

<div class="myleftpanel">
<ul>

<div class="buttonshead"><li><div class="eventimg"><img src ="led-icons/cog.png"></div>Settings</li></div>

<div class="buttons">${prof}</div>
<div class="buttons">${alert}</div>
<div class="buttons">${notif}</div>
<div class="buttons">${addp}</div>
<div class="buttons">${dash}</div>
<div class="buttons">${help2}</div>
<div class="buttons">${musers}</div>
<div class="buttons">${propmg}</div>
<div class="buttons">${addpp}</div>
<div class="buttons">${editpp}</div>
<div class="buttons">${advert}</div>

</ul>
</div>

<div class="mycontainer">

<div class="myrightpanel">
</div>

<div class="formholder">

<div class="formtitle">Delete Requests</div>

<form:form method="post" commandName="alerts" id="deletepropForm"> 

<div class="itemholder">
<div class="formlabel">Property Name</div>
<div class="formdisplay">
<form:input path="name" id="name" readonly="true" cssClass="textclassx"></form:input>
</div>
</div>

<div class="itemholder">
<div class="formlabel">County</div>
<div class="formdisplay">
<form:input path="county" id="county" readonly="true" cssClass="textclassx"></form:input>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Location</div><div class="formdisplay">
<form:input path="location" id="location" readonly="true" cssClass="textclassx"></form:input>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Property Type</div>
<div class="formdisplay">
<form:input path="propertyType" id="propertyType" readonly="true" cssClass="textclassx"></form:input>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Category</div>
<div class="formdisplay">
<form:input path="category" id="category" readonly="true" cssClass="textclassx"></form:input>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Contacts</div>
<div class="formdisplay">
<form:input path="contacts" id="contacts" readonly="true" cssClass="textclassx"></form:input>
</div>
</div>

<a><div class="update" id="delete">Delete Property</div></a><a href="/kejani/property.html"><div class="update" id="delete">Back to Properties</div></a>

</form:form> 

</div>

<div id="error" class="error"></div>
<div id="info" class="info"></div>

<div class="formfooter"></div>

</div>

</div>

</body>
<div class="myfooter2">
</html>