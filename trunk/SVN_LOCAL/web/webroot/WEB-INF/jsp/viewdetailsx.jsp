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

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-106182339-1', 'auto');
  ga('send', 'pageview');

</script>
    
    <link rel="canonical" href="https://www.kejamart.com" />
    <script src="https://apis.google.com/js/platform.js" async defer>
    </script>

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
    <link rel="stylesheet" type="text/css" href="css/view.css"> 
    <link rel = "shortcut icon" href="img/logox.png"/>
    
    <c:if test="${!empty props}">
    <c:forEach var="pfb" items="${props}"> 
    
    <meta property="og:url"           content="https://www.kejamart.com/viewdetails.html?id=${pfb.id}&views=${pfb.views}"/>
    <meta property="og:type"          content="website" />
    <meta property="og:title"         content="kejamart - Find Quality Homes with Ease" />
    <meta property="og:description"   content="${pfb.name}. ${pfb.locationVal}, ${pfb.countyVal}. ${pfb.bedrooms} bedrooms" />
    <meta property="og:image"         content="https://www.kejamart.com/KejaniGallery/Livingroom.jpg"/> 
  
    </c:forEach>
    </c:if>  

</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">

<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.10";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
</script>
	
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
  
  <div class="sharediv">
  
  <c:if test="${!empty props}">
  <c:forEach var="psm" items="${props}"> 
  
  <div class="sharefb">
  <div class="fb-share-button" data-href="https://www.kejamart.com/viewdetails.html?id=${psm.id}&amp;views=${psm.views}" data-layout="button_count" data-size="small" data-mobile-iframe="true">
  <a class="fb-xfbml-parse-ignore" target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=http%3A%2F%2Flocalhost%3A9090%2Fkejani%2Fviewdetails.html%3Fid%3D1%26views%3D123&amp;src=sdkpreparse">Share</a></div>
  </div>
  
  <div class="sharex">
  <a href="https://twitter.com/share" class="twitter-share-button" data-url="https://www.kejamart.com/viewdetails.html?id=${psm.id}&amp;views=${psm.views}" data-show-count="true">Tweet</a>
  <script async src="//platform.twitter.com/widgets.js" charset="utf-8"></script>
  </div>
  
  <div class="sharex">
  <g:plus action="share"></g:plus>
  </div>
  
  </c:forEach> 
  </c:if>
  
  </div>
  
  <section id="works" class="home-section color-dark text-center bg-white"> 
  
  <div class="panel panel-default">
  <div class="panel-body"> 
  
  <div class="maincover">
  <div class="propcover">
   
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
      <img class="d-block img-fluid" src="${pr.path}" style='height:370px;width:100%'>
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
  
    <div class="detailsdivamt">
    <div class="contactprop">
        <c:if test="${!empty props}">
        <c:forEach var="ps" items="${props}"> 
        <div class="results-headx">Kshs <fmt:formatNumber value="${ps.amount}" type="currency" currencySymbol="" pattern="###,###"/> </div>
        </c:forEach>
        </c:if>
     </div><!-- contactprop -->
   </div><!-- detailsdiv --> 
   
  <div class="detailsdiv">
   <div class="borderx"></div>
  <div class="contactprop">
      <c:if test="${!empty props}">
      <c:forEach var="p" items="${props}"> 
      <div class="results-headc"> Property Details </div>
  	<div class="results-head1"> ${p.category} ${p.propertyType}</div>
  	<div class="results-head1"> <i class="fa fa-map-marker"></i> ${p.locationVal}, ${p.countyVal} </div>
  	<div class="results-head1"> <a class="results-headrr"> ${p.bedrooms} - </a> <a class="results-headr"> Bedrooms </a></div>
  	<div class="results-head1"> <a class="results-headrr"> ${p.bathroom} - </a> <a class="results-headr"> Bathrooms </a></div>
  	<div class="results-head1"> <a class="results-headrr"> ${p.parking} - </a> <a class="results-headr"> Parking Spaces </a></div>
      </c:forEach>
      </c:if>
   </div><!-- contactprop -->
   </div><!-- detailsdiv -->
   
   <div class="detailsdivdet">
   <div class="borderx"></div>
   <div class="descdiv">
      <c:if test="${!empty props}">
      <c:forEach var="pd" items="${props}"> 
      <div class="results-headc"> Description </div>
      <div class="results-head1">${pd.description} </div>
      </c:forEach>
      </c:if>
   </div>
   </div><!-- detailsdiv -->  
   
   <div class="detailsdiv">
   <div class="borderx"></div>
   <div class="contactprop2">
      <div class="results-headc"> Features </div>   
      <c:if test="${!empty feats}">
      <c:forEach var="ft" items="${feats}"> 
      <div class="results-head1"> <img src="led-icons/accept.png"/> &nbsp; ${ft.feature} </div>
      </c:forEach>
      </c:if>
      </div>
      </div><!-- detailsdiv --> 
      
   <div class="detailsdiv">
   <div class="borderx"></div>
   <div class="contactprop2">
   <div class="results-headc"> Resources </div>
      <c:if test="${!empty recs}">
      <c:forEach var="rx" items="${recs}">      
      <div class="results-head1"> <img src="led-icons/accept.png"/> &nbsp; <a href="${rx.path}">${rx.fileName} </a> 
      </div>
      </c:forEach>
      </c:if>
      </div>
   </div><!-- detailsdiv -->  

   <div class="mymaps">
   <div class="viewmap" onclick="myMaps()"> Map &nbsp <i class="fa fa-map-marker"></i></div>
   </div>   
   
    <div id="map"></div>

    <script>
      function initMap() {
        <c:if test="${!empty props}">
        <c:forEach var="px" items="${props}">
        
        var uluru = {lat: ${px.latitude}, lng: ${px.longitude}};
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 12,
          center: uluru
        });
        var marker = new google.maps.Marker({
          position: uluru,
          map: map
        });
      }
        
        </c:forEach>
        </c:if>      
    </script>
    
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDHYjSPiAMTNlrCKUQd4col-mwAtTeebHo&callback=initMap" type="text/javascript">
    </script>    
   
   </div><!-- propcover --> 
   
   <div class="relatedprop">
   
  <div class="detailsdiv">
  <div class="borderx"></div>
   <div class="contactprop2">
      <c:if test="${!empty props}">
      <c:forEach var="pps" items="${props}"> 
      <div class="results-headc"> Enquire from Agent &nbsp; </div>
      
      <div class="detailslogo">
      <img src="${pps.logo}" style='height:100px; width:100px;'/>
      </div>
      <input type="hidden"  id="enquiries" name="enquiries" value="${pps.enquiries}">
      <input type="hidden"  id="views" name="views" value="${pps.views}">   
      <div class="results-headp" id="phonedetails" style="display:none"> <span class="glyphicon glyphicon-phone"></span> &nbsp; ${pps.contacts} Agent's Contact</div>
      <div class="results-head1" id="contacts" style="display:none"><div class="agent">Agent's contact ${pps.contacts} </div></div>
      <div class="propname" id="namex2"><input type="text" name="namex" class="form-control" id="namex" placeholder="Name"/> </div>
      <div class="propname" id="cell2"><input type="text" name="cell" class="form-control" id="cell" placeholder="Phone Number"/> </div>
      <div class="propname" id="emailx2"><input type="text" name="emailx" class="form-control" id="emailx" placeholder="Email"/> </div>
      <div id="error" class="error"></div>
      <div id="info" class="info"></div>
      <div class="enquiry" onclick="sendEnquiry()"> Send Enquiry &nbsp; <span class="glyphicon glyphicon-envelope"></span></div>
      
      </c:forEach>
      </c:if>      
   
   </div>   
   </div><!-- detailsdiv -->    
   
   
   <div class="detailsdiv">
   <div class="borderx"></div>
   <div class="results-headc"> Related Property </div>   
   <!-- Property list: Full -->                    
    <c:if test="${!empty related}">
    <c:forEach var="rel" items="${related}">            
	<div class="propertydiv"> 	 
	
	<form id="viewDetails" action="viewdetails.html" method="get" accept-charset=utf-8>
	<input type="hidden" name="id" id="id" value="${rel.id}">
	<input type="hidden" name="views" id="views" value="${rel.views}">	
	<img src="${rel.path}" ${rel.orientation}><p>	
	<div class="results-headb"> ${rel.name} </div>
	<div class="results-head1"> ${rel.locationVal}, ${rel.countyVal} County.</div>
	<div class="results-head1"> <i class='fa fa-bed'></i> ${rel.bedrooms} &nbsp; <i class='fa fa-bath'></i> 
	${rel.bathroom} &nbsp; <i class="fa fa-car"></i> ${rel.parking} </div>	
	<div class="resultsamt"> Ksh. <fmt:formatNumber value="${rel.amount}" type="currency" currencySymbol="" pattern="###,###"/> </div>
	<div class="views"> ${rel.views} views </div>
        <button type="submit" class="view-holder">Details</button>
	</form>		
    </div>         
    </c:forEach>
    </c:if> 
    </div>   
    
       <c:if test="${!empty props}">
       <c:forEach var="yt" items="${props}"> 
       
       <div class="detailstube"> 
       <iframe class ="iframex" id="iframex" src="${yt.videourl}" frameborder="0" allowfullscreen></iframe>
       </div><!-- detailsdiv -->   
       </c:forEach>
       </c:if>
    
   </div><!-- relatedprop -->   
   </div><!-- maincover -->
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
						<p>Copyright � 2017 kejamart. All rights reserved.</p>

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
