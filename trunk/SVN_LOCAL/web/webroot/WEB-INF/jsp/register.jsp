<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Find Quality Homes with Ease - kejamart</title>
    
        <!-- JQuery, Jackson, Spring Validation --> 
        
        <script src="<%=request.getContextPath() %>/javascript/jquery.js"></script>
        <script type="text/javascript">
    	var contexPath = "<%=request.getContextPath() %>";
        </script>
        <script src="<%=request.getContextPath() %>/javascript/register.js"></script>
        
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-106182339-1', 'auto');
  ga('send', 'pageview');

</script>        

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="css/animate.css" rel="stylesheet" />
    <!-- Squad theme CSS -->
    <link href="css/style.css" rel="stylesheet">
	<link href="css/login.css" rel="stylesheet">
	<link href="color/default.css" rel="stylesheet">
	<link href="css/app.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/style.css">
  <link rel = "shortcut icon" href="img/logox.png"/>	


</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">

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

	<!-- Section: login -->
    <section id="contact" class="home-section nopadd-bot color-dark bg-gray text-center"><section>
     <h6 class="h-bold">Register</h6>		
     
      <div class="modal-dialog">
	  <div class="loginmodal-container">
	  
	  <form id="registerForm" method="post">
                        
       <div id="alert-danger" class="alert alert-danger" style="display:none"></div>
       <div id="alert-success" class="alert alert-success" style="display:none"></div>
					
	    <input type="text" class="form-control" id="firstName" placeholder="First Name">
        <input type="text" class="form-control" id="lastName" placeholder="Last Name">
        <input type="text" class="form-control" id="email" placeholder="Email">
	    <input type="password" class="form-control" id="password" placeholder="Password">
	    <input type="text" class="form-control" id="mobile" placeholder="Mobile">
	    <p><p>
            <select type="select" id="mailing" class="form-control">
            <option value=""> -- Subscribe for News & Events -- </option>
            <option value="subscribe" >Subscribe</option>
            </select>	    
	    <p><p>
	    
            <select type="select" id="role" class="form-control">
            <option value=""> -- Select a category -- </option>
            <option value="USER" >Regular User</option>            
            <option value="MANAGER" >Estate Manager / Agent</option>
            </select>
            <p><p>
            
	    <input type="button" class="btn btn-primary btn-lg btn-block" value="Register" onclick="doAjaxPost()">
            
       </form>
	</div>
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
				<div class="col-md-12 col-lg-12">
					<div class="wow shake" data-wow-delay="0.4s">
					</div><p>
					<p>Copyright � 2017 kejamart. All rights reserved.</p>

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
