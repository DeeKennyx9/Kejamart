<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<script src="<%=request.getContextPath() %>/javascript/profile.js"></script>

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
<div class="buttons"><li><div class="eventimg"><img src ="led-icons/user_silhouette.png"></div>Profile</li></div>
<div class="buttons"><li><div class="eventimg"><img src ="led-icons/exclamation.png"></div>Requests</li></div>
<div class="buttons"><li><div class="eventimg"><img src ="led-icons/inbox.png"></div>Notifications</li></div>
<div class="buttons"><li><div class="eventimg"><img src ="led-icons/house.png"></div>Manage Property</div>
<div class="buttons"><li><div class="eventimg"><img src ="led-icons/chart_bar.png"></div>Dashboard</li></div>
<div class="buttons"><li><div class="eventimg"><img src ="led-icons/help.png"></div>Help</li></div>

</ul>
</div>

<div class="mycontainer">

<div class="myrightpanel">
</div>

<c:forEach var="profile" items="${profiles}"> 

<div class="formholder">

<div class="formtitle">Manage Profile</div>

<div class="itemholder">
<div class="formlabel">First Name</div><div class="formdisplay"><input type="text" class="textclass" id="firstName" value ="${profile.firstName}"></div>
</div>
<div id ="dfname" class="dialogue"></div>
<div class="itemholder">
<div class="formlabel">Last Name</div><div class="formdisplay"><input type="text" class="textclass" id="lastName" value ="${profile.lastName}"></div>
</div>
<div id ="dlname" class="dialogue"></div>
<div class="itemholder">
<div class="formlabel">Password</div><div class="formdisplay"><input type="text" class="textclass" id="password" value ="${profile.password}"></div>
</div>
<div id ="dpass" class="dialogue"></div>
<div class="itemholder">
<div class="formlabel">Category</div><div class="formdisplay">
<select type="select" id="category" class="selectclass">
<option>${profile.category}</option>
<option value="Personal" >Personal</option>
<option value="Merchant" >Merchant</option>
</select>
</div>
</div>
<div id ="dcat" class="dialogue"></div>
<div class="itemholder">
<div class="formlabel">Mailing</div><div class="formdisplay">
<input type="checkbox" id="mailing" value="Mailing" class="textboxclass"/></div>
</div>
<div id ="dmailing" class="dialogue"></div>
<div class="itemholder">
<div class="formlabel">Email</div><div class="formdisplay"><input type="text" class="textclass2" id="email" value ="${profile.email}"> 
<img src ="led-icons/exclamation_octagon_fram.png">
Your email is linked to all your transactions in this portal. </div> 
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