<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored ="false" %>

<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Find Quality Homes with Ease - kejamart</title>
    
    <script src="<%=request.getContextPath() %>/javascript/kdialogue.js"></script>
    <script src="<%=request.getContextPath() %>/javascript/kejanitabs.js"></script>
    
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-106182339-1', 'auto');
  ga('send', 'pageview');

</script>    
    
    <!-- css -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="css/nivo-lightbox.css" rel="stylesheet" />
    <link href="css/nivo-lightbox-theme/default/default.css" rel="stylesheet" type="text/css" />
    <link href="css/owl.carousel.css" rel="stylesheet" media="screen" />
    <link href="css/owl.theme.css" rel="stylesheet" media="screen" />
    <link href="css/flexslider.css" rel="stylesheet" />
    <link href="css/animate.css" rel="stylesheet" />
    <link href="color/default.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/style.css"> 
    <link rel = "shortcut icon" href="img/logox.png"/>

</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">

	<section id="intro" class="home-slide text-light">
	
    <!-- Navigation -->
    <div id="navigation">
        <nav class="navbar navbar-custom" role="navigation">
                              <div class="container">
                                    <div class="row">
                                          <div class="col-md-2">
                                                   <div class="site-logo">
                                                            <a href="search.html" class="navbar-brand">kejamart</a>
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
                                                                  <li><a href="search.html">Home</a></li>
                                                                  <li><a href="about.html">About</a></li>
                                                                  <li><a href="contact.html">Contact</a></li>
       
                                                         </div>
                                                            </ul>
                                                      </div>
                                                      
                                                      <!-- /.Navbar-collapse -->
                             
                                          </div>
                                    </div>
                              </div>
                              <!-- /.container -->
                        </nav>
    </div> 
    <!-- /Navigation --> 
    </section>

<section id="works" class="home-section color-dark text-center bg-white">
<h6 class="h-bold">Login</h6>
<p><br><p><br>
<div class="container">
${errmg}

<form id ="loginForm" method="post" class="form-inline">

<div class="form-group">
<input type="text" id="email" name="email" class="form-control" placeholder="Email">
</div>
<p><p>
<div class="form-group">
<input type="password" id="password" name="password" class="form-control" placeholder="Password">
</div>
<p><p>
<input class="btn btn-primary" type="submit" value="Sign In" onclick="doLogin()">

<a href="register.html" class="btn btn-info">Sign Up</a>

</form>

<a href="/kejamart/changepassword.html">Password Reset</a>

${loginProcess}

</div>

</section>

	<footer>
		<div class="container">
		<div style="visibility: visible; animation-name: zoomIn;" class="col-sm-12 text-center wow zoomIn">
			<h3 class="footer h3">Follow us on</h3><br>
			<div class="footer_social">
				<ul>
					<li><a class="f_facebook" href=""><i class="fa fa-facebook"></i></a></li>
					<li><a class="f_twitter" href=""><i class="fa fa-twitter"></i></a></li>
					<li><a class="f_instagram" href=""><i class="fa fa-instagram"></i></a></li>				
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
    <script src="js/jquery.min.js"></script>	 
    <script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.sticky.js"></script>
	<script src="js/jquery.flexslider-min.js"></script>
    <script src="js/jquery.easing.min.js"></script>	
	<script src="js/jquery.scrollTo.js"></script>
	<script src="js/jquery.appear.js"></script>
	<script src="js/stellar.js"></script>
	<script src="js/wow.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/nivo-lightbox.min.js"></script>
    <script src="js/custom.js"></script>
    <script src="contactform/contactform.js"></script>
    
</body>

</html>
