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

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="css/kejanix.css">

    <!-- Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="css/animate.css" rel="stylesheet" />
    <!-- Squad theme CSS -->
	<link href="css/login.css" rel="stylesheet">
	<link href="color/default.css" rel="stylesheet">
	<link href="css/app.css" rel="stylesheet">
	<link rel="stylesheet" href="css/kejanix.css">
	<link rel="stylesheet" href="css/style.css">
  <link rel = "shortcut icon" href="img/logox.png"/>	

</head>
<body id="page-top" data-spy="scroll" data-target=".navbar-custom">

        <nav class="navbarx navbar-customx" role="navigation">
                              <div class="container">
                                    <div class="row">
                                          <div class="col-md-2">
                                                   <div class="site-logo">
                                                            <a href="search.html" class="navbar-brand"></a>
                                                    </div>
                                          </div>
                                          

                                          <div class="col-md-10">
                         
                                                      <!-- Brand and toggle get grouped for better mobile display -->
                                          <div class="navbar-header">
                                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menu">
                                                <i class="fa fa-bars"></i>
                                                </button>
                                          </div>
                                                      <!-- Collect the nav links, forms, and other content for toggling -->
                                                      <div class="collapse navbar-collapse" id="menu">
                                                            <ul class="nav navbar-nav navbar-right">
                                                                                                  
                                                                                                  <li>${sea}</li>                                                            
                                                                                                  <li>${prof}</li>
								                                  <li>${alert}</li>
								                                  <li>${notif}</li>
								                                  <li>${addp}</li>
								                                  <li>${dash}</li>
								                                  <li>${help2}</li>
								                                  <li>${musers}</li>
								                                  <li>${propmg}</li>
								                                  <li>${addpp}</li>
								                                  <li>${editpp}</li>
                                                                  <li>${advert}</li>
                                                                  <c:if test="${empty userSession}"> 
								  </c:if>
								  <c:if test="${!empty userSession}">
								  <li>${logoutLink}</li>
								  <li class="user">${userSession}</li>	
								  </c:if>            
                                                         </div>
                                                            </ul>
                                                      </div>
                                                      
                                                      <!-- /.Navbar-collapse -->
                             
                                          </div>
                                    </div>
                              </div>
                              <!-- /.container -->
                        </nav>
                        
<section id="contact" class="home-section nopadd-bot color-dark bg-white text-center">                        

<h6 class="h-bold">Notifications</h6>

<div class="formholderm">
<c:if test="${!empty messages}">

<table id="alertHolder" class="alertHolder">
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
</tr>

<tr class="mailbody" id="mailbody" style="display:none">
<th>${msg.message}</th>
</tr>

</c:forEach>
</table>
</c:if>

</div>
</section>

<p><br>



    <!-- Core JavaScript Files -->
    
    <script src="jsmain/jquery.min.js"></script>
    <script src="jsmain/bootstrap.min.js"></script>
    <script src="jsmain/jquery.easing.min.js"></script>
	<script src="jsmain/jquery.scrollTo.js"></script>
	<script src="jsmain/wow.min.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="jsmain/custom.js"></script>   


</body>

</html>

