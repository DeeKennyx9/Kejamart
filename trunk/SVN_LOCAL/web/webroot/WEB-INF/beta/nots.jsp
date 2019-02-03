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
				url: contexPath + "/notifications.html",
   			  	type: "GET",
			  	
			  	success: function() {
			  	
			  	}
			});			
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
<div class="buttons">${notif} <a class="notif">${mtotals}</a></div>
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

<div class="formholder2" id="listData">

<div class="formtitle">Notifications</div>

<c:if test="${!empty messages}">

<table id="alertHolder">
<tr class="listheader">
<th><div class="mailclass7"></div></th>
<th><div class="mailclass2">From</div></th>
<th><div class="mailclass2">Date Received</div></th>
<th><div class="mailclass2">Open Message</div></th>
<th><div class="mailclass2">Delete</div></th>
</tr>

<c:forEach var="msg" items="${messages}">
<tr class="mailholder">

<div id ="dial" class="dialogue"></div>

<form id="messageForm" accept-charset=utf-8>
<th><div class="mailclass3"><img src="led-icons/email.png"></div></th>
<input type="hidden" name="id" id="id" value="${msg.id}">
<th><input type="text" class="mailclass" id="sender" name="sender" value="${msg.sender}"></th>
<th><input type="text" class="mailclass" id="createdDate" name="createdDate" value="${msg.createdDate}"></th>
<th><div class="mailclass"><img src="led-icons/email_open.png" onclick="mailFunction()"></div></th>
</form>

<form id="messageFormDel" action="notifications.html" method="post" accept-charset=utf-8>
<input type="hidden" name="id" id="id" value="${msg.id}">
<th><div class="mailclass"><img src="led-icons/delete.png" onclick="deleteMessages()"></div></th>
</form>
</div>
</tr>

<tr class="mailbody" id="mailbody" style="display:none">
<th>${msg.message}</th>
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