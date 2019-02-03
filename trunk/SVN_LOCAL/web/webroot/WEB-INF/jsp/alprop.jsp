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
<script src="<%=request.getContextPath() %>/javascript/searchProperty.js"></script>
<script src="<%=request.getContextPath() %>/javascript/property.js"></script>
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
				url: contexPath + "/property.html",
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
<body>

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

<div class="formdisplay">
<form id="searchForm" action="allproperty.html" method="POST" accept-charset=utf-8>
  Enter Property Name to Search <p><br>
<input type="text" class="textclassa" id="name" name="name" onkeyup="">&nbsp; 
<img src="led-icons/magnifier.png" id="mybutton" onclick="search()">
</form>
</div>

<div class="resulttext" id="resulttext"></div>

<div class="resultx2" id="resultx">
<table class="searchdisplay" id="searchdisplay">
<tr class="listheader2">
<th><div class="listclass2">Upload Pictures</div></th>
<th><div class="listclass2">Edit Property</div></th>
<th><div class="listclass2">Welcome Picture</div></th>
<th><div class="listclass2">Upload Resources</div></th>
<th><div class="listclass2">Add Features</div></th>
<th><div class="listclass2">Add Logo</div></th>
<th><div class="listclass2">Upload Adverts</div></th>
<th><div class="listclass2">Delete</div></th>
</tr>
<tr class="listholder">

<form id="propertyForm" action="propertypics.html" method="get" accept-charset=utf-8>
<input type="hidden" name="id" id="idx" value="${prop.id}">
<input type="hidden" id="bedrooms" name="bedroomsx" value="${prop.bedrooms}">
<th><div class="listclass"><input type="image" name="submit" src="led-icons/camera.png"></th>
</form>

<form id="propertyForm" action="editproperty.html" method="get" accept-charset=utf-8>
<input type="hidden" name="id" id="ida" value="${prop.id}">
<input type="hidden" class="listclass" id="name" name="name" value="${prop.name}">
<th><div class="listclass"><input type="image" name="submit" src="led-icons/pencil.png"></th>
</form>

<form id="welcomePicForm" action="propertymain.html" method="get" accept-charset=utf-8>
<input type="hidden" name="id" id="idb" value="${prop.id}">
<th><input type="hidden" id="name" name="name" value="${prop.name}"></th>
<th><div class="listclass2"><input type="image" name="submit" src="led-icons/television.png"></th>
</form>

<form id="propertyForm" action="resources.html" method="get" accept-charset=utf-8>
<input type="hidden" name="id" id="idc" value="${prop.id}">
<th><input type="hidden" id="name" name="name" value="${prop.name}"></th>
<th><input type="hidden" id="bedrooms" name="bedrooms" value="${prop.bedrooms}"></th>
<th><div class="listclass2"><input type="image" name="submit" src="led-icons/attach_2.png"></th>
</form>

<form id="propertyForm" action="features.html" method="get" accept-charset=utf-8>
<input type="hidden" name="id" id="idd" value="${prop.id}">
<th><input type="hidden" id="name" name="name" value="${prop.name}"></th>
<th><div class="listclass2"><input type="image" name="submit" src="led-icons/asterisk_orange.png"></th>
</form>

<form id="logoForm" action="propertylogo.html" method="get" accept-charset=utf-8>
<input type="hidden" name="id" id="ide" value="${prop.id}">
<th><input type="hidden" id="name" name="name" value="${prop.name}"></th>
<th><div class="listclass2"><input type="image" name="submit" src="led-icons/star_1.png"></th>
</form>

<form id="advertForm" action="adverts.html" method="get" accept-charset=utf-8>
<input type="hidden" name="id" id="idf" value="${prop.id}">
<input type="hidden" name="name" id="name" value="${prop.name}">
<th><div class="listclass2"><input type="image" name="submit" src="led-icons/rocket.png"></th>
</form>

<form id="propertyFormDel" action="deleteproperty.html" method="post" accept-charset=utf-8>
<input type="hidden" name="id" id="idg" value="${prop.id}">
<input type="hidden" class="listclass" id="name" name="name" value="${prop.name}">
<th><div class="listclass5"><img src="led-icons/cross.png" onclick="propDialDel()"></div></th>
</form>

</tr>
</table>
</div>

<div class="propertyholder" id="listData">
<div class="formtitle">Manage All Property</div>

<c:if test="${!empty property}">

