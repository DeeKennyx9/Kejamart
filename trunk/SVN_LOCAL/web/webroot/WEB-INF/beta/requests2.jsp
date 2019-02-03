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
<script src="<%=request.getContextPath() %>/javascript/deleteRequest.js"></script>
<script src="<%=request.getContextPath() %>/javascript/userInterface.js"></script>
<script src="<%=request.getContextPath() %>/javascript/deleteLinks.js"></script>

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
<div class="buttons">${adv}</div>
<div class="buttons">${com}</div>

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
<div id ="dcountr" class="dialogue"></div>

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
<div id ="dloc" class="dialogue"></div>

<div class="itemholder">
<div class="formlabel">Property Type</div><div class="formdisplay">
<select type="select" id="propertyType" class="selectclass">
<option value="" >---Select---</option>
<option value="To Let" >To Let</option>
<option value="For Sale" >For Sale</option>
</select>
</div>
</div>
<div id ="dpropt" class="dialogue"></div>

<div class="itemholder">
<div class="formlabel">Category</div><div class="formdisplay">
<select type="select" id="category" class="selectclass" onchange="showDiv()">
<option value="" >---Select---</option>
<option value="Residential" >Residential</option>
<option value="Commercial" >Commercial</option>
</select>
</div>
</div>
<div id ="dcat" class="dialogue"></div>

<div class="itemholder" id="bedroomholder">
<div class="formlabel">Bedrooms</div><div class="formdisplay"><input type="number" class="textnumber" id="bedrooms" value="0"></div>
</div>
<div id ="dbed" class="dialogue"></div>

</div>

<div id="error" class="error"></div>
<div id="info" class="info"></div>

<div class="update" id="create" onclick="saveRequest()">Create Request</div>

<div class="formholder2" id="listData">

<div class="formtitle">Requests</div>

<table>
<tr class="listheader">
<th><div class="listclass3"></div></th>
<th><div class="listclass2">County</div></th>
<th><div class="listclass2">Location</div></th>
<th><div class="listclass2">Category</div></th>
<th><div class="listclass2">Property Type</div></th>
<th><div class="listclass5">Bedrooms</div></th>
<th><div class="listclass4">Delete</div></th>
</tr>
<c:forEach var="alert" items="${alerts}">
<tr class="listholder">
<th><div class="listclass3"><img src="led-icons/flag_blue.png"><a class="hidden" id="idz">${alert.id}</a></div></th>
<th><div class="listclass" id="cty">${alert.county}</div></th>
<th><div class="listclass" id="loc">${alert.location}</div></th>
<th><div class="listclass" id="cat">${alert.category}</div></th>
<th><div class="listclass" id="pro">${alert.propertyType}</div></th>
<th><div class="listclass3" id="bed">${alert.bedrooms}</div></th>
<th><div class="listclass4"><img src="led-icons/cross.png" onclick="request();"></div></th>
</tr>
</c:forEach>
</table>

</div>


<div class="formfooter"></div>


</div>

</div>

</body>
<div class="myfooter2">
</html>