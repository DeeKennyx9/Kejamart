<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page isELIgnored ="false" %>

<!DOCTYPE html>
<html>
<head>
<title>Find Your Preferred Properties, Create Alerts and Get Notifications - Kejani</title>

<script type="text/javascript" src="javascript/menuz.js"></script> 
    
<!-- JQuery, Jackson, Spring Validation --> 
        
<script src="<%=request.getContextPath() %>/javascript/jquery.js"></script>
<script type="text/javascript">
var contexPath = "<%=request.getContextPath() %>";
</script>
<script src="<%=request.getContextPath() %>/javascript/kejanitabs.js"></script>
<script src="<%=request.getContextPath() %>/javascript/property.js"></script>
<script src="<%=request.getContextPath() %>/javascript/refreshPages.js"></script>
<script src="<%=request.getContextPath() %>/javascript/deleteProperty.js"></script>
<script src="<%=request.getContextPath() %>/javascript/kdialogue.js"></script>
<script src="<%=request.getContextPath() %>/javascript/userInterface.js"></script>
<script src="<%=request.getContextPath() %>/javascript/deleteLinks.js"></script>

<script type="text/javascript">
   
    $(document).ready(function() {
		      
		$().ready(function() {
		    $().load(function(event) {
 
			$.ajax({
				url: contexPath + "/property.html",
   			  	type: "GET",
			  	
			  	success: function() {
			  	
			  	}
			});			
		    });
		});
});   
</script>

<script>
$(document).ready(
        function() {

            $('#country').change(
            function() {
                
                $.getJSON('property2.html', {
                    country: $(this).val(),
                    ajax : 'true'
                }, function(data) {
                    
                    var listInfo = '<option value="">--Select--</option>';
                    var len = data.length;
      
                    for ( var i = 0; i < len; i++) {
                    	listInfo += '<option value="' + data[i].id + '">' + data[i].county + '</option>';

                    }
                    listInfo += '</option>';
   
                    $('#county').html(listInfo);

                });  
            });
            
        });	        
</script>

<script>
$(document).ready(
        function() {

            $('#county').change(
            function() {
                
                $.getJSON('property3.html', {
                    county: $(this).val(),
                    ajax : 'true'
                }, function(data) {
                    
                    var listInfo = '<option value="">--Select--</option>';
                    var len = data.length;
      
                    for ( var i = 0; i < len; i++) {
                    	listInfo += '<option value="' + data[i].id + '">' + data[i].location + '</option>';

                    }
                    listInfo += '</option>';
   
                    $('#location').html(listInfo);

                });  
            });
            
        });	        
</script>

<link rel="stylesheet" href="css/kejani.css">
  <link rel = "shortcut icon" href="img/logoz.jpg"/>

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

<div class="kejanitab" onclick="myFunction()">Show / Hide Property Form</div>

<div class="formholder" id="formholder" style="display:none">

<div class="formtitle">Manage Property</div>

<div class="itemholder">
<div class="formlabel">Property Name</div><div class="formdisplay"><input type="text" class="textclassa" id="name"></div>
</div>

<div class="itemholder">
<div class="formlabel">Contacts</div><div class="formdisplay"><input type="text" class="textclassa" id="contacts"></div>
</div>

<div class="itemholder">
<div class="formlabel">Website</div><div class="formdisplay"><input type="text" class="textclassa" id="website"></div>
</div>

<div class="itemholder">
<div class="formlabel">Amount</div><div class="formdisplay"><input type="text" class="textnumber" id="amount" value="0"></div>
</div>

<div class="itemholder2">
<div class="formlabel">Property Description</div>
<div class="formdisplay"><textarea class="textclassi" id="description"></textarea></div>
</div>

<div class="itemholder">
<div class="formlabel">Country</div><div class="formdisplay">
<select type="select" id="country" name="country" class="selectclass">
<option value="" >--Select--</option>
<c:forEach items="${countryList}" var="ct">
<option value="${ct.id}">${ct.country}</option>
</c:forEach>
</select>
</div>
</div>

<div class="itemholder">
<div class="formlabel">County</div><div class="formdisplay">
<select type="select" id="county" name="county" class="selectclass">
<option value="">--Select--</option>
</select>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Location</div><div class="formdisplay">
<select type="select" id="location" name="location" class="selectclass">
<option value="">--Select--</option>
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

<div class="itemholder">
<div class="formlabel">Range</div><div class="formdisplay">
<select type="select" id="prange" class="selectclass">
<option value="6" >--Select--</option>
<c:forEach items="${rangeList}" var="rn">
<option value="${rn.id}">${rn.prange}</option>
</c:forEach>
</select>
</div>
</div>

<div class="update" id="create" onclick="saveProperty()">Create Property</div>

</div>

<div id="error" class="error"></div>
<div id="info" class="info"></div>

<div class="formholder2" id="listData">

<div class="formtitle">Properties</div>

<c:if test="${!empty property}">

<table id="alertHolder">
<tr class="listheader">
<th><div class="listclass7"></div></th>
<th><div class="listclass2">Name</div></th>
<th><div class="listclass2">County</div></th>
<th><div class="listclass2">Location</div></th>
<th><div class="listclass2">Category</div></th>
<th><div class="listclass2">Edit</div></th>
<th><div class="listclass2">Delete</div></th>
</tr>

<c:forEach var="prop" items="${property}">
<tr class="listholder">

<div id ="dial" class="dialogue"></div>

<form id="propertyForm" action="editproperty.html" method="get" accept-charset=utf-8>
<th><div class="listclass3"><img src="led-icons/pin.png"></div></th>
<input type="hidden" name="id" id="id" value="${prop.id}">
<th><input type="text" class="listclass" id="name" name="name" value="${prop.name}"></th>
<th><input type="text" class="listclass" id="county" name="county" value="${prop.countyVal}"></th>
<th><input type="text" class="listclass" id="location" name="location" value="${prop.locationVal}"></th>
<th><input type="text" class="listclass" id="category" name="category" value="${prop.category}"></th>
<th><div class="listclass"><input type="image" name="submit" src="led-icons/pencil.png"></th>
</form>

<form id="propertyFormDel" action="deleteproperty.html" method="post" accept-charset=utf-8>
<input type="hidden" name="id" id="id" value="${prop.id}">
<th><img src="led-icons/cross.png" onclick="propDialDel()"></th>
</form>
</div>

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