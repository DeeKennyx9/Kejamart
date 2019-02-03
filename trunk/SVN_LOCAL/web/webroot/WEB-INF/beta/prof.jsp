<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<script src="<%=request.getContextPath() %>/javascript/editprofile.js"></script>

<link rel="stylesheet" href="css/kejani.css">
  <link rel = "shortcut icon" href="img/logoz.jpg"/>

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

<div class="formtitle">Manage Profile</div>

<c:forEach var="profile" items="${profiles}"> 

<div class="itemholder">
<div class="formlabel">First Name</div><div class="formdisplay"><input type="text" class="textclass" id="firstName" value ="${profile.firstName}"></div>
</div>
<div id ="dfname" class="dialogue"></div>
<div class="itemholder">
<div class="formlabel">Last Name</div><div class="formdisplay"><input type="text" class="textclass" id="lastName" value ="${profile.lastName}"></div>
</div>
<div class="itemholder">
<div class="formlabel">Mobile</div><div class="formdisplay"><input type="text" class="textclass" id="mobile" value ="${profile.mobile}"></div>
</div>
<div id ="dpass" class="dialogue"></div>
<div class="itemholder">
<div class="formlabel">Mailing</div><div class="formdisplay">
<select type="select" id="mailing" class="selectclass">
<option>${profile.mailing}</option>
<option value="subscribe" >Subscribe</option>
<option value="unsubscribe" >Unsubscribe</option>
</select>
</div>
</div>
<div id ="dmailing" class="dialogue"></div>
<div class="itemholder">
<div class="formlabel">Email</div><div class="formdisplay"><input type="text" class="textclass" id="email" value ="${profile.email}"> 
<img src ="led-icons/exclamation_octagon_fram.png"> &nbsp;
Your email is linked to all your transactions on this portal. </div> 
</div>
<div id ="demail" class="dialogue"></div>

</c:forEach>

</div>

<div id="error" class="error"></div>
<div id="info" class="info"></div>

<div class="update" id="update" onclick="doAjaxPost()">Update Changes</div>

<div class="formfooter"></div>


</div>

</div>

</body>
<div class="myfooter2">
</html>