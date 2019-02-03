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

<script>
$(document).ready(
        function() {

            $('#country').change(
            function() {
                
                $.getJSON('requests2.html', {
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
                
                $.getJSON('requests3.html', {
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

<div class="formholder">

<div class="formtitle">Manage Alerts</div>

<form id = "data" class="data">

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
<option value="" >--Select--</option>
<option value="To Let" >To Let</option>
<option value="For Sale" >For Sale</option>
</select>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Category</div><div class="formdisplay">
<select type="select" id="category" class="selectclass" onchange="showDiv()">
<option value="" >--Select--</option>
<option value="Residential" >Residential</option>
<option value="Commercial" >Commercial / For Business</option>
</select>
</div>
</div>

<div class="itemholder" id="bedroomholder">
<div class="formlabel">Bedrooms</div><div class="formdisplay"><input type="number" class="textnumber" id="bedrooms" value="0"></div>
</div>

<div class="itemholder">
<div class="formlabel">Range</div><div class="formdisplay">
<select type="select" id="prange" class="selectclass">
<option value="" >--Select--</option>
<c:forEach items="${rangeList}" var="rn">
<option value="${rn.id}">${rn.prange}</option>
</c:forEach>
</select>
</div>
</div>

</div>

</form>

<div class="update" id="create" onclick="saveRequest()">Create Request</div>

<div id="error" class="error" ></div>
<div id="info" class="info"></div>

<div class="formholder2" id="listData">

<div class="formtitle">Alerts</div>

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
<th><input type="text" class="listclass" id="county" name="county" value="${alert.countyVal}"></th>
<th><input type="text" class="listclass" id="locationVal" name="locationVal" value="${alert.locationVal}"></th>
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