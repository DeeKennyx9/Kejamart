<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page isELIgnored ="false" %>

<!DOCTYPE html>
<html>
<head>
<title>Find Quality Homes with Ease - kejamart</title>

<script type="text/javascript" src="javascript/menuz.js"></script> 
    
<!-- JQuery, Jackson, Spring Validation --> 
        
<script src="<%=request.getContextPath() %>/javascript/jquery.js"></script>
<script type="text/javascript">
var contexPath = "<%=request.getContextPath() %>";
</script>
<script src="<%=request.getContextPath() %>/javascript/propertypics.js"></script>
<script src="<%=request.getContextPath() %>/javascript/refreshPages.js"></script>
<script src="<%=request.getContextPath() %>/javascript/kdialogue.js"></script>
<script src="<%=request.getContextPath() %>/javascript/userInterface.js"></script>
<script src="<%=request.getContextPath() %>/javascript/deleteLinks.js"></script>

<script type="text/javascript">
   
    $(document).ready(function() {
		      
		$().ready(function() {
		    $().load(function(event) {
 
			$.ajax({
				url: contexPath + "/adverts.html",
   			  	type: "GET",
			  	
			  	success: function() {
			  	
			  	}
			});			
		    });
		});
});   
</script>

  <link rel = "shortcut icon" href="img/logox.png"/>

</head>

<link rel="stylesheet" href="css/kejani.css">

</head>
<body id ="main">

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

<div class="formtitle">Manage Adverts</div>

<form id ="advertForm" method="post" enctype="multipart/form-data" accept-charset=utf-8>

<div class="itemholder">
<div class="formlabel">Advert Upload</div><div class="formdisplay"><input type="file" name="file" class="textclass2" id="file"></div>
</div>

<div class="itemholder">
<div class="formlabel">Picture Orientation</div><div class="formdisplay">
<select type="select" id="orientation" name="orientation" class="selectclass">
<option value="" >--Select--</option>
<option value="style='height:200px;width:200px;-ms-transform:rotate(90deg);-webkit-transform:rotate(90deg); 
transform:rotate(90deg);'" >Vertical</option>
<option value="style='height:200px;width:200px;'" >Horizontal</option>
</select>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Page Location</div><div class="formdisplay">
<select type="select" id="pagelocation" name="pagelocation" class="selectclass">
<option value="" >--Select--</option>
<option value="TopCenter" >Top Center</option>
<option value="LeftPixel" >Left Pixel</option>
</select>
</div>
</div>

<div id="error" class="error"></div>
<div id="info" class="info"></div>

${errmg}

</form>

</div>

<div class="update" id="create" onclick="postAdverts()">Add Advert</div><a href="/kejani/allproperty.html"><div class="update" id="delete">Back to Manage Properties</div></a>

<div class="formholder2" id="listData">

<div class="formtitle">Adverts</div>

<c:if test="${!empty advertsByProperty}">
<c:forEach var="ad" items="${advertsByProperty}">

<div class="picholder"><img src="${ad.path}" ${ad.orientation} /></div>

</c:forEach>
</c:if>

</div>

<div class="formfooter"></div>

</div>

</div>

</body>
<div class="myfooter2">
</html>