<table id="alertHolder">
<tr class="listheader">
<th><div class="listclass7"></div></th>
<th><div class="listclass2">Name</div></th>
<th><div class="listclass2">Location</div></th>
<th><div class="listclass2">Upload Pictures</div></th>
<th><div class="listclass2">Edit Property</div></th>
<th><div class="listclass2">Welcome Picture</div></th>
<th><div class="listclass2">Upload Resources</div></th>
<th><div class="listclass2">Add Features</div></th>
<th><div class="listclass2">Add Logo</div></th>
<th><div class="listclass2">Upload Adverts</div></th>
<th><div class="listclass2">Delete</div></th>
</tr>

<c:forEach var="prop" items="${property}">
<tr class="listholder">

<div id ="dial" class="dialogue"></div>

<form id="propertyForm" action="propertypics.html" method="get" accept-charset=utf-8>
<th><div class="listclass3"><img src="led-icons/pin.png"></div></th>
<input type="hidden" name="id" id="id" value="${prop.id}">
<input type="hidden" id="bedrooms" name="bedrooms" value="${prop.bedrooms}">
<th><input type="text" class="listclass" id="name" name="name" value="${prop.name}"></th>
<th><input type="text" class="listclass" id="location" name="location" value="${prop.locationVal}"></th>
<th><div class="listclass"><input type="image" name="submit" src="led-icons/camera.png"></th>
</form>

<form id="propertyForm" action="editproperty.html" method="get" accept-charset=utf-8>
<input type="hidden" name="id" id="id" value="${prop.id}">
<th><div class="listclass"><input type="image" name="submit" src="led-icons/pencil.png"></th>
</form>

<form id="welcomePicForm" action="propertymain.html" method="get" accept-charset=utf-8>
<input type="hidden" name="id" id="id" value="${prop.id}">
<th><input type="hidden" id="name" name="name" value="${prop.name}"></th>
<th><div class="listclass2"><input type="image" name="submit" src="led-icons/television.png"></th>
</form>

<form id="propertyForm" action="resources.html" method="get" accept-charset=utf-8>
<input type="hidden" name="id" id="id" value="${prop.id}">
<th><input type="hidden" id="name" name="name" value="${prop.name}"></th>
<th><input type="hidden" id="bedrooms" name="bedrooms" value="${prop.bedrooms}"></th>
<th><div class="listclass2"><input type="image" name="submit" src="led-icons/attach_2.png"></th>
</form>

<form id="propertyForm" action="features.html" method="get" accept-charset=utf-8>
<input type="hidden" name="id" id="id" value="${prop.id}">
<th><input type="hidden" id="name" name="name" value="${prop.name}"></th>
<th><div class="listclass2"><input type="image" name="submit" src="led-icons/asterisk_orange.png"></th>
</form>

<form id="logoForm" action="propertylogo.html" method="get" accept-charset=utf-8>
<input type="hidden" name="id" id="id" value="${prop.id}">
<th><input type="hidden" id="name" name="name" value="${prop.name}"></th>
<th><div class="listclass2"><input type="image" name="submit" src="led-icons/ruby.png"></th>
</form>

<form id="advertForm" action="adverts.html" method="get" accept-charset=utf-8>
<input type="hidden" name="id" id="id" value="${prop.id}">
<input type="hidden" name="name" id="name" value="${prop.name}">
<th><div class="listclass2"><input type="image" name="submit" src="led-icons/rocket.png"></th>
</form>

<form id="propertyFormDel" action="deleteproperty.html" method="post" accept-charset=utf-8>
<input type="hidden" name="id" id="id" value="${prop.id}">
<th><div class="listclass5"><img src="led-icons/cross.png" onclick="propDialDel()"></div></th>
</form>

</tr>
</c:forEach>
</table>

</c:if>

</div>
</section>


	<footer>
		<div class="container">
		
		<div style="visibility: visible; animation-name: zoomIn;" class="col-sm-12 text-center wow zoomIn">
			<h3 class="footer h3">Follow us on</h3><br>
			<div class="footer_social">
				<ul>
					<li><a class="f_facebook" href="https://twitter.com/KejaniInfo"><i class="fa fa-facebook"></i></a></li>
					<li><a class="f_twitter" href="https://twitter.com/KejaniInfo"><i class="fa fa-twitter"></i></a></li>
					<li><a class="f_instagram" href="https://www.instagram.com/kejanilimited/"><i class="fa fa-instagram"></i></a></li>
				</ul>
			</div>
                </div><!--- END COL -->
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
					
					<div class="text-center">
						<a href="#intro" class="totop"><i class="fa fa-angle-up fa-3x"></i></a>
						<p>Copyright © 2017 kejamart. All rights reserved.</p>

					</div>
				</div>
			</div>	
		</div>
	</footer>


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