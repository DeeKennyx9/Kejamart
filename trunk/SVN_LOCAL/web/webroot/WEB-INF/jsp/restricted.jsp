<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored ="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Find Quality Homes with Ease - kejamart</title>
    
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
                                                                  <li><a href="search.html">Home</a></li>
                                                                  <li><a href="about.html">About</a></li>
                                                                  <li><a href="contact.html">Contact</a></li>
                                                                  <c:if test="${empty userSession}"> 
			                                          <li><a href="login.html" class="btn btn-bannery">Log In</a></li>
			                                          <li><a href="register.html" class="btn btn-bannerx">Sign Up</a></li>
								  </c:if>
								  <c:if test="${!empty userSession}">
								  <li>${logoutLink}</li>
								  <li>${profileLink}</li>
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
    </div> 
    <!-- /Navigation -->
    </section>
    
<section id="works" class="home-section2 color-dark bg-white">    

<div class="results-header1"><i> Access denied !!! Please log in or register to access this page ...</i></div>

<img src="img/restricted.jpg" height="500px"/>	

<br><p>

</section>
    
</body>

</html>