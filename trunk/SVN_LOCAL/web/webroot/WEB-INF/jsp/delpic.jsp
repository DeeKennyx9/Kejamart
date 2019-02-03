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

<script src="<%=request.getContextPath() %>/javascript/kdialogue.js"></script>
<script src="<%=request.getContextPath() %>/javascript/editproperty.js"></script>    
<script src="<%=request.getContextPath() %>/javascript/jquery.js"></script>
<script type="text/javascript">
var contexPath = "<%=request.getContextPath() %>";
</script>

<link rel="stylesheet" href="css/kejani.css">
  <link rel = "shortcut icon" href="img/logox.png"/>

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

<div class="formtitle">Delete Property</div>

<form method="post" enctype="multipart/form-data" accept-charset=utf-8>

<c:forEach var="prop" items="${propertyPics}"> 

<input type="hidden" id="id" value=${prop.id}>

<div class="itemholder">
<div class="formlabel">File Name</div><div class="formdisplay"><input type="file" name="fileUpload" class="textclass2" id="fileName" value="${prop.fileName}"></div>
</div>

</div>

<div id="error" class="error"></div>
<div id="info" class="info"></div>

</form>

</c:forEach>

<div class="formfooter"></div>

</div>

</div>

</body>
<div class="myfooter2">
</html>