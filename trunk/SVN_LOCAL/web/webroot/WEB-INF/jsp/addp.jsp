<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<script src="<%=request.getContextPath() %>/javascript/profile.js"></script>

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

<div class="formtitle">Add Profile</div>

<div class="itemholder">
<div class="formlabel">First Name</div><div class="formdisplay"><input type="text" class="textclass" id="firstName" ></div>
</div>
<div id ="dfname" class="dialogue"></div>
<div class="itemholder">
<div class="formlabel">Last Name</div><div class="formdisplay"><input type="text" class="textclass" id="lastName" ></div>
</div>
<div id ="dlname" class="dialogue"></div>
<div class="itemholder">
<div class="formlabel">Password</div><div class="formdisplay"><input type="text" class="textclass" id="password"></div>
</div>
<div id ="dpass" class="dialogue"></div>
<div class="itemholder">
<div class="formlabel">Category</div><div class="formdisplay">
<select type="select" id="category" class="selectclass">
<option>--Select--</option>
<option value="Personal" >Personal</option>
<option value="Merchant" >Merchant</option>
</select>
</div>
<div class="itemholder">
<div class="formlabel">Role</div><div class="formdisplay">
<select type="select" id="role" class="selectclass">
<option>--Select--</option>
<option value="USER" >USER</option>
<option value="MANAGER" >MANAGER</option>
<option value="ADMIN" >ADMIN</option>
</select>
</div>
</div>
<div id ="dcat" class="dialogue"></div>
<div class="itemholder">
<div class="formlabel">Subscribe</div><div class="formdisplay">
<input type="checkbox" id="mailing" class="textboxclass"/></div>
</div>
<div id ="dmailing" class="dialogue"></div>
<div class="itemholder">
<div class="formlabel">Email</div><div class="formdisplay"><input type="text" class="textclass2" id="email"> 
<img src ="led-icons/exclamation_octagon_fram.png">
Your email is linked to all your transactions on this portal. </div> 
</div>
<div id ="demail" class="dialogue"></div>

</div>

<div id="error" class="error"></div>
<div id="info" class="info"></div>

<div class="update" id="update" onclick="doAjaxPost()">Add User</div>

<div class="formfooter"></div>


</div>

</div>

</body>
<div class="myfooter2">
</html>