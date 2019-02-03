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
<script src="<%=request.getContextPath() %>/javascript/refreshPages.js"></script>
<script src="<%=request.getContextPath() %>/javascript/kdialogue.js"></script>
<script src="<%=request.getContextPath() %>/javascript/userInterface.js"></script>
<script src="<%=request.getContextPath() %>/javascript/deleteLinks.js"></script>

<script type="text/javascript">
   
    $(document).ready(function() {
		      
		$().ready(function() {
		    $().load(function(event) {
 
			$.ajax({
				url: contexPath + "/propertymain.html",
   			  	type: "GET",
			  	
			  	success: function() {
			  	
			  	}
			});			
		    });
		});
});   
</script>

<link rel="stylesheet" href="css/kejani.css">
  <link rel = "shortcut icon" href="img/logox.png"/>
  
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

<div class="formtitle">Add Main Picture</div>

<form id ="mainPickForm" method="post" enctype="multipart/form-data" accept-charset=utf-8>

<div class="itemholder">
<div class="formlabel">Picture Upload</div><div class="formdisplay"><input type="file" name="file" class="textclass2" id="file" onclick="clearResults()"></div>
</div>

</form>

</div>

<div id="error" class="error"> ${errmg} </div>
<div id="info" class="info"> ${infomg} </div>

<div class="update" id="create" onclick="postMainPic()">Add Picture</div><a href="/kejani/allproperty.html"><div class="update" id="delete">Back to Manage Properties</div></a>

<div class="formholder2" id="listData">

<div class="formtitle">Main Picture</div>

<c:if test="${!empty picsByProperty}">
<c:forEach var="pc" items="${picsByProperty}">

<div class="picholderx"><img src="${pc.path}" ${pc.orientation} /></div>

</c:forEach>
</c:if>

</div>

<div class="formfooter"></div>

</div>

</div>

</body>
<div class="myfooter2">
</html>