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
        
<script src="<%=request.getContextPath() %>/javascript/jquery.js"></script>
<script type="text/javascript">
var contexPath = "<%=request.getContextPath() %>";
</script>
<script src="<%=request.getContextPath() %>/javascript/deleteRequest.js"></script>

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

<div class="formtitle">Delete Requests</div>

<form accept-charset=utf-8>

<c:forEach var="alert" items="${alerts}"> 

<div class="itemholder">
<div class="formlabel"></div><div class="formdisplay"><input type="checkbox" class="textclass" id="checkBoxAll" value ="${alert.id}"></div>
</div>

<div class="itemholder">
<div class="formlabel">County</div><div class="formdisplay"><input type="text" class="textclass" id="county" value ="${alert.county}"></div>
</div>

<div class="itemholder">
<div class="formlabel">Location</div><div class="formdisplay"><input type="text" class="textclass" id="location" value ="${alert.location}"></div>
</div>

<div class="itemholder">
<div class="formlabel">Category</div><div class="formdisplay"><input type="text" class="textclass" id="category" value ="${alert.category}"></div>
</div>

<div class="itemholder">
<div class="formlabel">Property Type</div><div class="formdisplay"><input type="text" class="textclass" id="propertyType" value ="${alert.propertyType}"></div>
</div>

<div class="itemholder">
<div class="formlabel">Bedrooms</div><div class="formdisplay"><input type="text" class="textclass" id="bedrooms" value ="${alert.bedrooms}"></div>
</div>

<div class="update" id="update" onclick="deleteAlert();">Delete Alert</div>

</form>

</c:forEach>

<div id="error" class="error"></div>
<div id="info" class="info"></div>

<div class="formfooter"></div>

</div>

</div>

</body>
<div class="myfooter2">
</html>