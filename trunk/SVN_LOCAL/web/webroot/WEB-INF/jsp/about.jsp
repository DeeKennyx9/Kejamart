<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored ="false" %>

<html lang="en" class=" js no-touch">
<head>
  <title>Find Quality Homes with Ease - kejamart</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <script src="<%=request.getContextPath() %>/javascript/kdialogue.js"></script>
  
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-106182339-1', 'auto');
  ga('send', 'pageview');

</script>  
  
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
  <link href='https://fonts.googleapis.com/css?family=Open+Sans:300,600|Raleway:600,300|Josefin+Slab:400,700,600italic,600,400italic' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" type="text/css" href="css/slick-team-slider.css"/>
  <link rel="stylesheet" type="text/css" href="css/style2.css">
  <link rel = "shortcut icon" href="img/logov.png"/>
  
</head>
<body>

        <% 
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setHeader("Expires", "0"); // Proxies
        %>

	
	<!--HEADER START-->
	<div class="main-navigation navbar-fixed-top">
		<nav class="navbar navbar-default">
			<div class="container">
			<div class="navbar-header">
			  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
			    <span class="icon-bar"></span>
			    <span class="icon-bar"></span>
			    <span class="icon-bar"></span>
			  </button>
			  <div class="site-logo">
			  <a class="navbar-brand" href="search.html">kejamart</a>
			  </div>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
			  <ul class="nav navbar-nav navbar-right">
			    <li><a href="search.html">HOME</a></li>
			    <li><a href="about.html">ABOUT</a></li>			    
			    <li><a href="contact.html">CONTACT</a></li>
			    <c:if test="${empty userSession}"> 
	                    <li><a href="login.html" class="btn btn-bannery">Log In</a></li>
	                    <li><a href="register.html" class="btn btn-bannerx">Sign Up</a></li>
			    </c:if>
			    <c:if test="${!empty userSession}">
			    <li>${logoutLink}</li>
			    <li>${profileLink}</li>
			    <li class="user2">${userSession}</li>	
                            </c:if>
			  </ul>
			</div>
		  </div>
		</nav>
	</div>
	<!--HEADER END-->

	<!--BANNER START-->
	<div id="banner" class="section-padding">
		<div class="container">
			<div class="row">
				<div class="jumbotron">
				 <h1 class="small">Welcome To <span class="bold">kejamart</span></h1>
				 <p class="big">Your Property Listing Partner.</p>
				 <form id="search" action="search.html" method="get" accept-charset=utf-8>
				 <p class="search"><img src="img/search.png" onclick="goSearch()"/><p>
				 </form>
				</div>
			</div>
		</div>
	</div>
	<!--BANNER END-->

	<!--SERVICE START-->
	<div id="service" class="section-padding">

        <div class="narrative2"><i class="glyphicon glyphicon-search fa-5x"></i></div>
        <div class="narrative2">
        <div class="narheading">Search easily for your desired properties</div>
        Select the options provided on the search page and search for properties across locations in different counties.
	<br>Try out our simple search engine.
        </div>
        
        <div class="narrative2"><i class="fa fa-tablet fa-5x"></i></div>
        <div class="narrative2">
        <div class="narheading">Access our website on different platforms</div>
        Browse easily on different platforms, from your tablet to your smartphone.<p>Our site is user friendly across devices for easier access.
	<br>Register and login to manage your user profile.
        </div>
        
        <div class="narrative2"><i class="fa fa-exclamation-circle fa-5x"></i></div>
        <div class="narrative2"></div>
        <div class="narrative2">
        <div class="narheading">Create alerts and recieve notifications</div>
        Recieve notifications on select properties by creating alerts based on your required criteria.<br>If you dont find your preferred property, 
        your preferred property will find you.
        </div>
        
        <div class="narrative2"><i class="glyphicon glyphicon-camera fa-5x"></i></div>
        <div class="narrative2">
        <div class="narheading">To our clients, we offer quality photography at no extra cost</div>
        Our clients get the best service. <br>Enjoy our proffessional photography services at no extra cost and enhance the look and feel of your property.
        <br>Show off your best qualities.
        </div>
        
        <div class="narrative2"><i class="fa fa-home fa-5x"></i></div>
        <div class="narrative2">
        <div class="narheading">We only showcase the best in the market</div>
        Our quality assurance process ensures that all property listed on our site have qualities that meet your expectations.
	<br>We focus on the outskirts. Our key pillars are space, serenity, security, ambience and cost effective living. 
        <br>Enjoy your search. 
        </div>        

	</div>
	<!--SERVICE END-->


	<!--FOOTER START-->
	<footer class="footer section-padding">
		<div class="container">
			<div class="row">
				<div style="visibility: visible; animation-name: zoomIn;" class="col-sm-12 text-center wow zoomIn">
					<h3>Follow us on</h3>
					<div class="footer_social">
						<ul>
					<li><a class="f_facebook" href=""><i class="fa fa-facebook"></i></a></li>
					<li><a class="f_twitter" href=""><i class="fa fa-twitter"></i></a></li>
					<li><a class="f_instagram" href=""><i class="fa fa-instagram"></i></a></li>						
						</ul>
					</div>
				</div><!--- END COL -->
			</div><!--- END ROW -->
		</div><!--- END CONTAINER -->
	</footer>
	<!--FOOTER END-->
	<div class="footer-bottom">
		<div class="container">
			<div style="visibility: visible; animation-name: zoomIn;" class="col-md-12 text-center wow zoomIn">
				<div class="footer_copyright">
					<p class="p">Copyright © 2017 kejamart. All rights reserved.</p>

				</div>
			</div>
		</div>
	</div>
  	<script src="js/jquery.min.js"></script>
  	<script src="js/jquery.easing.min.js"></script>
  	<script src="js/bootstrap.min.js"></script>
  	<script src="js/jquery.mixitup.js" type="text/javascript"></script>
	<script type="text/javascript" src="js/slick.min.js"></script>

</body>
</html>