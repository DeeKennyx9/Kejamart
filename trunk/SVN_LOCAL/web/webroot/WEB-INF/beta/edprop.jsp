<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored ="false" %>

<html>
<head>
<title>Kejani - Your Property Search and Listing Made Easy</title>

<script type="text/javascript" src="javascript/menuz.js"></script> 
    
<!-- JQuery, Jackson, Spring Validation --> 
        
<script src="<%=request.getContextPath() %>/javascript/deleteProperty.js"></script>        
<script src="<%=request.getContextPath() %>/javascript/jquery.js"></script>
<script type="text/javascript">
var contexPath = "<%=request.getContextPath() %>";
</script>

<script type="text/javascript">

function editProperty() {

var form = document.getElementById("editpropForm");
form.submit();

}

</script>


<link rel="stylesheet" href="css/kejani.css">

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
<div class="buttons">${dash}</div>
<div class="buttons">${help}</div>
<div class="buttons">${addp}</div>
<div class="buttons">${musers}</div>
<div class="buttons">${addpp}</div>
<div class="buttons">${editpp}</div>
<div class="buttons">${malert}</div>
<div class="buttons">${cnotif}</div>
<div class="buttons">${advert}</div>
<div class="buttons">${help2}</div>

</ul>
</div>

<div class="mycontainer">

<div class="myrightpanel">
</div>

<div class="formholder">

<div class="formtitle">Edit Property</div>

<form:form method="post" commandName="property" modelAttribute="property" id="editpropForm"> 

<div class="itemholder">
<div class="formlabel">Property Name</div>
<div class="formdisplay">
<form:input path="name" id="name" cssClass="textclassax"></form:input>
<form:errors path="name" cssStyle="color: #ff0000;"></form:errors>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Contacts</div>
<div class="formdisplay">
<form:input path="contacts" id="contacts" cssClass="textclassax"></form:input>
<form:errors path="contacts" cssStyle="color: #ff0000;"></form:errors>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Website</div>
<div class="formdisplay">
<form:input path="website" id="website" cssClass="textclassax"></form:input>
<form:errors path="website" cssStyle="color: #ff0000;"></form:errors>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Amount</div>
<div class="formdisplay">
<form:input path="amount" id="amount" cssClass="textnumber"></form:input>
<form:errors path="amount" cssStyle="color: #ff0000;"></form:errors>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Property Description</div>
<div class="formdisplay">
<form:input path="description" id="description" cssClass="textclassax"></form:input>
<form:errors path="description" cssStyle="color: #ff0000;"></form:errors>
</div>
</div>

<div class="itemholder">
<div class="formlabel">County</div>
<div class="formdisplay">
<form:input path="county" id="county" cssClass="textclassax"></form:input>
<form:errors path="county" cssStyle="color: #ff0000;"></form:errors>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Location</div><div class="formdisplay">
<form:input path="location" id="location" cssClass="textclassax"></form:input>
<form:errors path="location" cssStyle="color: #ff0000;"></form:errors>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Property Type</div>
<div class="formdisplay">
<form:input path="propertyType" id="propertyType" cssClass="textclassax"></form:input>
<form:errors path="propertyType" cssStyle="color: #ff0000;"></form:errors>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Category</div>
<div class="formdisplay">
<form:input path="category" id="category" cssClass="textclassax"></form:input>
<form:errors path="category" cssStyle="color: #ff0000;"></form:errors>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Bedrooms</div>
<div class="formdisplay">
<form:input path="bedrooms" id="bedrooms" cssClass="textnumber"></form:input>
<form:errors path="category" cssStyle="color: #ff0000;"></form:errors>
</div>
</div>

<div class="update" id="edit" onclick="editProperty()">Edit Property</div></a><a href="/kejani/property.html"><div class="update" id="cancel">Back to Properties</div>

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