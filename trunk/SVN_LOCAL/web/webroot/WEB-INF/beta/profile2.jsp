<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored ="false" %>

<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Kejani - Your Property Search and Listing Made Easy</title>
    
<script type="text/javascript" src="javascript/menuz.js">
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
	<link href="css/simple-sidebar.css" rel="stylesheet">

    <!-- =======================================================
        Theme Name: Squadfree
        Theme URL: https://bootstrapmade.com/squadfree-free-bootstrap-template-creative/
        Author: BootstrapMade
        Author URL: https://bootstrapmade.com
    ======================================================= -->

</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom" onload="load()">
	<!-- Preloader -->
	<div id="preloader">
	  <div id="load"></div>
	</div>


    <nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                    <i class="fa fa-bars"></i>
                </button>
                <a class="navbar-brand" href="index.html">
                    <h1>Kejani</h1>
                </a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#index">Home</a></li>
        <li><a href="#search">Search Property</a></li>
		<li><a href="#developers">Developers</a></li>
		<li><a href="#contact">Contact Us</a></li>
      </ul>
            </div>
            <!-- /.navbar-collapse -->
             
		<c:if test="${empty userSession}">   
		</c:if>
        <c:if test="${!empty userSession}">
            <span id="email">${userSession}</span>
        </c:if>
            <span id="profile">${profileLink}</span>
        </div>
        <!-- /.container -->
    </nav>

<section>

<div id="wrapper">

        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
              <li class='sidebar-brand'>Settings</li>
                    <a href="#">${prof}</a>
                    <a href="#">${addp}</a>
                    <a href="#">${musers}</a>
                    <a href="#">${addpp}</a>
                    <a href="#">${editpp}</a>
                    <a href="#">${alert}</a>
                    <a href="#">${malert}</a>
                    <a href="#">${notif}</a>
                    <a href="#">${cnotif}</a>
                    <a href="#">${adv}</a>
                    <a href="#">${com}</a>
                    <a href="#">${dash}</a>
                    <a href="#">${help}</a>
             </ul>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1>Profile</h1>

<div id="error" class="error"></div>
<div id="info" class="success"></div>

<c:forEach var="profile" items="${profiles}"> 

<form action="editprofile.html" method="post"> 
<input type="hidden" name="id" value ="${profile.id}"><br>
<input type="hidden" name="firstName" value ="${profile.firstName}"><br>
<input type="hidden" name="lastName" value ="${profile.lastName}"><br>
<input type="hidden" name="email" value ="${profile.email}"><br>
<input type="hidden" name="password" value ="${profile.password}">
<p>
<input type="submit" value="Edit" />

</form>

</c:forEach>

                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->

</section>

    <!-- Core JavaScript Files -->
    <script src="jsmain/jquery.min.js"></script>
    <script src="jsmain/bootstrap.min.js"></script>
    <script src="jsmain/jquery.easing.min.js"></script>
	<script src="jsmain/jquery.scrollTo.js"></script>
	<script src="jsmain/wow.min.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="jsmain/custom.js"></script>
    <script src="contactform/contactform.js"></script>
    <!-- jQuery -->
    <script src="jsmain/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="jsmain/bootstrap.min.js"></script>

    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>

</body>

</html>
