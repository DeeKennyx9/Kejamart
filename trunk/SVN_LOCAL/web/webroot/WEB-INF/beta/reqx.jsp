<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page isELIgnored ="false" %>

<!DOCTYPE html>
<html>
<head>
<title>Kejani - Your Property Search and Listing Made Easy</title>

<script type="text/javascript" src="javascript/menuz.js"></script> 
    
<!-- JQuery, Jackson, Spring Validation --> 
        
<script src="<%=request.getContextPath() %>/javascript/jquery.js"></script>
<script type="text/javascript">
var contexPath = "<%=request.getContextPath() %>";
</script>
<script src="<%=request.getContextPath() %>/javascript/request.js"></script>
<script src="<%=request.getContextPath() %>/javascript/refreshPages.js"></script>
<script src="<%=request.getContextPath() %>/javascript/kdialogue.js"></script>
<script src="<%=request.getContextPath() %>/javascript/userInterface.js"></script>
<script src="<%=request.getContextPath() %>/javascript/deleteLinks.js"></script>

<script type="text/javascript">
   
    $(document).ready(function() {
		      
		$().ready(function() {
		    $().load(function(event) {
 
			$.ajax({
				url: contexPath + "/requests.html",
   			  	type: "GET",
			  	
			  	success: function() {
			  	
			  	}
			});			
		    });
		});
});   
</script>

</head>

<link rel="stylesheet" href="css/kejani.css">

</head>
<body id ="main">

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
<div class="buttons">${help2}</div>
<div class="buttons">${addp}</div>
<div class="buttons">${musers}</div>
<div class="buttons">${malert}</div>
<div class="buttons">${addpp}</div>
<div class="buttons">${editpp}</div>
<div class="buttons">${advert}</div>

</ul>
</div>

<div class="mycontainer">

<div class="myrightpanel">
</div>

<div class="formholder">

<div class="formtitle">Manage Requests</div>

<div class="itemholder">
<div class="formlabel">County</div><div class="formdisplay">
<select type="select" id="county" class="selectclass">
<option value="" >---Select---</option>
<option value="Nairobi" >Nairobi</option>
<option value="Machakos" >Machakos</option>
<option value="Kajiado North" >Kajiado North</option>
<option value="Nakuru" >Nakuru</option>
<option value="Mombasa" >Mombasa</option>
<option value="Kisumu" >Kisumu</option>
</select>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Location</div><div class="formdisplay">
<select type="select" id="location" class="selectclass">
<option value="" >---Select---</option>
<option value="Westlands" >Westlands</option>
<option value="Thika Road" >Thika Road</option>
<option value="Mombasa Road" >Mombasa Road</option>
<option value="Langata" >Langata</option>
<option value="Ngong Road" >Ngong Road</option>
<option value="Kilimani" >Kilimani</option>
<option value="Muthaiga" >Muthaiga</option>
<option value="Karen" >Karen</option>
<option value="Syokimau" >Syokimau</option>
<option value="Utawala" >Utawala</option>
</select>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Property Type</div><div class="formdisplay">
<select type="select" id="propertyType" class="selectclass">
<option value="" >---Select---</option>
<option value="To Let" >To Let</option>
<option value="For Sale" >For Sale</option>
</select>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Category</div><div class="formdisplay">
<select type="select" id="category" class="selectclass" onchange="showDiv()">
<option value="" >---Select---</option>
<option value="Residential" >Residential</option>
<option value="Commercial" >Commercial</option>
</select>
</div>
</div>

<div class="itemholder" id="bedroomholder">
<div class="formlabel">Bedrooms</div><div class="formdisplay"><input type="number" class="textnumber" id="bedrooms" value="0"></div>
</div>

</div>

<div id="error" class="error" onmouseout="requestList()"></div>
<div id="info" class="info" onmouseout="requestList()"></div>

<div class="update" id="create" onclick="saveRequest()">Create Request</div>

<div class="formholder2" id="listData">

<div class="formtitle">Requests</div>

<c:if test="${!empty alerts}">

<table id="alertHolder">
<tr class="listheader">
<th><div class="listclass7"></div></th>
<th><div class="listclass2">County</div></th>
<th><div class="listclass2">Location</div></th>
<th><div class="listclass2">Category</div></th>
<th><div class="listclass2">Property Type</div></th>
<th><div class="listclass5">Bedrooms</div></th>
<th><div class="listclass4">Delete</div></th>
</tr>

<c:forEach var="alert" items="${alerts}">
<tr class="listholder">

<div id ="dial" class="dialogue"></div>

<form id="requestForm" action="deleterequest.html" method="post" accept-charset=utf-8>
<th><div class="listclass3"><img src="led-icons/pin.png"></div></th>
<input type="hidden" name="id" id="id" value="${alert.id}">
<th><input type="text" class="listclass" id="county" name="county" value="${alert.county}"></th>
<th><input type="text" class="listclass" id="location" name="location" value="${alert.location}"></th>
<th><input type="text" class="listclass" id="category" name="category" value="${alert.category}"></th>
<th><input type="text" class="listclass" id="propertyType" name="propertyType" value="${alert.propertyType}"></th>
<th><input type="text" class="listclass3" id="bedrooms" name="bedrooms" value="${alert.bedrooms}"></th>
<th><div class="listclass4"><img src="led-icons/cross.png" onclick="reqDial()"></div></th>
</form>

</tr>
</c:forEach>
</table>

</c:if>

</div>

<div class="formfooter"></div>

</div>

</div>

</body>
<div class="myfooter2">
</html>