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

    <title>Find Your Preferred Properties, Create Alerts and Get Notifications - Kejani</title>
    
<!-- JQuery, Jackson, Spring Validation --> 
        
<script src="<%=request.getContextPath() %>/javascript/jquery.js"></script>

<script type="text/javascript">
var contexPath = "<%=request.getContextPath() %>";
</script>

<script src="<%=request.getContextPath() %>/javascript/enquire.js"></script>
<script src="<%=request.getContextPath() %>/javascript/kejanitabs.js"></script>
<script src="<%=request.getContextPath() %>/javascript/retrieveProps.js"></script>
<script src="<%=request.getContextPath() %>/javascript/search.js"></script>
<script src="<%=request.getContextPath() %>/javascript/refreshPages.js"></script>
<script src="<%=request.getContextPath() %>/javascript/kdialogue.js"></script>
<script src="<%=request.getContextPath() %>/javascript/userInterface.js"></script>
<script src="<%=request.getContextPath() %>/javascript/deleteLinks.js"></script>    
    
    <!-- css -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="css/owl.carousel.css" rel="stylesheet" media="screen" />
    <link href="css/owl.theme.css" rel="stylesheet" media="screen" />
    <link href="css/flexslider.css" rel="stylesheet" />
    <link href="css/animate.css" rel="stylesheet" />
    <link href="color/default.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/style.css"> 
      <link rel = "shortcut icon" href="img/logoz.jpg"/>

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
                                                            <a href="index.html" class="navbar-brand">Kejani</a>
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
                                                                  <li><a href="index.html">Home</a></li>
                                                                  <li><a href="search.html">Property Search</a></li>
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

  <section id="works" class="home-section color-dark text-center bg-white">

  <div class="panel panel-default">
  <div class="panel-body">	
    
  <div class="detailsdiv">
    <c:if test="${!empty props}">
    <c:forEach var="ppx" items="${props}">   
    <div class="propertylabel"> ${ppx.name}</div>
    </c:forEach> 
    </c:if>    
    
  <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
  
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="4"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="5"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="6"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="7"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="8"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="9"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="10"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="11"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="12"></li>
    
  </ol>
  <div class="carousel-inner" role="listbox">
  
    <c:if test="${!empty props}">
    <c:forEach var="pp" items="${props}"> 
    <div class="item active">
      <img class="d-block img-fluid" src="${pp.path}" style='height:400px;width:600px'>
    </div>
    </c:forEach>
    </c:if>

    <c:if test="${!empty propertyPics}">
    <c:forEach var="pr" items="${propertyPics}"> 
    <div class="item">
      <img class="d-block img-fluid" src="${pr.path}" style='height:400px;width:600px'>
    </div>
    </c:forEach>
    </c:if>      
    
  </div> <!-- carousel-inner -->
  <a class="left carousel-control" href="#carouselExampleIndicators" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
  </a>
  <a class="right carousel-control" href="#carouselExampleIndicators" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
  </a>
  </div> <!-- carouselExampleIndicators -->  
  </div> <!-- detailsdiv -->
  
  <div class="detailsdiv2">
  <div class="contactprop">
      <c:if test="${!empty props}">
      <c:forEach var="p" items="${props}"> 
      <div class="labelheader"><div class="results-headc"> Property Details </div></div>
  	<div class="results-head1"> ${p.category} ${p.propertyType}</div>
  	<div class="results-head1"> <i class="fa fa-map-marker"></i> &nbsp; ${p.locationVal}, ${p.countyVal} County </div>
  	<div class="results-head1"> <i class='fa fa-bed'></i> ${p.bedrooms} bedrooms &nbsp; <i class='fa fa-bath'></i> ${p.bathroom} bathrooms </div>
  	<div class="results-head1"> <i class="fa fa-car"></i> ${p.parking} parking </div>	
  	<div class="views"> ${p.views} views</div>
      </c:forEach>
      </c:if>
   </div><!-- contactprop -->
   </div><!-- detailsdiv -->
   
  <div class="detailsdiv2x">
   <div class="contactprop2">
      <c:if test="${!empty props}">
      <c:forEach var="pps" items="${props}"> 
      <div class="labelheader"><div class="results-heade"> Enquire from Agent &nbsp; </div></div>
      <div class="results-company"> ${pps.company} </div>
         
      <input type="hidden"  id="enquiries" name="enquiries" value="${pps.enquiries}">
      <input type="hidden"  id="views" name="views" value="${pps.views}">   
      <div class="results-headp" id="phonedetails" style="display:none"> <span class="glyphicon glyphicon-phone"></span> &nbsp; ${pps.contacts} Agent's Contact</div>
      <div class="results-head1" id="contacts" style="display:none"><div class="agent">Agent's contact ${pps.contacts} </div></div>
      <div class="results-head1" id="cell2"><input type="text" name="cell" class="form-control" id="cell" placeholder="Your Phone Number"/> </div>
      <div id="error" class="error"></div>
      <div id="info" class="info"></div>
      <div class="enquiry" onclick="sendEnquiry()"> Send Enquiry &nbsp; <span class="glyphicon glyphicon-envelope"></span></div>
      
      </c:forEach>
      </c:if>      
   
   </div>   
   </div><!-- detailsdiv -->   
   
  <div class="detailsdiva">
  <div class="contactprop">
      <c:if test="${!empty props}">
      <c:forEach var="ps" items="${props}"> 
      <div class="labelheaderx"><div class="results-headx">Kshs. ${ps.amount}</div></div>
      </c:forEach>
      </c:if>
   </div><!-- contactprop -->
   </div><!-- detailsdiv --> 
   
   <div class="detailsdivz">
   <div class="contactprop2">
      <c:if test="${!empty props}">
      <c:forEach var="pd" items="${props}"> 
      <div class="labelheader"><div class="results-headc"> Description </div></div>
      <div class="results-head1">${pd.description} </div>
      </c:forEach>
      </c:if>
      </div>

   </div><!-- detailsdiv -->   
   
   <div class="detailsdivz">
   <div class="contactprop2">
      <div class="labelheader"><div class="results-headc"> features </div></div>   
      <c:if test="${!empty feats}">
      <c:forEach var="ft" items="${feats}"> 
      <div class="results-head1"> <img src="led-icons/accept.png"/> &nbsp; ${ft.feature} </div>
      </c:forEach>
      </c:if>
      </div>
   </div><!-- detailsdiv --> 
   
   <div class="detailsdivzz">
   <div class="labelheader"><div class="results-headc"> Resources </div></div>
      <c:if test="${!empty recs}">
      <c:forEach var="rx" items="${recs}">      
      <div class="results-head1"> <img src="led-icons/accept.png"/> &nbsp; <a href="${rx.path}">${rx.fileName} </a> 
      </div>
      </c:forEach>
      </c:if>
   </div><!-- detailsdiv -->    
   
   <div class="detailsdivv">
   <div class="contactprop2"></div>
   </div><!-- detailsdiv -->    

   
   <div class="viewmap" onclick="myMaps()">Google Map &nbsp <i class="fa fa-map-marker"></i></div>
  
   <div class="detailsdiv4" id="detailsdiv4">
   
    <div id="map"></div>
    <script>
      function initMap() {
        <c:if test="${!empty props}">
        <c:forEach var="px" items="${props}">     	  
        var uluru = {${px.latitude}, ${px.longitude}};
        </c:forEach>
        </c:if>        
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 4,
          center: uluru
        });
        var marker = new google.maps.Marker({
          position: uluru,
          map: map
        });
      }
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD_CJcBIHPU1G7zHyzfIuo5KZSMxhVCMw8&callback=initMap">
    </script>   
   
   </div>
      
   </div> <!-- detailsdiv -->  
   </div> <!-- panel body -->
   </div> <!-- panel default -->   
   
   </section>
   
   <!-- /Section: services -->		
	
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
						<p>Copyright © 2017 Kejani. All rights reserved.</p>

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
